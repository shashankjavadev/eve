package com.foodkonnekt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantLogin;
import com.foodkonnekt.model.Pos;
import com.foodkonnekt.model.Role;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.service.BusinessService;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.service.ItemService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;

@Controller
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    
    @Autowired
    private BusinessService businessService;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    ItemService itemService;
    private static int EXPIRY_TIME = 30 * 60 * 1000;

    /**
     * Find address by merchantId
     * 
     * @param merchantId
     * @return List<Address>
     */
    @RequestMapping(value = "/getMerchantLocationDB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<Object, Object> getAddress(@RequestParam(required = true) Integer merchantId) {
        Map<Object, Object> locationMap = new HashMap<Object, Object>();
        try{
        List<Address> addresses = merchantService.findAddressByMerchantId(merchantId);
        if (addresses != null && !addresses.isEmpty()) {
            locationMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
            locationMap.put(IConstant.DATA, addresses);
        } else {
            locationMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
            locationMap.put(IConstant.DATA, addresses);
        }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            
            e.printStackTrace();
            
            }
        }
        return locationMap;
    }

    /**
     * Add merchant logo
     * 
     * @param merchant
     * @return
     */
    @RequestMapping(value = "/updateMerchanrLogo", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> addLogo(@RequestBody Merchant merchant) {
        Map<Object, Object> logoMap = new HashMap<Object, Object>();
        try{
        Merchant result = merchantService.addMerchantLogo(merchant);
        if (result != null) {
            logoMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
            logoMap.put(IConstant.MESSAGE, "Merchant Logo add successfully");
        } else {
            logoMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
            logoMap.put(IConstant.MESSAGE, "Merchant not found");
        }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return logoMap;
    }

    /**
     * Find merchant logo by merchant Id
     * 
     * @param merchantId
     * @return Merchant
     */
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(@ModelAttribute("Merchant") Merchant merchant, ModelMap model, HttpServletRequest request,
                    @RequestParam(required = false) String saveStatus) {
        

        model.addAttribute("Merchant", new Merchant());
        return "signup";
    }
    
    @RequestMapping(value = "/saveMerchant", method = RequestMethod.POST)
    public String saveMerchant(@ModelAttribute("Merchant") Merchant merchant, ModelMap model, HttpServletRequest request,
                    @RequestParam(required = false) String saveStatus) {
    	merchant.getMerchantLogin().setPassword(EncryptionDecryptionUtil.encryptString(merchant.getMerchantLogin().getPassword()));
    	Vendor vendor= new Vendor();
    	vendor.setEmail(merchant.getMerchantLogin().getEmailId());
    	vendor.setName(merchant.getName());
    	Pos pos= new Pos();
    	Role role= new Role();
    	role.setId(3);
    	pos.setPosId(3);
    	vendor.setPos(pos);
    	vendor.setRole(role);
    	merchant.setOwner(vendor);
    	merchant.setIsInstall(1);
    	merchant.getMerchantLogin().setMerchant(merchant);
    	Merchant result=  merchantService.save(merchant);
    	businessService.saveDefaultBusinessHours(result);
    	businessService.saveDefaultPaymentMode(result);
    	HttpSession session = request.getSession();
        // String image = ImageUploadUtils.getImage(file);
        session.setAttribute("merchant",result);
        String merchId = result.getId().toString();
        String base64encode = EncryptionDecryptionUtil.encryption(merchId);
        String merchantName=result.getName().replaceAll("\\s+","");
        String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/clover/" + base64encode;
        
        session.setAttribute("onlineOrderLink", orderLink);
       
        return "uploadLogo";
    }
    
    
    
    @RequestMapping(value = "/getMerchantAndLogo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<Object, Object> getMerchantLogo(@RequestParam(required = true) Integer merchantId) {
        Map<Object, Object> merchantMap = new HashMap<Object, Object>();
        try{
        Merchant merchant = merchantService.findByMerchantId(merchantId);
        if (merchant != null) {
            merchantMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
            merchantMap.put(IConstant.DATA, merchant);
        } else {
            merchantMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
            merchantMap.put(IConstant.MESSAGE, "Merchant not found");
        } } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return merchantMap;
    }
    
    @RequestMapping(value = "/checkDuplicateMerchant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean checkDuplicateMerchant(@RequestParam(required = true) String emailId) {
       // Map<Object, Object> merchantMap = new HashMap<Object, Object>();
        try{
        MerchantLogin merchantLogin = merchantService.findByMerchantEmailId(emailId);
        if (merchantLogin != null) {
            return true;
        } else {
        	 return false;
        } } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            return false;
        }
        
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin , ModelMap model,
            HttpServletRequest request) {
        
        return "login";
    }
    
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotpassword(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin, ModelMap model,
            HttpServletRequest request) {
        
        return "forgotpassword";
    }
    
    @RequestMapping(value = "/merchantForgotPassword", method = RequestMethod.POST)
    public String merchantForgotPassword(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin, ModelMap model,
            HttpServletRequest request) {
    	try {
            boolean status = merchantService.findByEmailId(merchantLogin.getEmailId());
            if (status) {
            	model.addAttribute("response", "Please check your inbox - we have sent you an email with instructions.");
            } else {
            	model.addAttribute("response", "Email does not exist! Please sing up.");
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        
       // return "forgotpassword";
    	return "redirect:" + UrlConstant.BASE_URL + "/forgotpassword";
    }
    
    /*@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String changepassword(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin, ModelMap model,
            HttpServletRequest request) {
        
        return "changepassword";
    }*/
    
    @RequestMapping(value = "/changepassword", params = { "email", "merchantId" }, method = RequestMethod.GET)
    public String changepassword(Map<String, Object> map,ModelMap model, HttpServletRequest request,
                    @RequestParam(value = "email") String email,@RequestParam(value = "merchantId") String merchantId, @RequestParam(value = "tLog") String tLog) {

    	
        try {
            Long time = Long.valueOf(EncryptionDecryptionUtil.decryption(tLog));
            Long currentTime = System.currentTimeMillis();
            int diff = (int) (currentTime - time);

            if (diff < EXPIRY_TIME) {

                
            	MerchantLogin merchantLogin = merchantService.findByMerchantEmailId(email);
                    if (merchantLogin != null && merchantId.equalsIgnoreCase(EncryptionDecryptionUtil.encryptString(merchantLogin.getId()
                                                    + ""))) {
                    	merchantLogin.setPassword("");
                    	model.addAttribute("MerchantLogin", merchantLogin);
                        return "changepassword";
                        
                    } else {
                    	map.put("MerchantLogin", new MerchantLogin());
                        return "forgotpassword";
                    }
                
            }else {
                System.out.println("Link is expired");
                model.addAttribute("response", "Link is expired !Please try again.");
                map.put("MerchantLogin", new MerchantLogin());
                return "redirect:" + UrlConstant.BASE_URL + "/forgotpassword";
                //return "forgotpassword";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "/resetMerchantPassword", method = RequestMethod.POST)
    public String resetMerchantPassword(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin, ModelMap model,HttpServletResponse response,HttpServletRequest request) {

        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
        	MerchantLogin result = merchantService.findByMerchantEmailId(merchantLogin.getEmailId());
            if (result != null) {
            	if(result.getEmailId().equals(merchantLogin.getEmailId())){
            		if(merchantLogin.getPassword()!=null && !merchantLogin.getPassword().isEmpty()){
            		result.setPassword(EncryptionDecryptionUtil.encryptString(merchantLogin.getPassword()));
            		
            		merchantService.saveMerchantLogin(result);
            		}
            	}
            	
            	model.addAttribute("loginresponse", "Your password has been reset.");
            	return "redirect:" + UrlConstant.BASE_URL + "/login";
            } else {
                
            	model.addAttribute("loginresponse", "Invalid Emailid or Password ! Please try again");
                return "redirect:" + UrlConstant.BASE_URL + "/login";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            model.addAttribute("loginresponse", "Something went wrong there! Please try again");
            return "redirect:" + UrlConstant.BASE_URL + "/login";
        }
        
    }
    @RequestMapping(value = "/merchantLogin", method = RequestMethod.POST)
    public String merchantLogin(@ModelAttribute("MerchantLogin") MerchantLogin merchantLogin, ModelMap model,HttpServletResponse response,HttpServletRequest request) {

        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
        	MerchantLogin result = merchantService.findByEmailAndPassword(merchantLogin.getEmailId(),
                            EncryptionDecryptionUtil.encryptString(merchantLogin.getPassword()));
            if (result != null) {
            	
            	Merchant merchant = result.getMerchant();
            	
            	 HttpSession session = request.getSession();
            	 
            	 session.setAttribute("merchant", merchant);
            	  String merchId = merchant.getId().toString();
                  String base64encode = EncryptionDecryptionUtil.encryption(merchId);
                  String merchantName=merchant.getName().replaceAll("\\s+","");
                  String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/clover/" + base64encode;
                  
                  session.setAttribute("onlineOrderLink", orderLink);
            	return "redirect:" + UrlConstant.BASE_URL + "/adminHome";
            } else {
                
            	model.addAttribute("loginresponse", "Invalid Emailid or Password ! Please try again");
                return "redirect:" + UrlConstant.BASE_URL + "/login";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            model.addAttribute("loginresponse", "Something went wrong there! Please try again");
            return "redirect:" + UrlConstant.BASE_URL + "/login";
        }
        
    }
    
   
    /**
     * Get all categories     * 
     * @return Map<Object, Object>
     */
    @RequestMapping(value = "getAllItems", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> getAllItemsByMerchantUId(@RequestParam("merchantUId") String merchantUId,HttpServletResponse response) {
    	System.out.println("getAllItems");
        Map<Object, Object> allItemsMap = new HashMap<Object, Object>();
        List<Item> allItems = itemService.getAllItemsByMerchantUId(merchantUId);
        Gson gson = new Gson();
        String eventTypesJson = gson.toJson(allItems);
        if (!allItems.isEmpty()) {
        	allItemsMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
        	allItemsMap.put(IConstant.DATA, eventTypesJson);
        } else {
        	allItemsMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
        	allItemsMap.put(IConstant.DATA, eventTypesJson);
        }
        return allItemsMap;
    }   
    
    /**
     * Get all categories     * 
     * @return Map<Object, Object>
     */
    @RequestMapping(value = "getAllItemsByVendorUId", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> getAllItemsByVenderUId(@RequestParam("vendorUId") String vendorUId,HttpServletResponse response) {
    	System.out.println("getAllItems");
        Map<Object, Object> allItemsMap = new HashMap<Object, Object>();
        List<Item> allItems = itemService.getAllItemsByVenderUId(vendorUId);
        Gson gson = new Gson();
        String eventTypesJson = gson.toJson(allItems);
        if (!allItems.isEmpty()) {
        	allItemsMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
        	allItemsMap.put(IConstant.DATA, eventTypesJson);
        } else {
        	allItemsMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
        	allItemsMap.put(IConstant.DATA, eventTypesJson);
        }
        return allItemsMap;
    }   
    
    
    
}
