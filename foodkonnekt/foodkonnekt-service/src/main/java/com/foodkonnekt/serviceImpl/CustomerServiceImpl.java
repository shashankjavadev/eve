package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.clover.vo.CustomerJsonVo;
import com.foodkonnekt.clover.vo.CustomerVo;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.CouponRedeemedDto;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OrderDiscount;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OrderDiscountRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.repository.ZoneRepository;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.util.CouponUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerrRepository customerrRepository;
    
    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    VendorRepository vendorRepository;
    /**
     * Find by email , password and vendorId
     */
    public Customer findByEmailAndPasswordAndVendorId(String emailId, String password, Integer vendorId,Integer merchantId) {
        List<Customer> customers = customerrRepository.findByEmailIdAndPasswordAndVendorId(emailId, password,
                        vendorId);
        Customer resultcustomer = null;
        try {
            if (customers != null) {
                if (!customers.isEmpty()) {
                	for(Customer customer:customers){
                    
                    if (customer != null) {
                        customer.setAddresses(null);
                        if (customer.getMerchantt() != null && customer.getMerchantt().getId()==merchantId) {
                            customer.getMerchantt().setItems(null);
                            customer.getMerchantt().setMerchantSubscriptions(null);
                            customer.getMerchantt().setModifierGroups(null);
                            customer.getMerchantt().setModifiers(null);
                            customer.getMerchantt().setOpeningClosingDays(null);
                            customer.getMerchantt().setOrderRs(null);
                            customer.getMerchantt().setOrderTypes(null);
                            customer.getMerchantt().setPaymentModes(null);
                            customer.getMerchantt().setTaxRates(null);
                            customer.getMerchantt().setVouchars(null);
                            customer.getMerchantt().setAddresses(null);
                            customer.getMerchantt().setSubscription(null);
                            customer.getMerchantt().setMerchantLogin(null);
                            customer.getMerchantt().setSocialMediaLinks(null);
                            resultcustomer=customer;
                        }
                    }
                   
                	}
                	if(resultcustomer==null){
                		Customer customer = customers.get(0);
                		resultcustomer=new Customer();
                		resultcustomer.setId(null);
                		if(customer.getPassword()!=null){
                			resultcustomer.setPassword("@duplicatepassword#"+customer.getPassword());
                		}
                    if (resultcustomer != null) {
                    	Merchant merchant = merchantRepository.findOne(merchantId);
                    	resultcustomer.setMerchantt(merchant);
                    	resultcustomer.setFirstName(customer.getFirstName());
                    	resultcustomer.setLastName(customer.getLastName());
                    	resultcustomer.setAnniversaryDate(customer.getAnniversaryDate());
                    	resultcustomer.setBirthDate(customer.getBirthDate());
                    	resultcustomer.setCreatedDate(customer.getCreatedDate());
                    	resultcustomer.setCustomerType("java");
                    	resultcustomer.setAddresses(customer.getAddresses());
                    	resultcustomer.setEmailId(customer.getEmailId());
                    	resultcustomer.setPhoneNumber(customer.getPhoneNumber());
                    	
                        if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
                        	Clover clover = new Clover();
                        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
                        clover.setUrl(IConstant.CLOVER_URL);
                        clover.setMerchantId(merchant.getPosMerchantId());
                        clover.setAuthToken(merchant.getAccessToken());
                        String customerResponse = new CloverServiceImpl().createCustomer(resultcustomer, clover);
                        JSONObject jObject = new JSONObject(customerResponse);
                        if (customerResponse.contains("id"))
                        {
                        	resultcustomer.setCustomerPosId(jObject.getString("id"));

                        JSONObject addressesJsonObject = jObject.getJSONObject("addresses");

                        JSONArray addressesJsonArray = addressesJsonObject.getJSONArray("elements");
                        int index = 0;
                        if (addressesJsonArray != null) {
                            for (Object jObj : addressesJsonArray) {
                                JSONObject addressesJson = (JSONObject) jObj;
                                if (addressesJson.toString().contains("id")) {
                                    System.out.println(addressesJson.getString("id"));
                                    Address address = resultcustomer.getAddresses().get(index++);
                                    address.setAddressPosId(addressesJson.getString("id"));
                                    //System.out.println(customer.getAddresses().get(0).getCity());
                                   // customer.getAddresses().add(address);
                                }
                            }
                        }
                        
                        JSONObject emailJsonObject = jObject.getJSONObject("emailAddresses");

                        JSONArray emailJsonArray = emailJsonObject.getJSONArray("elements");
                        if (emailJsonArray != null) {
                            for (Object jObj : emailJsonArray) {
                                JSONObject emailJson = (JSONObject) jObj;
                                if (emailJson.toString().contains("id")) {
                                    System.out.println(emailJson.getString("id"));
                                    resultcustomer.setEmailPosId(emailJson.getString("id"));
                                }
                            }
                        }
                        
                        JSONObject phoneJsonObject = jObject.getJSONObject("phoneNumbers");

                        JSONArray phoneJsonArray = phoneJsonObject.getJSONArray("elements");
                        if (phoneJsonArray != null) {
                            for (Object jObj : phoneJsonArray) {
                                JSONObject phoneJson = (JSONObject) jObj;
                                if (phoneJson.toString().contains("id")) {
                                    System.out.println(phoneJson.getString("id"));
                                    resultcustomer.setPhonePosId(phoneJson.getString("id"));
                                }
                            }
                        }
                    }
                        }
                        String merchantLogo = null;
                        if (merchant.getMerchantLogo() == null) {
                            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
                        } else {
                            merchantLogo = UrlConstant.BASE_PORT + merchant.getMerchantLogo();
                        }
                        resultcustomer = updateCustomerProfile(resultcustomer);

                    	
                    	resultcustomer.setAddresses(null);
                        if (resultcustomer.getMerchantt() != null) {
                            resultcustomer.getMerchantt().setItems(null);
                            resultcustomer.getMerchantt().setMerchantSubscriptions(null);
                            resultcustomer.getMerchantt().setModifierGroups(null);
                            resultcustomer.getMerchantt().setModifiers(null);
                            resultcustomer.getMerchantt().setOpeningClosingDays(null);
                            resultcustomer.getMerchantt().setOrderRs(null);
                            resultcustomer.getMerchantt().setOrderTypes(null);
                            resultcustomer.getMerchantt().setPaymentModes(null);
                            resultcustomer.getMerchantt().setTaxRates(null);
                            resultcustomer.getMerchantt().setVouchars(null);
                            resultcustomer.getMerchantt().setAddresses(null);
                            resultcustomer.getMerchantt().setSubscription(null);
                            resultcustomer.getMerchantt().setMerchantLogin(null);
                            resultcustomer.getMerchantt().setSocialMediaLinks(null);
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
        return resultcustomer;
    }

    public Customer findByEmailAndPassword(String emailId, String password) {
        List<Customer> customers = customerrRepository.findByEmailIdAndPassword(emailId, password);
        Customer customer = null;
        try {
            if (customers != null) {
                if (!customers.isEmpty()) {
                    customer = customers.get(0);
                    if (customer != null) {
                        customer.setAddresses(null);
                        if (customer.getMerchantt() != null) {
                            customer.getMerchantt().setItems(null);
                            customer.getMerchantt().setMerchantSubscriptions(null);
                            customer.getMerchantt().setModifierGroups(null);
                            customer.getMerchantt().setModifiers(null);
                            customer.getMerchantt().setOpeningClosingDays(null);
                            customer.getMerchantt().setOrderRs(null);
                            customer.getMerchantt().setOrderTypes(null);
                            customer.getMerchantt().setPaymentModes(null);
                            customer.getMerchantt().setTaxRates(null);
                            customer.getMerchantt().setVouchars(null);
                            customer.getMerchantt().setAddresses(null);
                            customer.getMerchantt().setSubscription(null);
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
        return customer;
    }
    /**
     * find by customer id
     */
    public List<Address> getAddressByCustomerId(Integer customerId) {
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        for (Address address : addresses) {
            address.setMerchant(null);
        }
        return addresses;
    }

    /**
     * Find customer and customer address by customerId
     */
    public Customer getCustomerProfile(Integer customerId) {
        Customer customer = customerrRepository.findOne(customerId);
        try {
            if (customer != null) {
                List<Address> finalAddresses = new ArrayList<Address>();
                List<Address> addresses = addressRepository.findByCustomerId(customerId);
                for (Address address : addresses) {
                    Address address2 = new Address();
                    address2.setId(address.getId());
                    address2.setAddress1(address.getAddress1());
                    address2.setAddress2(address.getAddress2());
                    address2.setAddress3(address.getAddress3());
                    address2.setCity(address.getCity());
                    address2.setState(address.getState());
                    address2.setZip(address.getZip());
                    address2.setAddressPosId(address.getAddressPosId());

                    List<Zone> zones = zoneRepository.findByMerchantId(customer.getMerchantt().getId());
                    double distance;
                    final double MILES_PER_KILOMETER = 0.621;
                    for (Zone zone : zones) {
                        distance = checkDelivery(address, zone.getAddress());
                        double miles = distance * MILES_PER_KILOMETER;
                        if (miles <= zone.getZoneDistance()) {
                            address2.setDeliveryFee(zone.getDeliveryFee());
                            address2.setDeliveryPosId(zone.getDeliveryLineItemPosId());
                        }
                    }
                    finalAddresses.add(address2);
                }
                customer.setAddresses(finalAddresses);
                customer.getMerchantt().setItems(null);
                customer.getMerchantt().setMerchantSubscriptions(null);
                customer.getMerchantt().setModifierGroups(null);
                customer.getMerchantt().setModifiers(null);
                customer.getMerchantt().setOpeningClosingDays(null);
                customer.getMerchantt().setOrderRs(null);
                customer.getMerchantt().setOrderTypes(null);
                customer.getMerchantt().setPaymentModes(null);
                customer.getMerchantt().setTaxRates(null);
                customer.getMerchantt().setVouchars(null);
                customer.getMerchantt().setAddresses(null);
                customer.getMerchantt().setSubscription(null);
                customer.getMerchantt().setMerchantLogin(null);
                customer.getMerchantt().setSocialMediaLinks(null);;
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return customer;
    }

    public Customer updateCustomerProfile(Customer customer) {
        try {
            if (customer.getId() == null) {
                customer.setCreatedDate(DateUtil.findCurrentDate());
            }
            List<Address> customerAddress = customer.getAddresses();
            int merchantId = customer.getMerchantt().getId();
            customer.setAddresses(null);
            if (customer.getId() != null) {
                Customer existingCustomer = customerrRepository.findOne(customer.getId());
                String existingCustomerOldPassword = existingCustomer.getPassword();
                String existingCustomerUpdatedPassword = customer.getPassword();
                customer.setCreatedDate(existingCustomer.getCreatedDate());
                if (!existingCustomerOldPassword.equals(existingCustomerUpdatedPassword)) {
                    customer.setPassword(EncryptionDecryptionUtil.encryptString(existingCustomerUpdatedPassword));
                }
                if (customer.getCheckId() != null && customer.getCheckId() == 1) {
                    customer = existingCustomer;
                } else {
                    if (customer.getImage() != null) {
                        if (customer.getImage().isEmpty()) {
                            customer.setImage(existingCustomer.getImage());
                        }
                    }
                    customer = customerrRepository.save(customer);
                    customer = customerrRepository.findOne(customer.getId());
                }
            } else {
            	Merchant merchant= merchantRepository.findOne(merchantId);
                customer.setMerchantt(merchant);
                if (customer.getPassword() != null && !customer.getPassword().isEmpty()){
                	String pwsd[]=customer.getPassword().split("#");
                
                	if(pwsd!=null && pwsd.length>1&& pwsd[0].equals("@duplicatepassword")){
                		
                		customer.setPassword(pwsd[1]);
                	}else{
                		customer.setPassword(EncryptionDecryptionUtil.encryptString(customer.getPassword()));
                	}
                }
                	
                	
                    
                customer = customerrRepository.save(customer);
            }
            /*
             * if (customerAddress.get(0).getAddress2() != null && !customerAddress.get(0).getAddress2().isEmpty()) {
             * setAddress(customerAddress, merchantId, customer); }
             */
            // changed by sumit
            if ((customerAddress != null && customerAddress.size() > 0) && (customerAddress.get(0).getZip()!=null && !customerAddress.get(0).getZip().isEmpty())) {
                setAddress(customerAddress, merchantId, customer);
            }
            if (customer != null) {
                customer.setMerchantt(merchantRepository.findOne(merchantId));
                customer = customerrRepository.save(customer);
                List<Address> addresses = addressRepository.findByCustomerId(customer.getId());
                if (addresses != null && !addresses.isEmpty()) {
                    for (Address address : addresses) {
                        address.setZones(null);
                    }
                    customer.setAddresses(addresses);
                }
            }
            if (customer.getMerchantt() != null) {
                customer.getMerchantt().setItems(null);
                customer.getMerchantt().setMerchantSubscriptions(null);
                customer.getMerchantt().setModifierGroups(null);
                customer.getMerchantt().setModifiers(null);
                customer.getMerchantt().setOpeningClosingDays(null);
                customer.getMerchantt().setOrderRs(null);
                customer.getMerchantt().setOrderTypes(null);
                customer.getMerchantt().setPaymentModes(null);
                customer.getMerchantt().setTaxRates(null);
                customer.getMerchantt().setVouchars(null);
                customer.getMerchantt().setAddresses(null);
                customer.getMerchantt().setSubscription(null);
                customer.getMerchantt().setMerchantLogin(null);
                customer.getMerchantt().setSocialMediaLinks(null);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return customer;
    }

    private List<Address> setAddress(List<Address> customerAddress, Integer merchantId, Customer customer) {
        try {
            for (Address address : customerAddress) {
                Address address2 = null;
                if (address.getId() != null) {
                    address2 = addressRepository.findOne(address.getId());
                } else {
                    Merchant merchant = merchantRepository.findOwnerId(merchantId);
                    customer.setMerchantt(merchant);
                    address2 = new Address();
                    address2.setMerchant(merchant);
                    address2.setCustomer(customer);
                }
                address2.setAddress1(address.getAddress1());
                address2.setAddress2(address.getAddress2());
                address2.setAddress3(address.getAddress3());
                address2.setCity(address.getCity());
                address2.setState(address.getState());
                address2.setZip(address.getZip());
                address2.setAddressPosId(address.getAddressPosId());
                addressRepository.save(address2);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return customerAddress;
    }

    /**
     * Find by email Id
     */
    public boolean findAdminByEmail(String emailId) {
        List<Customer> customers = customerrRepository.findByEmailId(emailId);
        Customer admin=null;
        if (customers != null) {
            if (!customers.isEmpty()) {
                int status = 0;
                for (Customer cust : customers) {
                    if (cust.getPassword() != null && cust.getCustomerType()!=null && cust.getCustomerType().equals("admin")) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                            admin=cust;
                        }
                    }
                }
                if (status > 0) {
                	if(admin!=null)
                    MailSendUtil.forgotAdminPasswordEmail(admin);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean findByEmail(String emailId) {
        List<Customer> customer = customerrRepository.findByEmailId(emailId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                int status = 0;
                for (Customer cust : customer) {
                    if (cust.getPassword() != null) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                        }
                    }
                }
                if (status > 0) {
                    MailSendUtil.forgotPasswordEmail(customer.get(0));
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean findByEmailIdAndMerchantId(String emailId, Integer merchantId) {
        List<Customer> customer = customerrRepository.findByEmailIdAndMerchantId(emailId, merchantId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                int status = 0;
                for (Customer cust : customer) {
                    if (cust.getPassword() != null) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                        }
                    }
                }
                if (status > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean findByEmailIdAndVendorId(String emailId,Integer vendorId) {
        List<Customer> customer = customerrRepository.findByEmailIdAndVendorId(emailId, vendorId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                int status = 0;
                for (Customer cust : customer) {
                    if (cust.getPassword() != null) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                        }
                    }
                }
                if (status > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Find by emailId
     */
    public boolean findByEmailId(String emailId) {
        List<Customer> customer = customerrRepository.findByEmailId(emailId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                int status = 0;
                for (Customer cust : customer) {
                    if (cust.getPassword() != null) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                        }
                    }
                }
                if (status > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Find by vendorId
     */
    public List<Customer> findByVendorId(Integer merchantId) {
        List<Customer> customers = customerrRepository.findByMerchantId(merchantId);
        for (Customer customer : customers) {
            List<OrderR> orderRs = orderRepo.findByCustomerId(customer.getId());
            if (orderRs != null) {
                if (!orderRs.isEmpty()) {
                    customer.setOrderCount(orderRs.size());
                }
            }
        }
        return customers;
    }

    public double checkDelivery(Address address, Address merchantAddress) {
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
                        if (!obj.isNull("distance")) {
                            JSONObject distanceObj = obj.getJSONObject("distance");
                            distan = distanceObj.getString("text");
                            String[] arry = distan.split(" ");
                            distance = Double.parseDouble(arry[0].replace(",", ""));
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
        }

        return distance;
    }

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
        } catch (IOException e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
        }
        return responseBuilder.toString();
    }

    /**
     * Set guest customer password
     */
    
    public Customer setGuestCustomerPassword(Customer sessionCustomer) {
        Customer customer = customerrRepository.findOne(sessionCustomer.getId());
        customer.setPassword(EncryptionDecryptionUtil.encryptString(sessionCustomer.getPassword()));
        sessionCustomer.setPassword(EncryptionDecryptionUtil.encryptString(sessionCustomer.getPassword()));
        if(customer.getCustomerType()!=null && customer.getCustomerType().equals("admin")){
        	customer.setCustomerType("admin");
        }else{
        customer.setCustomerType("java");
        }
        customerrRepository.save(customer);
        return sessionCustomer;
    }

    /**
     * Find by emailId and merchantId
     */
    public boolean findByEmailAndMerchantId(String emailId, Integer merchantId) {
        List<Customer> customer = customerrRepository.findByEmailIdAndMerchantId(emailId, merchantId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                int status = 0;
                for (Customer cust : customer) {
                    if (cust.getPassword() != null) {
                        if (!cust.getPassword().isEmpty()) {
                            status++;
                        }
                    }
                }
                if (status > 0) {
                    MailSendUtil.forgotPasswordEmail(customer.get(0));
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Customer findByEmailAndCustomerId(String emailId, Integer id) {
        Customer customer = customerrRepository.findOne(id);
        if (customer != null) {
            return customer;
        } else {
            return null;
        }
    }

    public Customer findCustomerByEmailAndMerchantId(String emailId, Integer merchantId) {
        List<Customer> customer = customerrRepository.findByEmailIdAndMerchantId(emailId, merchantId);
        if (customer != null) {
            if (!customer.isEmpty()) {
                return customer.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Customer> findByEmailIDAndMerchantId(String emailId, Integer merchantId) {
        return customerrRepository.findByEmailIdAndMerchantId(emailId, merchantId);
    }

    public String findCustomerInventory(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.DESC, "id");
        Page<Customer> customers = customerrRepository.findByMerchantId(merchantId,pageable);
        List<CustomerVo> result = new ArrayList<CustomerVo>();
        for (Customer customer : customers.getContent()) {
            if(customer.getCustomerType()!=null && customer.getCustomerType().equals("clover")){
            	continue;
            }
        	CustomerVo customerVo = new CustomerVo();
            customerVo.setId(customer.getId());
            customerVo.setFirstName(customer.getFirstName());
            customerVo.setEmailId(customer.getEmailId());
            customerVo.setPhoneNumber(customer.getPhoneNumber());
            if (customer.getCreatedDate() != null) {
                customerVo.setCreatedDate(customer.getCreatedDate());
            } else {
                customerVo.setCreatedDate("");
            }
            List<OrderR> orderRs = orderRepo.findByCustomerId(customer.getId());
            if (orderRs != null) {
                if (!orderRs.isEmpty()) {
                    customerVo.setOrderCount(orderRs.size());
                    customerVo.setView("<a href=customerOrders?customerId=" + customer.getId()
                                    + " class='edit' style='color: blue'>view</a>");
                } else {
                    customerVo.setOrderCount(orderRs.size());
                    customerVo.setView("");
                }
            }
            result.add(customerVo);
        }
        result = getCustomerListBasedOnSearchParameter(searchParameter, result);
        CustomerJsonVo customerJsonVo = new CustomerJsonVo();
        customerJsonVo.setiTotalDisplayRecords((int)customers.getTotalElements());
        customerJsonVo.setiTotalRecords((int)customers.getTotalElements());
        customerJsonVo.setAaData(result);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(customerJsonVo);
    }

    private List<CustomerVo> getCustomerListBasedOnSearchParameter(String searchParameter, List<CustomerVo> result) {
        if (null != searchParameter && !searchParameter.equals("")) {
            List<CustomerVo> personsListForSearch = new ArrayList<CustomerVo>();
            searchParameter = searchParameter.toUpperCase();
            for (CustomerVo customerVo : result) {
                if (customerVo.getFirstName().toUpperCase().indexOf(searchParameter) != -1
                                || customerVo.getEmailId().toString().indexOf(searchParameter) != -1
                                || customerVo.getPhoneNumber().toString().indexOf(searchParameter) != -1) {
                    personsListForSearch.add(customerVo);
                }
            }
            result = personsListForSearch;
            personsListForSearch = null;
        }
        return result;
    }

    public String searchCustomerByTxt(Integer merchantId, String searchTxt) {
        List<Customer> customers = customerrRepository
                        .findByFirstNameLikeIgnoreCaseAndMerchantId(searchTxt, merchantId);
        List<CustomerVo> result = new ArrayList<CustomerVo>();
        for (Customer customer : customers) {
            CustomerVo customerVo = new CustomerVo();
            customerVo.setId(customer.getId());
            customerVo.setFirstName(customer.getFirstName());
            customerVo.setEmailId(customer.getEmailId());
            customerVo.setPhoneNumber(customer.getPhoneNumber());
            if (customer.getCreatedDate() != null) {
                customerVo.setCreatedDate(customer.getCreatedDate());
            } else {
                customerVo.setCreatedDate("");
            }
            List<OrderR> orderRs = orderRepo.findByCustomerId(customer.getId());
            if (orderRs != null) {
                if (!orderRs.isEmpty()) {
                    customerVo.setOrderCount(orderRs.size());
                    customerVo.setView("<a href=customerOrders?customerId=" + customer.getId()
                                    + " class='edit' style='color: blue'>view</a>");
                } else {
                    customerVo.setOrderCount(orderRs.size());
                    customerVo.setView("");
                }
            }
            result.add(customerVo);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(result);
    }

    public List<Address> findAllCustomerAddress(Integer customerId, Integer merchantId) {
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        List<Address> list = new ArrayList<Address>();
        for (Address address : addresses) {
            Address address2 = new Address();
            address2.setId(address.getId());
            address2.setAddress1(address.getAddress1());
            address2.setAddress2(address.getAddress2());
            address2.setAddress3(address.getAddress3());
            address2.setCity(address.getCity());
            address2.setCountry(address.getCountry());
            address2.setState(address.getState());
            address2.setZip(address.getZip());
            address2.setAddressPosId(address.getAddressPosId());
            list.add(address2);
        }
        return list;
    }

    /**
     * Find by customerId
     */
    public Customer findByCustomerId(Integer customerId) {
        return customerrRepository.findOne(customerId);
    }

	public List<Map<String, Object>> checkDuplicatCouponAndRecalculate(Customer customer, List<Map<String, Object>> listOfALLDiscounts) {
		List<Map<String,Object>> orderDiscountMapList = new ArrayList<Map<String,Object>>();
		String	responseKoupon =null;
		Merchant merchant =  merchantRepository.findById(customer.getMerchantId());
		customer.setMerchantUId(merchant.getMerchantUid());
		Integer vendorId = merchantRepository.findOwnerByMerchantId(customer.getMerchantId());
		Vendor vendor = vendorRepository.findOne(vendorId);
		customer.setVendorUId(vendor.getVendorUid());
		if(listOfALLDiscounts!=null){
		for(Map<String , Object> discount:listOfALLDiscounts){
        	String couponUID=(String)discount.get("couponUID");
        boolean isUseabilitySingle= (Boolean) discount.get("isUsabilitySingle");
       // boolean canApplyCoupon=true;
			if(isUseabilitySingle && customer!=null && customer.getId()!=null && couponUID!=null){
				/*OrderDiscount orderDiscount = new OrderDiscount();
				orderDiscount.setCouponCode(couponUID);*/
				CouponRedeemedDto couponRedeemed = new CouponRedeemedDto();
				couponRedeemed.setKouponCode(couponUID);
				couponRedeemed.setCustomerContactNo(customer.getPhoneNumber());
				couponRedeemed.setMerchantUId(merchant.getMerchantUid());
				couponRedeemed.setVendorUId(vendor.getVendorUid());
				
				String url = "http://localhost:8080/koupons/getCustomerDuplicateKoupon";
				Gson gson = new Gson();
				String appliedDate = null;
		    	 String couponRedeemedJson = gson.toJson(couponRedeemed);
		    	 System.out.println(couponRedeemedJson);
		    	 responseKoupon = CouponUrlUtil.getCouponData(url, couponRedeemedJson);
		    	 System.out.println("@$%@#%@#$% "+responseKoupon);
		    	 JSONObject jsonObj = new JSONObject(responseKoupon);
		    	 String responseCode= jsonObj.getString("response");
		    	 //JSONObject responseDataObj= jsonObj.getJSONObject("DATA");
		    	 JSONArray responseDataArray = jsonObj.getJSONArray("DATA");
		    	 System.out.println("responseDataArray-"+responseDataArray);
		    	 for(int i=0;i<responseDataArray.length();i++){
		    		 JSONObject kouponItemObject=responseDataArray.getJSONObject(i);
		    		appliedDate = (String) kouponItemObject.get("appliedDate");
		    		isUseabilitySingle = kouponItemObject.getBoolean("isUsabilitySingle");
		    		 System.out.println("---appliedDate---"+appliedDate+"-isUseabilitySingle-"+isUseabilitySingle);
		    		 Map<String,Object> orderDiscountMap = new HashMap<String, Object>();
						orderDiscountMap.put("couponCode", couponUID);
						orderDiscountMap.put("appliedDate",appliedDate);
						orderDiscountMapList.add(orderDiscountMap);
		    	 }
		 	 		
		 	 		/*if(responseCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE) && responseDataArray!=null && responseDataArray.toString().contains("isUsabilitySingle")){
		 	 			if(!responseDataArray.get("kouponCode").equals(null) && !responseDataArray.get("kouponCode").equals("")){
							//couponUID = (String)responseDataArray.get("kouponCode");
							appliedDate = (String)responseDataObj.get("appliedDate");
							SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
							Date d = new Date(appliedDate);
							String s =sdf.format(d);
							System.out.println(s);
							Map<String,Object> orderDiscountMap = new HashMap<String, Object>();
							orderDiscountMap.put("couponCode", couponUID);
							orderDiscountMap.put("appliedDate",appliedDate);
							orderDiscountMapList.add(orderDiscountMap);
		 	 			}
		 	 		}*/
		    	 
				//List<OrderDiscount> orderDiscounts= orderDiscountRepository.findByCustomerIdAndCouponCode(customer.getId(),couponUID);
				/*if(orderDiscounts!=null && !orderDiscounts.isEmpty() && orderDiscounts.size()>0){
					Map<String,Object> orderDiscountMap = new HashMap<String, Object>();
					orderDiscountMap.put("couponCode", orderDiscounts.get(0).getCouponCode());
					orderDiscountMap.put("appliedDate", orderDiscounts.get(0).getCouponDate());
					orderDiscountMapList.add(orderDiscountMap);
				}*/
				
			} 
        }
	}
		
		return orderDiscountMapList;
	}

	public Customer findByEmailAndPasswordAndMerchantId(String emailId,
			String password, Integer merchantId) {
		List<Customer> customers = customerrRepository.findByEmailIdAndPasswordAndMerchanttId(emailId, password,
				merchantId);
Customer customer = null;
try {
    if (customers != null) {
        if (!customers.isEmpty()) {
            customer = customers.get(0);
            if (customer != null) {
                customer.setAddresses(null);
                if (customer.getMerchantt() != null) {
                    customer.getMerchantt().setItems(null);
                    customer.getMerchantt().setMerchantSubscriptions(null);
                    customer.getMerchantt().setModifierGroups(null);
                    customer.getMerchantt().setModifiers(null);
                    customer.getMerchantt().setOpeningClosingDays(null);
                    customer.getMerchantt().setOrderRs(null);
                    customer.getMerchantt().setOrderTypes(null);
                    customer.getMerchantt().setPaymentModes(null);
                    customer.getMerchantt().setTaxRates(null);
                    customer.getMerchantt().setVouchars(null);
                    customer.getMerchantt().setAddresses(null);
                    customer.getMerchantt().setSubscription(null);
                    customer.getMerchantt().setMerchantLogin(null);
                    customer.getMerchantt().setSocialMediaLinks(null);
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
return customer;
	}

	public List<Customer> findByEmailIDAndVendorId(String emailId,
			Integer vendorId) {
		return customerrRepository.findByEmailIdAndVendorId(emailId, vendorId);
	}
}
