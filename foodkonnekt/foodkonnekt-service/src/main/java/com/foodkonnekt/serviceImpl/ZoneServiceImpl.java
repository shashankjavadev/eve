package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.clover.vo.ZoneVo;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.ZoneRepository;
import com.foodkonnekt.service.ZoneService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerrRepository customerrRepository;

    /**
     * Save zone into database
     */
    public Zone createZone(Zone zone) {
        zone.setCreatedOn(new Date());
        zone.setModifiedOn(new Date());
        zone = zoneRepository.save(zone);
        return zone;
    }

    public Zone updateZoneDetail(Zone zone) {
        if (zone.getId() != null) {
            Zone dbZone = zoneRepository.findOne(zone.getId());
            dbZone.setModifiedOn(new Date());
            dbZone.setZoneName(zone.getZoneName());
            dbZone = zoneRepository.save(dbZone);
            return dbZone;
        } else {
            createZone(zone);
            return zone;
        }
    }

    public String deleteZone(Integer zoneId) {
        if (zoneId != null) {
            Zone zone = zoneRepository.findOne(zoneId);
            if (zone == null) {
                return "No Zone exist for the ID : " + zoneId;
            } else {
                try {
                    zoneRepository.delete(zoneId);
                    return "Zone deleted for Id: " + zoneId;
                } catch (Exception e) {
                    return "Exception occure while deleting the zone for ID: " + zoneId + "  is : " + e.getMessage();
                }
            }
        } else {
            return "Zone Id is empty";
        }

    }

    public List<Zone> getAllZone() {
        return zoneRepository.findAll();
    }

    /**
     * Check delivery zone
     */
    public String checkAddressForDeliveryZone(Address address) {
        List<Zone> zones = zoneRepository.findByMerchantIdAndZoneStatus(address.getMerchId(), 0);
        String status = null;
        try {
            double distance;
            Collections.sort(zones, new Comparator<Zone>() {
                public int compare(Zone z1, Zone z2) {
                    return z1.getZoneDistance().compareTo(z2.getZoneDistance());
                }
            });
            final double MILES_PER_KILOMETER = 0.621;
            for (Zone zone : zones) {
                distance = checkDelivery(address, zone.getAddress());
                double miles = distance * MILES_PER_KILOMETER;
                if (miles <= zone.getZoneDistance()) {
                    status = zone.getDeliveryLineItemPosId();
                    return status + "#" + zone.getDeliveryFee() + "#" + zone.getIsDeliveryZoneTaxable() + "#"
                                    + zone.getMinDollarAmount() + "#" + zone.getAvgDeliveryTime();
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Find distance between two address
     * 
     * @param address
     * @param merchantAddress
     * @return
     */
    private double checkDelivery(Address address, Address merchantAddress) {
        String add2 = null;
        if (address.getAddress2() != null) {
            add2 = address.getAddress2();
        } else {
            add2 = "";
        }
        String merchantAdd2 = null;
        if (merchantAddress.getAddress2() != null) {
            merchantAdd2 = merchantAddress.getAddress2();
        } else {
            merchantAdd2 = "";
        }
        String origin = address.getAddress1() + "," + add2 + "," + address.getCity() + "," + address.getState() + ","
                        + address.getZip();
        String des = merchantAddress.getAddress1() + "," + merchantAdd2 + "," + merchantAddress.getCity() + ","
                        + merchantAddress.getState() + "," + merchantAddress.getZip();
        HttpGet request;
        double distance = 0;
        try {
            request = new HttpGet("https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                            + URLEncoder.encode(origin, "UTF-8") + "&destinations=" + URLEncoder.encode(des, "UTF-8"));
            String response = convertToStringJson(request);
            JSONObject jObject = new JSONObject(response);
            String distan = null;
            if (jObject.getString("status").equals("OK")) {
                JSONArray rows = jObject.getJSONArray("rows");
                for (Object jObj : rows) {
                    JSONObject jsonObj = (JSONObject) jObj;
                    JSONArray elements = jsonObj.getJSONArray("elements");
                    for (Object jObj2 : elements) {
                        JSONObject obj = (JSONObject) jObj2;
                        if(obj!=null && obj.has("distance")){
                        JSONObject distanceObj = obj.getJSONObject("distance");
                        distan = distanceObj.getString("text");
                        String[] arry = distan.split(" ");
                        if (arry[0] != null) {
                            if (arry[0].contains(",")) {
                                distance = Double.parseDouble(arry[0].replace(",", ""));
                            } else {
                                distance = Double.parseDouble(arry[0]);
                            }
                        }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }

        return distance;
    }

    /**
     * convert http response to string
     * 
     * @param request
     * @return
     */
    public String convertToStringJson(HttpGet request) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        StringBuilder responseBuilder = new StringBuilder();
        try {
            response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

    /**
     * Save zone
     */
    public void save(Zone zone, Merchant merchant) {
        try {
            List<Address> addresses = addressRepository.findByMerchantId(merchant.getId());
            if (zone != null) {
            	if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
                if (zone.getId() == null) {
                    String result = CloverUrlUtil.addDeliveryFeeLineItem(zone.getDeliveryFee(),
                                    merchant.getPosMerchantId(), merchant.getAccessToken(), "Delivery Fee",
                                    zone.getIsDeliveryZoneTaxable());
                    if (result != null) {
                        JSONObject jObject = new JSONObject(result);
                        if (jObject.toString().contains("id")) {
                            zone.setDeliveryLineItemPosId(jObject.getString("id"));
                        }
                    }
                } else {
                    String result = CloverUrlUtil.updateDeliveryFeeLineItem(zone.getDeliveryFee(),
                                    merchant.getPosMerchantId(), merchant.getAccessToken(), "Delivery Fee",
                                    zone.getDeliveryLineItemPosId(), zone.getIsDeliveryZoneTaxable());
                }
            	}
            	zone.setMerchant(merchant);
            }
            
            if (addresses != null) {
                Address address = addresses.get(0);
                zone.setAddress(address);
            }
            zoneRepository.save(zone);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
    }

    /**
     * Find zone by merchantId
     */
    public List<Zone> findZoneByMerchantId(Integer merchantId) {
        return zoneRepository.findByMerchantId(merchantId);
    }

    /**
     * Find by delivery zone
     */
    public Zone findById(int deliveryZoneId) {
        return zoneRepository.findOne(deliveryZoneId);
    }

    public String findByMerchantIdAndDeliveryZoneName(Integer merchantId, String deliveryZoneName) {
        Zone zone = zoneRepository.findByMerchantIdAndZoneName(merchantId, deliveryZoneName);
        if (zone != null) {
            return "true";
        } else {
            return "false";
        }

    }

    /**
     * Save valid address into database
     */
    public void saveAddress(Address address) {
        if (address.getCustomer() != null) {
            Customer customer = customerrRepository.findOne(address.getCustomer().getId());
            address.setCustomer(customer);
            addressRepository.save(address);
        }
    }

    public List<Zone> findZoneByMerchantIdAndStatus(Integer merchantId) {
        return zoneRepository.findByMerchantIdAndZoneStatus(merchantId, 0);
    }

    public String findByMerchantIdAndDeliveryZoneNameAndZoneId(Integer merchantId, String deliveryZoneName,
                    Integer zoneId) {
        Zone zone = zoneRepository.findByMerchantIdAndZoneName(merchantId, deliveryZoneName);
        if (zone != null) {
            if (zone.getId() != zoneId)
                return "true";
            else
                return "false";
        } else {
            return "false";
        }

    }

    /**
     * Find Address by addressId
     */
    public Address findAddressById(Integer addressId) {
        Address address = addressRepository.findOne(addressId);
        address.setCustomer(null);
        address.setMerchant(null);
        address.setZones(null);
        return address;
    }

    public double checkMinDeliveryAmount(Double subTotalAmount, Integer merchantId) {
        List<Zone> zones = zoneRepository.findByMerchantIdAndZoneStatus(merchantId, 0);
        Zone minAmountZone;
        if (!zones.isEmpty()) {
            if (zones.size() >= 2) {
                minAmountZone = Collections.min(zones, new ZoneVo());
            } else {
                minAmountZone = zones.get(IConstant.BOOLEAN_FALSE);
            }
            int retval = Double.compare(Double.parseDouble(minAmountZone.getMinDollarAmount()), subTotalAmount);
            if (retval > 0) {
                return Double.parseDouble(minAmountZone.getMinDollarAmount());
            } else if (retval < 0) {
                return 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
