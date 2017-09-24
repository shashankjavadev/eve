package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Vendor;

public class ProducerUtil {

    /**
     * customer signin
     * 
     * @param customerJson
     * @return
     */
    public static String signIn(String customerJson) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/LoginByCustomer");
        return convertToStringJson(postRequest, customerJson);
    }

    /**
     * Hit specific url
     * 
     * @param postRequest
     * @param customerJson
     * @return json response
     */
    public static String convertToStringJson(HttpPost postRequest, String customerJson) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            StringEntity input = new StringEntity(customerJson);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);
            System.out.println("Output from Server .... \n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }
            // System.out.println(responseBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

    /**
     * Set customer from json
     * 
     * @param jCustomer
     * @return
     */
    public static Customer getCustomer(JSONObject jCustomer) {
        Customer customer = new Customer();
        Merchant merchant = new Merchant();
        customer.setId(jCustomer.getInt("id"));
        customer.setFirstName(jCustomer.getString("firstName"));
        if (!jCustomer.isNull("lastName")) {
            customer.setLastName(jCustomer.getString("lastName"));
        }
        
      
System.out.println(jCustomer.isNull("customerPosId"));
        if(jCustomer.toString().contains("customerPosId")){
        	if(!jCustomer.isNull("customerPosId")){
        	if(!jCustomer.get("customerPosId").toString().isEmpty() )
        customer.setCustomerPosId(jCustomer.getString("customerPosId"));
        	}
        }
        
        if (jCustomer.has("password")) {
            customer.setPassword(jCustomer.getString("password"));
        }
        customer.setEmailId(jCustomer.getString("emailId"));
        customer.setPhoneNumber(jCustomer.getString("phoneNumber"));
        merchant.setId(jCustomer.getJSONObject("merchantt").getInt("id"));
        if (!jCustomer.isNull("image")) {
            customer.setImage(jCustomer.getString("image"));
        }
        customer.setMerchantt(merchant);
        if (!jCustomer.isNull("birthDate")) {
            customer.setBirthDate(jCustomer.getString("birthDate"));
        }
        if (!jCustomer.isNull("anniversaryDate")) {
            customer.setAnniversaryDate(jCustomer.getString("anniversaryDate"));
        }
        if (!jCustomer.isNull("addresses")) {
            customer.setAddresses(getAddress(jCustomer.getJSONArray("addresses")));
        }
        return customer;
    }

    /**
     * Set address object
     * 
     * @param jCustomer
     * @return
     */
    public static List<Address> getAddress(JSONArray jCustomerArray) {
        List<Address> addresses = new ArrayList<Address>();
        for (Object jObj : jCustomerArray) {
            Address address = new Address();
            JSONObject jCustomer = (JSONObject) jObj;
            address.setId(jCustomer.getInt("id"));
            if (!jCustomer.isNull("address1")) {
                address.setAddress1(jCustomer.getString("address1"));
            }
            if (!jCustomer.isNull("address2")) {
                address.setAddress2(jCustomer.getString("address2"));
            }
            address.setCity(jCustomer.getString("city"));
            address.setState(jCustomer.getString("state"));
            address.setZip(jCustomer.getString("zip"));
            
            if(!jCustomer.isNull("addressPosId"))
            address.setAddressPosId(jCustomer.getString("addressPosId"));

            if (!jCustomer.isNull("deliveryFee")) {
                address.setDeliveryFee(jCustomer.getDouble("deliveryFee"));
            }
            if (!jCustomer.isNull("deliveryPosId")) {
                address.setDeliveryPosId(jCustomer.getString("deliveryPosId"));
            }
            addresses.add(address);
        }
        return addresses;
    }

    /**
     * Get customer details by customerId
     * 
     * @param customerId
     * @return
     */
    public static String getCustomerProfile(Integer customerId) {
        HttpGet request = new HttpGet(UrlConstant.BASE_URL + "/customerProfile?customerId=" + customerId);
        return convertToStringJson(request);
    }

    public static String convertToStringJson(HttpGet request) {
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
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

    public static Customer getAddress(JSONArray customerJson, Customer customer) {
        List<Address> addresses = new ArrayList<Address>();
        for (Object jObj : customerJson) {
            Address address = new Address();
            JSONObject jCustomer = (JSONObject) jObj;
            address.setId(jCustomer.getInt("id"));
            address.setAddress1(jCustomer.getString("address1"));
            address.setAddress2(jCustomer.getString("address2"));
            // address.setAddress3(jCustomer.getString("address3"));
            address.setCity(jCustomer.getString("city"));
            address.setState(jCustomer.getString("state"));
            address.setZip(jCustomer.getString("zip"));
            address.setCustomer(customer);
            addresses.add(address);
        }
        customer.setAddresses(addresses);
        return customer;
    }

    /**
     * Call update profile
     * 
     * @param customerJson
     * @return
     */
    public static String updateCustomer(String customerJson) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/updateProfile");
        return convertToStringJson(postRequest, customerJson);
    }

    /**
     * Convert address to customer
     * 
     * @param address
     * @return
     */
    public static Customer setCustomer(Address address) {
        Customer customer = new Customer();
        Merchant vendor = new Merchant();
        if (address.getCustomer().getId() != null) {
            customer.setId(address.getCustomer().getId());
        }
        customer.setFirstName(address.getCustomer().getFirstName());
        customer.setEmailId(address.getCustomer().getEmailId());
        customer.setPhoneNumber(address.getCustomer().getPhoneNumber());
        customer.setPassword(address.getCustomer().getPassword());
        customer.setCheckId(address.getCustomer().getCheckId());
        vendor.setId(address.getCustomer().getMerchantt().getId());
        customer.setMerchantt(vendor);
        customer.setAddresses(setAddress(address));
        return customer;
    }

    public static Customer setCusotmerInfo(Customer customer, Integer merchantId) {
        Customer finalCustomer = new Customer();
        Merchant merchant = new Merchant();
        if (customer.getId() != null) {
            finalCustomer.setId(customer.getId());
        }
        finalCustomer.setFirstName(customer.getFirstName());
        finalCustomer.setEmailId(customer.getEmailId());
        finalCustomer.setPhoneNumber(customer.getPhoneNumber());
        finalCustomer.setPassword(customer.getPassword());
        finalCustomer.setCheckId(customer.getCheckId());
        merchant.setId(merchantId);
        finalCustomer.setMerchantt(merchant);
        finalCustomer.setVendor(customer.getVendor());

        List<Address> addresses = new ArrayList<Address>();
        Address address = new Address();
        address.setAddress1(customer.getAddress1());
        address.setAddress2(customer.getAddress2());
        address.setCity(customer.getCity());
        address.setState(customer.getState());
        address.setZip(customer.getZip());
        addresses.add(address);

        finalCustomer.setAddresses(addresses);
        return finalCustomer;
    }

    /**
     * Set addresses
     * 
     * @param addressObj
     * @return
     */
    private static List<Address> setAddress(Address addressObj) {
        List<Address> addresses = new ArrayList<Address>();
        Address address = new Address();
        address.setAddress1(addressObj.getAddress1());
        address.setAddress2(addressObj.getAddress2());
        address.setCity(addressObj.getCity());
        address.setState(addressObj.getState());
        address.setZip(addressObj.getZip());
        addresses.add(address);
        return addresses;
    }

    public static com.foodkonnekt.model.Customer getsignUpCustomer(JSONObject jCustomer) {
        Customer customer = new Customer();
        Merchant merchant = new Merchant();
        customer.setId(jCustomer.getInt("id"));
        customer.setFirstName(jCustomer.getString("firstName"));
        customer.setEmailId(jCustomer.getString("emailId"));
        customer.setPhoneNumber(jCustomer.getString("phoneNumber"));
        customer.setPassword(jCustomer.getString("password"));
        if(jCustomer.has("customerPosId")&& !jCustomer.isNull("customerPosId")){
        customer.setCustomerPosId(jCustomer.getString("customerPosId"));
        customer.setEmailPosId(jCustomer.getString("emailPosId"));
        customer.setPhonePosId(jCustomer.getString("phonePosId"));
        }
        merchant.setId(jCustomer.getJSONObject("merchantt").getInt("id"));
        customer.setMerchantt(merchant);
        if (!jCustomer.isNull("addresses")) {
            customer.setAddresses(getAddress(jCustomer.getJSONArray("addresses")));
        }
        if (!jCustomer.isNull("image")) {
            customer.setImage(jCustomer.getString("image"));
        }
        return customer;
    }

    /**
     * Request for forgot password
     * 
     * @param customerJson
     * @return
     */
    public static String forgotPassword(String customerJson) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/forgotPasswordByEmail");
        return convertToStringJson(postRequest, customerJson);
    }

    public static Customer setAddresses(Customer customer) {
        List<Address> list = new ArrayList<Address>();
        Address address = customer.getAddresses().get(0);
        String[] city = address.getCity().split(",");
        String[] add1 = address.getAddress1().split(",");
        String[] add2 = address.getAddress2().split(",");
        int add2Length = add2.length;
        int add1Length = add1.length;
        String[] state = address.getState().split(",");
        String[] zip = address.getZip().split(",");
        for (int i = 0; i < city.length; i++) {
            Address address2 = new Address();
            if (i == 0) {
                address2.setId(address.getId());
                address2.setAddressPosId(address.getAddressPosId());
            }
            if (add1Length > i) {
                address2.setAddress1(add1[i]);
            }
            if (add2Length > i) {
                address2.setAddress2(add2[i]);
            }
            address2.setCity(city[i]);
            address2.setState(state[i]);
            address2.setZip(zip[i]);
            list.add(address2);
        }
        customer.setAddresses(list);
        return customer;
    }

    /**
     * Validate address
     * 
     * @param address
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String validateAddress(Address address) throws UnsupportedEncodingException {
        String userId = "327MKONN4730";
        String encodeUrl = "<AddressValidateRequest USERID='" + userId + "'><Address><Address1>"
                        + address.getAddress1() + " " + address.getAddress2() + "</Address1> <Address2>"
                        + "</Address2> <City>" + address.getCity() + "</City><State>" + address.getState()
                        + "</State><Zip5>" + address.getZip()
                        + "</Zip5> <Zip4></Zip4></Address></AddressValidateRequest>";
        HttpGet request = new HttpGet("http://production.shippingapis.com/ShippingAPI.dll?API=Verify&XML="
                        + URLEncoder.encode(encodeUrl, "UTF-8"));
        return convertToStringJson(request);
    }

    /**
     * Check duplicate emailId
     * 
     * @param customerJson
     * @return
     */
    public static String duplicateEmail(String customerJson, Merchant merchant,Integer vendorId) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/checkEmailId?merchantId=" + merchant.getId()+"vendorId=" + vendorId);
        return convertToStringJson(postRequest, customerJson);
    }

    /**
     * Check delivery zone
     * 
     * @param addressJson
     * @return
     */
    public static String checkDeliveryZone(String addressJson) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/checkDeliveryZone");
        return convertToStringJson(postRequest, addressJson);
    }
    
    public static String createUpdateCloverAddress(String addressJson) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/createUpdateCloverAddress");
        return convertToStringJson(postRequest, addressJson);
    }

    /**
     * POST request for place order
     * 
     * @param finalOrderJson
     * @param merchant
     * @return
     */
    public static String placeOrder(String finalOrderJson, Merchant merchant) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/postOrderOnClover?merchant_id="
                        + merchant.getPosMerchantId() + "&access_token=" + merchant.getAccessToken());
        return convertToStringJson(postRequest, finalOrderJson);
    }

    /**
     * Payment API for clover order
     * 
     * @param paymentJson
     * @param merchant
     * @return
     */
    public static String orderPayment(String paymentJson, Merchant merchant) {
        HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL + "/cloverOrderPayment?merchant_id="
                        + merchant.getPosMerchantId() + "&access_token=" + merchant.getAccessToken());
        return convertToStringJson(postRequest, paymentJson);
    }

    public static String callMeteredAPI(Integer merchantId) {
        HttpGet request = new HttpGet(UrlConstant.BASE_URL + "/addPerOrderCharge?merchantId=" + merchantId);
        return convertToStringJson(request);
    }

    public static String validateAddress1(Address address) throws UnsupportedEncodingException {
        String userId = "327MKONN4730";
        String encodeUrl = "<AddressValidateRequest USERID='" + userId + "'><Address><Address1>"
                        + address.getAddress1() + " " + address.getAddress2() + "</Address1> <Address2>"
                        + "</Address2> <City>" + address.getCity() + "</City><State>" + address.getState()
                        + "</State><Zip5>" + address.getZip()
                        + "</Zip5> <Zip4></Zip4></Address></AddressValidateRequest>";
        HttpGet request = new HttpGet("http://production.shippingapis.com/ShippingAPI.dll?API=Verify&XML="
                        + URLEncoder.encode(encodeUrl, "UTF-8"));
        return convertToStringJson(request);
    }
}
