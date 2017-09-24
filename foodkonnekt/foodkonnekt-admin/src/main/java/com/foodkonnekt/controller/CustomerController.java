package com.foodkonnekt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class CustomerController extends Thread implements Runnable {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    CloverService cloverService;

    public static Customer customerMail = null;
    public static String customerLogo = null;
    public static String merchantName = "FoodKonnekt";

    /**
     * Find by email and password
     * 
     * @param registration
     * @param response
     * @return
     */
    @RequestMapping(value = "/LoginByCustomer", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> login(@RequestBody Customer customer, HttpServletResponse response) {

        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
        	Customer result=null;
        	if(customer.getVendor()!=null && customer.getVendor().getId()!=null){
        		 result = customerService.findByEmailAndPasswordAndVendorId(customer.getEmailId(),
                         EncryptionDecryptionUtil.encryptString(customer.getPassword()), customer.getVendor().getId(),customer.getVendorId());
        	}else{
             result = customerService.findByEmailAndPasswordAndMerchantId(customer.getEmailId(),
                            EncryptionDecryptionUtil.encryptString(customer.getPassword()), customer.getVendorId());
        	}
            if (result != null) {
                Customer customerResult = customerService.getCustomerProfile(result.getId());
                loginMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
                loginMap.put(IConstant.DATA, customerResult);
            } else {
                loginMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                loginMap.put(IConstant.MESSAGE, IConstant.LOGIN_FAILURE);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return loginMap;
    }

    /**
     * Update customer profile
     * 
     * @param customer
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> update(@RequestBody Customer customer, HttpServletResponse response) {
        Map<Object, Object> updateProfileMap = new HashMap<Object, Object>();
        try {
            String password = customer.getPassword();

            Integer custId = customer.getId();
            Clover clover = new Clover();
            Customer customerResult = null;
            if (custId != null) {
                customerResult = customerService.findByCustomerId(custId);
                if (customerResult != null) {
                    customer.setCustomerPosId(customerResult.getCustomerPosId());
                    customer.setEmailPosId(customerResult.getEmailPosId());
                    customer.setPhonePosId(customerResult.getPhonePosId());
                }
            }
            /* if (custId == null) { */
            Merchant merchant = merchantService.findById(customer.getMerchantt().getId());
            if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
            clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
            clover.setUrl(IConstant.CLOVER_URL);
            clover.setMerchantId(merchant.getPosMerchantId());
            clover.setAuthToken(merchant.getAccessToken());
            String customerResponse = cloverService.createCustomer(customer, clover);
            JSONObject jObject = new JSONObject(customerResponse);
            if (customerResponse.contains("id"))
            {
                customer.setCustomerPosId(jObject.getString("id"));

            JSONObject addressesJsonObject = jObject.getJSONObject("addresses");

            JSONArray addressesJsonArray = addressesJsonObject.getJSONArray("elements");
            int index = 0;
            if (addressesJsonArray != null) {
                for (Object jObj : addressesJsonArray) {
                    JSONObject addressesJson = (JSONObject) jObj;
                    if (addressesJson.toString().contains("id")) {
                        System.out.println(addressesJson.getString("id"));
                        Address address = customer.getAddresses().get(index++);
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
                        customer.setEmailPosId(emailJson.getString("id"));
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
                        customer.setPhonePosId(phoneJson.getString("id"));
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
            customerResult = customerService.updateCustomerProfile(customer);
            customerMail = customerResult;
            customerLogo = merchantLogo;
            if (merchant.getName() != null)
                merchantName = merchant.getName();
            /*
             * }else{ customerResult = customerService.updateCustomerProfile(customer); }
             */
            if (customerResult != null) {
                updateProfileMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
                updateProfileMap.put(IConstant.DATA, customerResult);
            } else {
                updateProfileMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                updateProfileMap.put(IConstant.MESSAGE, IConstant.CUSTOMER_NOT_FOUND);
            }
            if (password != null) {
                if (!password.isEmpty()) {
                    CustomerController controller = new CustomerController();
                    if (custId == null) {
                        controller.start();
                    }
                }
            }
        } catch (Exception e) {
            if (e != null) {
            	if(customer!=null && customer.getMerchantt()!=null){
            		StringWriter errors = new StringWriter();
            		e.printStackTrace(new PrintWriter(errors));
            		MailSendUtil.sendErrorMailToAdmin(errors.toString()+customer.getMerchantt().getId()+customer.getId());
            	}else{
            		MailSendUtil.sendExceptionByMail(e);
            	}
                
            }
            e.printStackTrace();
        }

        return updateProfileMap;
    }
    
    

    /**
     * Forgot password
     * 
     * @param customer
     * @param response
     * @return
     */
    @RequestMapping(value = "/forgotPasswordByEmail", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> forgot(@RequestBody Customer customer, HttpServletResponse response) {
        Map<Object, Object> forgotMap = new HashMap<Object, Object>();
        try {
            boolean status = customerService.findByEmail(customer.getEmailId());
            if (status) {
                forgotMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
                forgotMap.put(IConstant.DATA, "Password sent to email");
            } else {
                forgotMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                forgotMap.put(IConstant.MESSAGE, IConstant.CUSTOMER_NOT_FOUND);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return forgotMap;
    }

    /**
     * Check duplicate emailId
     * 
     * @param customer
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkEmailId", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> checkEmail(@RequestBody Customer customer, HttpServletResponse response,
                    @RequestParam(required = true) Integer merchantId, @RequestParam(required = false) Integer vendorId) {
        Map<Object, Object> emailMap = new HashMap<Object, Object>();
        try {
        	boolean status =false;
        	if(vendorId !=null && vendorId>0){
        		 status = customerService.findByEmailIdAndVendorId(customer.getEmailId(), vendorId);
        	}else{
        		 status = customerService.findByEmailIdAndMerchantId(customer.getEmailId(), merchantId);
        	}
            
            if (status) {
                emailMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
                emailMap.put(IConstant.DATA, "Email already exist");
            } else {
                emailMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                emailMap.put(IConstant.MESSAGE, IConstant.CUSTOMER_NOT_FOUND);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return emailMap;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String viewModifiers(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        /*
         * try { HttpSession session = request.getSession(false); if (session != null) { Merchant merchant = (Merchant)
         * session.getAttribute("merchant"); if (merchant != null) { model.addAttribute("customers",
         * customerService.findByVendorId(merchant.getId())); } else { return "redirect:https://www.foodkonnekt.com"; }
         * } else { return "redirect:https://www.foodkonnekt.com"; } } catch (Exception e) { if (e != null) {
         * MailSendUtil.sendExceptionByMail(e); e.printStackTrace(); return "redirect:https://www.foodkonnekt.com"; } }
         */
        return "customers";
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        MailSendUtil.customerRegistartionConfirmation(customerMail, customerLogo, merchantName);
        System.out.println("Thread over");
    }

    @RequestMapping(value = "/searchCustomerByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchCustomer(HttpServletRequest request,
                    @RequestParam(required = true) String searchTxt) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = customerService.searchCustomerByTxt(merchant.getId(), searchTxt);
        }
        return searchResult;
    }
}
