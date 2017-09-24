package com.foodkonnekt.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.OrderService;
import com.foodkonnekt.service.ZoneService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.ProducerUtil;
import com.google.gson.Gson;

@Controller
public class AddressController {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private OrderService orderService;

    /**
     * Check zone is exist in delivery zone or not
     * 
     * @param address
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkDeliveryZone", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> checkDelivery(@RequestBody final Address address,
                    HttpServletRequest request) throws Exception {
        Map<Object, Object> deliveryZoneMap = new HashMap<Object, Object>();
        try {
        	System.out.println("isDeliveryKoupon+isDeliveryKoupon+");
            String addressResponse = ProducerUtil.validateAddress(address);
            boolean status = addressResponse.contains("<Error>");
            if (status) {
                deliveryZoneMap.put("message", "Your address is not valid , enter valid address");
            } else {
                String deliveryCheckStatus = null;
                String deliveryItemPosId = null;
                double deliveryFee = 0;
                int isTaxableStatus = 0;
                double minimumDeliveryAmount = 0;
                String avgDeliveryTime = "0";
                HttpSession session = request.getSession();
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    address.setMerchId(merchant.getId());
                    Customer customer = (Customer) session.getAttribute("customer");
                    if(customer!=null){
                    	if(customer.getCustomerPosId()!=null){
                    		address.setCustPosId(customer.getCustomerPosId());
                    	}
                    }
                    address.setCustomer(customer);
                    Gson gson = new Gson();
                    String addressJson = gson.toJson(address);
                    String result = ProducerUtil.checkDeliveryZone(addressJson);
                    
                    
                    JSONObject jObject = new JSONObject(result);
                    if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                        deliveryCheckStatus = jObject.getString("DATA");
                        String[] deliveryResponse = deliveryCheckStatus.split("#");
                        deliveryItemPosId = deliveryResponse[0];
                        deliveryFee = Double.parseDouble(deliveryResponse[1]);
                        if (deliveryResponse[2] != null) {
                            if (!deliveryResponse[2].equals("null")) {
                                isTaxableStatus = Integer.parseInt(deliveryResponse[2]);
                            }
                        }
                        if (deliveryResponse[3] != null) {
                            if (!deliveryResponse[3].equals("null")) {
                                minimumDeliveryAmount = Double.parseDouble(deliveryResponse[3]);
                            }
                        }

                        if (deliveryResponse[4] != null) {
                            if (!deliveryResponse[4].equals("null")) {
                                avgDeliveryTime = deliveryResponse[4];
                            }
                        }
                    }
                    if (deliveryCheckStatus != null) {
                        deliveryZoneMap.put("message", "Your zone is in delivery zone");
                        deliveryZoneMap.put("itemPosId", deliveryItemPosId);
                        if(address!=null &&address.getIsDeliveryKoupon()!=null && address.getIsDeliveryKoupon()==true){
                        	System.out.println("inside true zone");
                        	deliveryFee = 0.0;
                        	minimumDeliveryAmount = 0.0;
                        deliveryZoneMap.put("itemPrice", deliveryFee);
                        deliveryZoneMap.put("minimumDeliveryAmount", minimumDeliveryAmount);
                        }else{
                        	deliveryZoneMap.put("itemPrice", deliveryFee);
                            deliveryZoneMap.put("minimumDeliveryAmount", minimumDeliveryAmount);
                        }
                        deliveryZoneMap.put("avgDeliveryTime", avgDeliveryTime);
                        if (isTaxableStatus == 1) {
                            deliveryZoneMap.put("deliveryTaxStatus", IConstant.BOOLEAN_TRUE);
                            deliveryZoneMap.put("deliveryTaxPrice", orderService.findConvenienceFeeAfterTax(
                                            String.valueOf(deliveryFee), merchant.getId()));
                            deliveryZoneMap.put("deliveryTaxWithComma", orderService.findConvenienceFeeAfterMultiTax(
                                            String.valueOf(deliveryFee), merchant.getId()));
                        } else {
                            deliveryZoneMap.put("deliveryTaxStatus", IConstant.BOOLEAN_FALSE);
                            deliveryZoneMap.put("deliveryTaxPrice", IConstant.BOOLEAN_FALSE);
                        }
                        String adressResult = ProducerUtil.createUpdateCloverAddress(addressJson);
                        if(adressResult!=null && !adressResult.isEmpty()){
                        	JSONObject addressObject = new JSONObject(adressResult);
                        	if(adressResult.contains("id")){
                        	System.out.println(addressObject.getString("id"));
                        	address.setAddressPosId(addressObject.getString("id"));
                        	}
                        }
                        zoneService.saveAddress(address);
                    } else {
                        deliveryZoneMap.put("message",
                                        "Your address is not within delivery zone. Please input a new address or select pick up option");
                    }
                } else {
                    deliveryZoneMap.put("message", "timeIssue");
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return deliveryZoneMap;
    }

    @RequestMapping(value = "/checkAddressValidity", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> checkAddress(@RequestParam(required = true) String aptNumber,
                    @RequestParam(required = true) String firstAddress, @RequestParam(required = true) String city,
                    @RequestParam(required = true) String state, @RequestParam(required = true) String zip,
                    HttpServletRequest request) throws Exception {
        Map<Object, Object> deliveryZoneMap = new HashMap<Object, Object>();
        try {
            String aptNum[] = aptNumber.split(",");
            String firstAddressArray[] = firstAddress.split(",");
            String cityArry[] = city.split(",");
            String stateArray[] = state.split(",");
            String zipArray[] = zip.split(",");
            String numb = "";
            boolean msgStatus = false;
            for (int i = 1; i < aptNum.length; i++) {
                Address address = new Address();
                address.setAddress1(aptNum[i]);
                if (firstAddressArray.length >= i) {
                    address.setAddress2(firstAddressArray[i]);
                } else {
                    address.setAddress2("");
                }
                address.setCity(cityArry[i]);
                address.setState(stateArray[i]);
                address.setZip(zipArray[i]);
                String addressResponse = ProducerUtil.validateAddress1(address);
                boolean status = addressResponse.contains("<Error>");
                if (status) {
                    msgStatus = true;
                    numb = numb + "," + i;
                }
            }
            if (msgStatus) {
                deliveryZoneMap.put("statusM", 1);
                deliveryZoneMap.put("message", "Your " + numb + " address is not valid , enter valid address");
            } else {
                deliveryZoneMap.put("statusM", 0);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return deliveryZoneMap;
    }

    @RequestMapping(value = "/checkDeliveryAfterLogin", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> checkLoginAddress(@RequestParam(required = true) Integer addressId,
    														@RequestParam(required = true) Boolean isDeliveryKoupon,
                    HttpServletRequest request) throws Exception {
        Map<Object, Object> deliveryZoneMap = new HashMap<Object, Object>();
        try {
        	System.out.println("isDeliveryKoupon+"+isDeliveryKoupon);
            Address address = zoneService.findAddressById(addressId);
            HttpSession session = request.getSession();
            String deliveryCheckStatus = null;
            String deliveryItemPosId = null;
            double deliveryFee = 0;
            int isTaxableStatus = 0;
            double minimumDeliveryAmount = 0;
            String avgDeliveryTime = "0";
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                address.setMerchId(merchant.getId());
                Gson gson = new Gson();
                String addressJson = gson.toJson(address);
                String result = ProducerUtil.checkDeliveryZone(addressJson);
                JSONObject jObject = new JSONObject(result);
                if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                    deliveryCheckStatus = jObject.getString("DATA");
                    String[] deliveryResponse = deliveryCheckStatus.split("#");
                    deliveryItemPosId = deliveryResponse[0];
                    deliveryFee = Double.parseDouble(deliveryResponse[1]);
                    if (deliveryResponse[2] != null) {
                        if (!deliveryResponse[2].equals("null")) {
                            isTaxableStatus = Integer.parseInt(deliveryResponse[2]);
                        }
                    }
                    if (deliveryResponse[3] != null) {
                        if (!deliveryResponse[3].equals("null")) {
                            minimumDeliveryAmount = Double.parseDouble(deliveryResponse[3]);
                        }
                    }
                    if (deliveryResponse[4] != null) {
                        if (!deliveryResponse[4].equals("null")) {
                            avgDeliveryTime = deliveryResponse[4];
                        }
                    }
                }
                if (deliveryCheckStatus != null) {
                    deliveryZoneMap.put("message", "Your zone is in delivery zone");
                    deliveryZoneMap.put("itemPosId", deliveryItemPosId);
                    
                    if(isDeliveryKoupon== true)
                    {
                    	deliveryFee = 0.0;
                    	minimumDeliveryAmount = 0.0;
                    	System.out.println("inside true"+deliveryFee+" "+minimumDeliveryAmount);
                    	deliveryZoneMap.put("itemPrice", deliveryFee);
                    deliveryZoneMap.put("minimumDeliveryAmount",minimumDeliveryAmount);
                    }else{
                    	deliveryZoneMap.put("itemPrice", deliveryFee);
                        deliveryZoneMap.put("minimumDeliveryAmount", minimumDeliveryAmount);
                    }
                    System.out.println("delivery fees after condition--"+deliveryFee+" "+minimumDeliveryAmount);
                    deliveryZoneMap.put("avgDeliveryTime", avgDeliveryTime);
                    if (isTaxableStatus == 1) {
                        deliveryZoneMap.put("deliveryTaxStatus", IConstant.BOOLEAN_TRUE);
                        deliveryZoneMap.put(
                                        "deliveryTaxPrice",
                                        orderService.findConvenienceFeeAfterTax(String.valueOf(deliveryFee),
                                                        merchant.getId()));
                        deliveryZoneMap.put("deliveryTaxWithComma", orderService.findConvenienceFeeAfterMultiTax(
                                        String.valueOf(deliveryFee), merchant.getId()));
                    } else {
                        deliveryZoneMap.put("deliveryTaxStatus", IConstant.BOOLEAN_FALSE);
                        deliveryZoneMap.put("deliveryTaxPrice", IConstant.BOOLEAN_FALSE);
                    }
                } else {
                    deliveryZoneMap.put("message",
                                    "Your address is not within delivery zone. Please input a new address or select pick up option");
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return deliveryZoneMap;
    }
}
