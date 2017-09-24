package com.foodkonnekt.contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.ProducerUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;

@Controller
public class HomeController {

    @Autowired
    private MerchantService merchantService;

    private static int EXPIRY_TIME = 30 * 60 * 1000;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CustomerrRepository customerrRepository;
    
   

    
    @RequestMapping(value = "/cayanpayment", method = RequestMethod.POST)
    public String homePost(final @RequestParam("TokenHolder") String token,  ModelMap model) {

      String merchantName = "TEST";
      String merchantSiteId = "XXXXXXXX";
      String merchantKey = "XXXXX-XXXXX-XXXXX-XXXXX-XXXXX";
      String invoiceNumber = "99123";
      String amount = "1.65";
      String vaultToken = token;
      String forceDuplicate = "true";
      String registerNumber = "123";
      String merchantTransactionId = "1234";

      /*Credit c = new Credit();
      CreditSoap cs = c.getCreditSoap();
      CreditResponse4 svr = cs.saleVault(merchantName, merchantSiteId, merchantKey, invoiceNumber, amount, vaultToken, forceDuplicate, registerNumber, merchantTransactionId);

      model.addAttribute("amount", svr.getAmount());
      model.addAttribute("referenceNumber", svr.getToken());*/
      return "home";
    }
    
    @RequestMapping(value = "/PaymentForm", method = RequestMethod.GET)
    public String homePost(ModelMap model) {

      return "PaymentForm";
    }
    @RequestMapping(value = {"{merchantName}/clover/{merchantId}","{merchantName}/foodtronix/{merchantId}"}, method = RequestMethod.GET)
    public String baseUrl(ModelMap model, Map<String, Object> map,HttpServletRequest request, @PathVariable("merchantName") String merchantName,
                    @PathVariable("merchantId") String merchantId) throws IOException {
        //model.addAttribute("merchantId", merchantId);
        try {
            Integer intMerchantId = 0;
            if(merchantId!=null){
            if (merchantId.matches("[0-9]+")) {
                intMerchantId = Integer.parseInt(merchantId);
            } else {
            	if(EncryptionDecryptionUtil.decryption(merchantId).matches("[0-9]+"))
                intMerchantId = Integer.parseInt(EncryptionDecryptionUtil.decryption(merchantId));
            }

            Merchant merchant = merchantService.findById(intMerchantId);
            if (merchant == null) {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
            HttpSession session = request.getSession();
            
       		 session.removeAttribute("vendor");
       		 session.removeAttribute("merchantList");
           
            // session.setMaxInactiveInterval(2 * 60);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                if (customer.getMerchantt() != null) {
                	System.out.println(merchant.getId().intValue() != customer.getMerchantt().getId().intValue());
                    if (merchant.getId().intValue() != customer.getMerchantt().getId().intValue()) {
                    	session.removeAttribute("customer");
                    }else{
                    	//session.removeAttribute("customer");
                    	System.out.println("customer logged in");
                    }
                }
            }
            if(merchant!=null && merchant.getIsInstall()!=null && merchant.getIsInstall()!=IConstant.SOFT_DELETE){
            session.setAttribute("merchant", merchant);
            session.setMaxInactiveInterval(365*24 * 60 * 60);
            model.addAttribute("vendorId", merchant.getId());
            map.put("Address", new Address());
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
       // return "redirect:" + UrlConstant.WEB_BASE_URL + "/home";
    }

    /**
     * Show home page
     * 
     * @param merchantId
     * @param model
     * @return
     * @throws IOException
     */
    /*@RequestMapping(value = "home", method = RequestMethod.GET)
    public String showReiProfilePage(ModelMap model, Map<String, Object> map, HttpServletRequest request,
                    HttpServletResponse response, @RequestParam(required = false) String merchantId) throws IOException {
        try {
            Integer intMerchantId = 0;
            if(merchantId!=null){
            if (merchantId.matches("[0-9]+")) {
                intMerchantId = Integer.parseInt(merchantId);
            } else {
            	if(EncryptionDecryptionUtil.decryption(merchantId).matches("[0-9]+"))
                intMerchantId = Integer.parseInt(EncryptionDecryptionUtil.decryption(merchantId));
            }

            Merchant merchant = merchantService.findById(intMerchantId);
            if (merchant == null) {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
            HttpSession session = request.getSession();
            
       		 session.removeAttribute("vendor");
       		 session.removeAttribute("merchantList");
           
            // session.setMaxInactiveInterval(2 * 60);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                if (customer.getMerchantt() != null) {
                	System.out.println(merchant.getId().intValue() != customer.getMerchantt().getId().intValue());
                    if (merchant.getId().intValue() != customer.getMerchantt().getId().intValue()) {
                    	session.removeAttribute("customer");
                    }else{
                    	//session.removeAttribute("customer");
                    	System.out.println("customer logged in");
                    }
                }
            }
            if(merchant!=null && merchant.getIsInstall()!=null && merchant.getIsInstall()!=IConstant.SOFT_DELETE){
            session.setAttribute("merchant", merchant);
            session.setMaxInactiveInterval(24 * 60 * 60);
            model.addAttribute("vendorId", merchant.getId());
            map.put("Address", new Address());
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }*/

    
//    multilocation changes start
    
    @RequestMapping(value="sendLocation", method = RequestMethod.GET)
    @ResponseBody
    public String sendLocation( @RequestParam("merchantId") String merchantId,@RequestParam("merchantName") String merchantName, ModelMap model, HttpServletRequest request){
    	  HttpSession session = request.getSession();
    	 Vendor vendor = (Vendor) session.getAttribute("vendor");
    	  String vendorId = EncryptionDecryptionUtil.encryption(Integer.toString(vendor.getId()));
    	  String merchId = EncryptionDecryptionUtil.encryption(merchantId);
    	  
    	 return  UrlConstant.WEB_BASE_URL + "/vendor/"+ vendorId + "/" + merchantName+ "/clover/" +merchId ;
    }
    
    
    @RequestMapping(value = {"vendor/{vendoreId}/{merchantName}/clover/{merchantId}","{merchantName}/foodtronix/{merchantId}"}, method = RequestMethod.GET)
    public String baseUrlForMultiLocation(ModelMap model, Map<String, Object> map, @PathVariable("vendoreId") String vendoreId,@PathVariable("merchantName") String merchantName,
                    @PathVariable("merchantId") String merchantId) throws IOException {
        model.addAttribute("merchantId", merchantId);
        model.addAttribute("vendoreId", vendoreId);
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/homeForMultiLocation";
    }

    /**
     * Show home page
     * 
     * @param merchantId
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "homeForMultiLocation", method = RequestMethod.GET)
    public String showReiProfilePageForMultiLocation(ModelMap model, Map<String, Object> map, HttpServletRequest request,
                    HttpServletResponse response, @RequestParam(required = false) String merchantId, @RequestParam(required = false) String vendoreId) throws IOException {
        try {
            Integer intMerchantId = 0;
            Integer intVendorId = 0;
            if(merchantId!=null){
            if (merchantId.matches("[0-9]+")) {
                intMerchantId = Integer.parseInt(merchantId);
            } else {
            	if(EncryptionDecryptionUtil.decryption(merchantId).matches("[0-9]+"))
                intMerchantId = Integer.parseInt(EncryptionDecryptionUtil.decryption(merchantId));
            }
            
            
            HttpSession session = request.getSession();
            List<Merchant> merchantList = new ArrayList<Merchant>();
            Vendor vendor = new Vendor();
            if(vendoreId!= null){
            	
            	 if (vendoreId.matches("[0-9]+")) {
            		 intVendorId = Integer.parseInt(vendoreId);
                 } else {
                 	if(EncryptionDecryptionUtil.decryption(vendoreId).matches("[0-9]+"))
                 		intVendorId = Integer.parseInt(EncryptionDecryptionUtil.decryption(vendoreId));
                 }
            	 vendor = merchantService.findVendorById(intVendorId);
            	 merchantList= merchantService.findAllMerchantsByVendorId(intVendorId);
            	 
            	 if(vendor!= null){
            		 session.setAttribute("vendor", vendor);
            	}
            	 
            		 session.setAttribute("merchantList", merchantList);
            	}
            
            
            
            
            Merchant merchant = merchantService.findById(intMerchantId);
            if (merchant == null) {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
           
            // session.setMaxInactiveInterval(2 * 60);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                if (customer.getMerchantt() != null) {
                	Merchant customerMerchant = merchantService.findById(customer.getMerchantt().getId());
                	System.out.println(merchant.getOwner().getId().intValue() != customerMerchant.getOwner().getId().intValue());
                    if (merchant.getOwner().getId().intValue() != customerMerchant.getOwner().getId().intValue()) {
                    	session.removeAttribute("customer");
                    }else{
                    	if(customerMerchant.getId()==merchant.getId()){
                    	customer.setMerchantt(merchant);
                    	session.setAttribute("customer",customer);
                    	System.out.println("customer logged in");
                    	}else{
                    		customer.setVendor(null);
                    		customer.setVendorId(merchant.getId());
                    		
                    		
                    		
                    		List<Customer> customers = customerrRepository.findByEmailIdAndPasswordAndMerchanttId(customer.getEmailId(), customer.getPassword(),merchant.getId());
                             if (customers!=null && !customers.isEmpty()) {
                                 /*JSONObject Customer = jObject.getJSONObject("DATA");
                                 Customer customerResult = ProducerUtil.getCustomer(Customer);*/
                                 
                                 session.setAttribute("customer", customers.get(0));
                                 
                             }else{
                            	 session.setAttribute("merchant", merchant);
                            	 saveDuplicateCustomer(request, response);
                             }
                    		
                    	}
                    }
                }
            }
            
            
            model.addAttribute("merchantList", merchantList);
            
            if(merchant!=null && merchant.getIsInstall()!=null && merchant.getIsInstall()!=IConstant.SOFT_DELETE){
            session.setAttribute("merchant", merchant);
            session.setMaxInactiveInterval(24 * 60 * 60);
            model.addAttribute("vendorId", merchant.getId());
            map.put("Address", new Address());
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
            }else{
            	return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }
    
    
    
    /**
     * Customer login
     * 
     * @param customer
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customerSignIn", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> customerSignInByAjax(@RequestBody final Customer customer,
                    HttpServletRequest request) throws Exception {
        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Vendor vendor = (Vendor) session.getAttribute("vendor");
            Gson gson = new Gson();
            if(vendor!=null && vendor.getId()!=null){
            	customer.setVendor(vendor);
            }
            String customerJson = gson.toJson(customer);
            String result = ProducerUtil.signIn(customerJson);
            JSONObject jObject = new JSONObject(result);
            if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                JSONObject Customer = jObject.getJSONObject("DATA");
                Customer customerResult = ProducerUtil.getCustomer(Customer);
                customerResult.setOrderType(customer.getOrderType());
                session.setAttribute("customer", customerResult);
                customer.setId(customerResult.getId());
                customer.setPhoneNumber(customerResult.getPhoneNumber());
                customer.setMerchantId(merchant.getId());
                customer.setVendor(vendor);
                
                System.out.println("phone------>"+customer.getPhoneNumber());
                List<Map<String,Object>> duplicateCouponMapList = new ArrayList<Map<String,Object>>();
                // Map<Object, Object> duplicate
                duplicateCouponMapList = customerService.checkDuplicatCouponAndRecalculate(customer, customer.getListOfALLDiscounts());
               System.out.println("---------------------------");
                for(int i=0; i<duplicateCouponMapList.size();i++)
                {
                	System.out.println(duplicateCouponMapList.get(i));
                }
                if (customerResult.getPassword() != null && !customerResult.getPassword().isEmpty())
                    loginMap.put("customerPswd", "Y");
                else
                    loginMap.put("customerPswd", "N");

                loginMap.put("success", "Login successfully");
                loginMap.put("duplicateCouponMapList", duplicateCouponMapList);
                loginMap.put("address", customerResult.getAddresses());
                loginMap.put("custId", customerResult.getId());
                
            } else {
                loginMap.put("success", "Invalid login");
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
     * customer registration
     * 
     * @param address
     * @param result
     * @param model
     * @param request
     * @param response
     * @return Map<Object, Object>
     */
    @RequestMapping(value = "/registerCustomer", method = { RequestMethod.POST })
    public @ResponseBody Map<Object, Object> saveCustomer(@RequestBody final Customer customer, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> duplicateEmail = new HashMap<Object, Object>();
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Vendor vendor = (Vendor) session.getAttribute("vendor");
            if (merchant != null) {
            	customer.setMerchantId(merchant.getId());
            	 if(vendor!=null && vendor.getId()!=null){
                 	customer.setVendor(vendor);
                 }
                Customer finalCustomer = ProducerUtil.setCusotmerInfo(customer, merchant.getId());
                
               
                Gson gson = new Gson();
                String customerJson = gson.toJson(finalCustomer);
                String result = ProducerUtil.updateCustomer(customerJson);
                if(result!=null && result.contains("response")){
                JSONObject jObject = new JSONObject(result);
                if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                    JSONObject Customer = jObject.getJSONObject("DATA");
                    Customer customerResult = ProducerUtil.getsignUpCustomer(Customer);
                   
                    customerResult.setOrderType(customer.getOrderType());
                    System.out.println("customer-order type--"+customer.getOrderType());
                    System.out.println("customerResult-order type--"+customerResult.getOrderType());
                    customer.setId(customerResult.getId());
                    customer.setPhoneNumber(customerResult.getPhoneNumber());
                    System.out.println("phone------>"+customer.getPhoneNumber()+" id--"+customer.getId()+" list of disc-"+customer.getListOfALLDiscounts());
                    List<Map<String,Object>> duplicateCouponMapList = new ArrayList<Map<String,Object>>();
                    // Map<Object, Object> duplicate
                    duplicateCouponMapList = customerService.checkDuplicatCouponAndRecalculate(customer, customer.getListOfALLDiscounts());
                    
                    System.out.println("---------------------------");
                    for(int i=0; i<duplicateCouponMapList.size();i++)
                    {
                    	System.out.println(duplicateCouponMapList.get(i));
                    }
                    
                    session.setAttribute("customer", customerResult);
                    duplicateEmail.put("message", "successfully register");
                    duplicateEmail.put("customerAfterSuccessId", customerResult.getId());
                }} else {
                    duplicateEmail.put("message", "Please try again");
                }
            } else {
                duplicateEmail.put("message", "timeIssue");
                response.sendRedirect("sessionTimeOut");
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return duplicateEmail;
    }
    
   
	 public String saveDuplicateCustomer( HttpServletRequest request, HttpServletResponse response) {
		 
    	 Map<Object, Object> duplicateEmail = new HashMap<Object, Object>();
         try {
		 
        	 HttpSession session = request.getSession();
        	 Merchant merchant = (Merchant) session.getAttribute("merchant");
        	 Customer customer = (Customer) session.getAttribute("customer");
        	 
        	 
        	 if (merchant != null && customer!= null) {
        		 
        		 if(merchant.getId() != customer.getMerchantt().getId()){
        			 
        			 
        		customer.setId(null);	 
        		 customer.setMerchantId(merchant.getId());
        		 
        		 if(customer.getVendor()== null){
        			 customer.setVendor(merchant.getOwner());
        		 }
             	 
                  
                 Customer finalCustomer = ProducerUtil.setCusotmerInfo(customer, merchant.getId());
                 
                 if(finalCustomer.getPassword()!= null){
                	 finalCustomer.setPassword("@duplicatepassword#"+finalCustomer.getPassword());
                 }
                 System.out.println(finalCustomer.getPassword());
                
                 Gson gson = new Gson();
                 String customerJson = gson.toJson(finalCustomer);
                 String result = ProducerUtil.updateCustomer(customerJson);
                 if(result!=null && result.contains("response")){
                 JSONObject jObject = new JSONObject(result);
                 if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                     JSONObject Customer = jObject.getJSONObject("DATA");
                     Customer customerResult = ProducerUtil.getsignUpCustomer(Customer);
                    
                     customerResult.setOrderType(customer.getOrderType());
                     System.out.println("customer-order type--"+customer.getOrderType());
                     System.out.println("customerResult-order type--"+customerResult.getOrderType());
                     customer.setId(customerResult.getId());
                     customer.setPhoneNumber(customerResult.getPhoneNumber());
                     System.out.println("phone------>"+customer.getPhoneNumber()+" id--"+customer.getId()+" list of disc-"+customer.getListOfALLDiscounts());
                     List<Map<String,Object>> duplicateCouponMapList = new ArrayList<Map<String,Object>>();
                     // Map<Object, Object> duplicate
                     duplicateCouponMapList = customerService.checkDuplicatCouponAndRecalculate(customer, customer.getListOfALLDiscounts());
                     
                     System.out.println("---------------------------");
                     for(int i=0; i<duplicateCouponMapList.size();i++)
                     {
                     	System.out.println(duplicateCouponMapList.get(i));
                     }
                     
                     session.setAttribute("customer", customerResult);
                     duplicateEmail.put("message", "successfully register");
                     duplicateEmail.put("customerAfterSuccessId", customerResult.getId());
                 }} else {
                     duplicateEmail.put("message", "Please try again");
                 }
             } else{
            	 
            	 session.setAttribute("customer",customer);
             }
        	}else {
                 duplicateEmail.put("message", "timeIssue");
                 response.sendRedirect("sessionTimeOut");
             }
        	 
        	 
        	 
        	 
        	 
		 
		 
         } catch (Exception e) {
             if (e != null) {
                 MailSendUtil.sendExceptionByMail(e);
             }
             e.printStackTrace();
         }
         return "success";
	 }
    
    
    
    

    /**
     * customer signIn
     * 
     * @param address
     * @param model
     * @param request
     * @param response
    * @return
     */
    @RequestMapping(value = "/signIn", method = { RequestMethod.POST })
    public String customerSignInByFormSubmit(@ModelAttribute("Address") Address address, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }

    /**
     * Customer logout.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        HttpSession session = request.getSession();
        if (session != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
            	Vendor vendor = (Vendor) session.getAttribute("vendor");
            	
                model.addAttribute("merchantId", merchant.getId());
                //session.invalidate();
                session.removeAttribute("customer");
                if(vendor!= null){
                	if(vendor.getId()!=null){
                	        model.addAttribute("vendoreId", vendor.getId());
                	        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                	}
               	}
            }
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }

    /**
     * Show profile page
     * 
     * @param model
     * @param map
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(ModelMap model, Map<String, Object> map, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (customer != null) {
                map.put("Customer", new Customer());
                model.addAttribute("Customer", customer);
                model.addAttribute("customerAddresses",
                                customerService.findAllCustomerAddress(customer.getId(), merchant.getId()));
                return "profile";
            } else {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "profile";
    }

    /**
     * Update customer profile and address
     * 
     * @param customer
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateCustomerProfile", method = { RequestMethod.POST })
    public String updateCustomer(@ModelAttribute("Customer") Customer customer, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Gson gson = new Gson();
            customer = ProducerUtil.setAddresses(customer);
            String customerJson = gson.toJson(customer);
            String result = ProducerUtil.updateCustomer(customerJson);
            JSONObject jObject = new JSONObject(result);
            if (result.contains("response")&&jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                JSONObject Customer = jObject.getJSONObject("DATA");
                Customer customerResult = ProducerUtil.getCustomer(Customer);
                session.setAttribute("customer", customerResult);
                model.addAttribute("Customer", (Customer) session.getAttribute("customer"));
                model.addAttribute("customerAddresses",
                                customerService.findAllCustomerAddress(customer.getId(), merchant.getId()));
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "profile";
    }

    /**
     * Show forgot password page
     * 
     * @param model
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String showForgot(ModelMap model, Map<String, Object> map, HttpServletRequest request) {
        map.put("Customer", new Customer());
        return "forgotPassword";
    }

    /**
     * Send password to email
     * 
     * @param customer
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendPassword", method = { RequestMethod.POST })
    public String sendPassword(@ModelAttribute("Customer") Customer customer, ModelMap model, Map<String, Object> map,
                    HttpServletRequest request, HttpServletResponse response) {
        try {
            Gson gson = new Gson();
            String customerJson = gson.toJson(customer);
            String result = ProducerUtil.forgotPassword(customerJson);
            JSONObject jObject = new JSONObject(result);
            if (jObject.has("response")) {
                if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                    model.addAttribute("message",
                                    "Please check your inbox - we have sent you an email with instructions");
                } else {
                    model.addAttribute("message",
                                    "Please check your inbox - we have sent you an email with instructions");
                }
            }
            map.put("Customer", new Customer());
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "forgotPassword";
    }

    @RequestMapping(value = "/forgotPasswordAction", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> forgotPasswordAction(@RequestBody final Customer customer,
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Object, Object> forgotPasswordMap = new HashMap<Object, Object>();
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                boolean status = customerService.findByEmailAndMerchantId(customer.getEmailId(), merchant.getId());
                if (status) {
                    forgotPasswordMap.put("message",
                                    "Please check your inbox - we have sent you an email with instructions");
                } else {
                    forgotPasswordMap
                                    .put("message", "An account with the above email does not exist. Please try again");
                }
            } else {
                response.sendRedirect("sessionTimeOut");
            }
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return forgotPasswordMap;
    }

    @RequestMapping(value = "/checkOldPassword", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> checkPassword(@RequestBody final Customer customer,
                    HttpServletRequest request) throws Exception {
        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
            HttpSession session = request.getSession();
            Customer customer2 = (Customer) session.getAttribute("customer");
            Customer result = customerService.findByEmailAndPasswordAndMerchantId(customer2.getEmailId(),
                            EncryptionDecryptionUtil.encryptString(customer.getPassword()), customer2.getMerchantt()
                                            .getId());
            if (result != null) {
                loginMap.put("success", "samePassword");
            } else {
                loginMap.put("success", "Your old password is not match , please enter correct password");
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
     * Check duplicate emailId
     * 
     * @param customer
     * @param model
     * @param map
     * @param request
     * @param response
     * @return message
     */
    @RequestMapping(value = "/checkDuplicateEmailId", method = { RequestMethod.POST })
    public @ResponseBody Map<Object, Object> checkEmailId(@RequestBody final Customer customer, ModelMap model,
                    Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> duplicateEmail = new HashMap<Object, Object>();
        try {
            Gson gson = new Gson();
            HttpSession session = request.getSession();
            if(session!=null){
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if(merchant!=null){
            	 Vendor vendor = (Vendor) session.getAttribute("vendor");
            	 Integer vendorId=0;
            	 if(vendor!=null && vendor.getId()!=null){
            		 vendorId=vendor.getId();
            		 
            	 }
            String customerJson = gson.toJson(customer);
            String result = ProducerUtil.duplicateEmail(customerJson, merchant,vendorId);
            if(result!=null &&result.startsWith("{") && result.endsWith("{")&& result.contains("{")){
            JSONObject jObject = new JSONObject(result);
            if (jObject.getString("response").equals(IConstant.RESPONSE_SUCCESS_MESSAGE)) {
                duplicateEmail.put("message", "true");
            } else {
                duplicateEmail.put("message", "false");
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
        return duplicateEmail;
    }

    /**
     * Show guest password screen
     * 
     * @param model
     * @param map
     * @param request
     * @return String
     */
    @RequestMapping(value = "/setGuestPassword", method = RequestMethod.GET)
    public String setGuestPassword(ModelMap model, Map<String, Object> map, HttpServletRequest request) {
        map.put("Customer", new Customer());
        return "setGuestPassword";
    }

    @RequestMapping(value = "/changepassword", params = { "email", "merchantId", "userId" }, method = RequestMethod.GET)
    public String setResetPassword(Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(value = "email") String email, @RequestParam(value = "merchantId") String merchantId,
                    @RequestParam(value = "userId") String userId, @RequestParam(value = "tLog") String tLog) {

        try {
            Long time = Long.valueOf(EncryptionDecryptionUtil.decryption(tLog));
            Long currentTime = System.currentTimeMillis();
            int diff = (int) (currentTime - time);

            if (diff < EXPIRY_TIME) {

                if (merchantId != null) {
                    String id = merchantId;
                    Customer customer = customerService.findCustomerByEmailAndMerchantId(email, Integer.valueOf(id));
                    if (customer != null
                                    && userId.equalsIgnoreCase(EncryptionDecryptionUtil.encryptString(customer.getId()
                                                    + ""))) {
                        customer.setPassword("");
                        map.put("Customer", customer);
                        return "resetGuestPassword";
                    } else {
                        return "redirec"
                        		+ "t:https://www.foodkonnekt.com";
                    }
                }
            } else {
                System.out.println("Link is expired");
                map.put("Customer", new Customer());
                return "forgotPassword";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/resetPhpCustomerPassword", params = { "email", "merchantId" }, method = RequestMethod.GET)
    public String resetPhpCustomerPassword(Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(value = "email") String email, @RequestParam(value = "merchantId") Integer merchantId) {

        try {
            Customer customer = customerService.findCustomerByEmailAndMerchantId(email, merchantId);
            if (customer != null) {
                customer.setPassword("");
                map.put("Customer", customer);
                map.put("phpcustomerErrorMsg", "Your Password Has Expired. Please Reset");
                return "resetGuestPassword";
            } else {
                return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "resetGuestPassword";
    }

    @RequestMapping(value = "/saveResetGuestPassword", method = RequestMethod.POST)
    public String saveResetGuestPassword(@ModelAttribute("Customer") Customer customer, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        try {
            customerService.setGuestCustomerPassword(customer);
            Customer customerInfo = customerService.findByEmailAndCustomerId("", customer.getId());
            System.out.println("marchant ID is" + customerInfo.getMerchantt().getId());

            HttpSession session = request.getSession();
            session.setAttribute("customer", customerInfo);
            String merchantName = customerInfo.getMerchantt().getName();
            merchantName = merchantName.replaceAll("\\s+", "");
            return "redirect:" + UrlConstant.WEB_BASE_URL + "/" + merchantName + "/clover/"
                            + EncryptionDecryptionUtil.encryption(String.valueOf(customerInfo.getMerchantt().getId()));
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:https://www.foodkonnekt.com";
    }

    /**
     * Save guest customer password
     * 
     * @param customer
     * @param model
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping(value = "/saveGuestPassword", method = RequestMethod.POST)
    public String saveGuestPassword(@ModelAttribute("Customer") Customer customer, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            if(session!=null){
            Customer sessionCustomer = (Customer) session.getAttribute("customer");
            if(sessionCustomer!=null){
            sessionCustomer.setPassword(customer.getPassword());
            sessionCustomer = customerService.setGuestCustomerPassword(sessionCustomer);
            session.setAttribute("customer", sessionCustomer);
               }else{
            	   return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
               }
            }else{
            	return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/myOrder";
    }

    /**
     * Cancel account request by guest customer
     * 
     * @param model
     * @param map
     * @param request
     * @return String
     */
    @RequestMapping(value = "/cancelAccountRequest", method = RequestMethod.GET)
    public String cancelAccountRequest(ModelMap model, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("customer");
        // session.invalidate();
        // return "redirect:https://www.foodkonnekt.com";
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }

    /**
     * Cancel account request by guest customer
     * 
     * @param model
     * @param map
     * @param request
     * @return String
     */
    @RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
    public String sessionTimeOut(ModelMap model) {
        return "sessionTimeOut";
    }
}
