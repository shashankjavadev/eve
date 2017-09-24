package com.foodkonnekt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.foodtronix.service.FoodTronixService;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.FoodTronix;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class FoodTronixController {
	@Autowired
	FoodTronixService foodTronixService;
	
	 @Autowired
	    private MerchantService merchantService;

	@RequestMapping(value = "/getFoodTronixData", method = RequestMethod.POST)
	public @ResponseBody String getCloverData(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
       
		foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
		
		System.out.println("Merchant saved");
		
		foodTronixService.saveItemProperty(foodtronix,merchant);
		
		System.out.println("Item Property saved");
		
		foodTronixService.saveItemPropertyGroup(foodtronix,merchant);
		
		System.out.println("Item Property Group saved");
		
		foodTronixService.saveDishCategory(foodtronix,merchant);
		
		System.out.println("DishCatefory saved");
		
		/*foodTronixService.savePizzaTemplate(foodtronix,merchant);
		
		System.out.println("Pizza templa saved");
		
		foodTronixService.savePizzaTemplateSize(foodtronix,merchant);
		
		System.out.println("Pizza Template size saved");
		
		foodTronixService.savePizzaSize(foodtronix,merchant);
		
		System.out.println("Pizza Size saved");
		
		foodTronixService.savePizzaTopping(foodtronix,merchant);
		
		System.out.println("Pizza Topping saved");
		
		foodTronixService.savePizzaToppingSize(foodtronix,merchant);
		
		System.out.println("Pizza Toppig Size saved");*/
				if (merchant != null) {
					return IConstant.RESPONSE_SUCCESS_MESSAGE;
				} else {
					return IConstant.RESPONSE_NO_DATA_MESSAGE;
				}

	}

	
	@RequestMapping(value = "/saveItemProperty", method = RequestMethod.POST)
	public @ResponseBody void saveItemProperty(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
		foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
         
		foodTronixService.saveItemProperty(foodtronix,merchant);
	}

	@RequestMapping(value = "/saveItemPropertyGroups", method = RequestMethod.POST)
	public @ResponseBody void saveItemPropertyGroups(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
		foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        
         
		foodTronixService.saveItemPropertyGroup(foodtronix,merchant);
	}
	
	@RequestMapping(value = "/saveDishCategory", method = RequestMethod.POST)
    public @ResponseBody void saveDishCategory(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        
        foodTronixService.saveDishCategory(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/savePizzaSize", method = RequestMethod.POST)
    public @ResponseBody void savePizzaSize(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        
        foodTronixService.savePizzaSize(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/savePizzaTemplate", method = RequestMethod.POST)
    public @ResponseBody void savePizzaTemplate(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        
        foodTronixService.savePizzaTemplate(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/savePizzaTemplateSize", method = RequestMethod.POST)
    public @ResponseBody void savePizzaTemplateSize(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        
        foodTronixService.savePizzaTemplateSize(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/savePizzaTopping", method = RequestMethod.POST)
    public @ResponseBody void savePizzaTopping(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        foodTronixService.savePizzaTopping(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/savePizzaToppingSize", method = RequestMethod.POST)
    public @ResponseBody void savePizzaToppingSize(@RequestBody FoodTronix foodtronix, HttpServletResponse response) {
        System.out.println("IN controller");
        foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		Merchant merchant = foodTronixService.saveMerchant(foodtronix);
        foodTronixService.savePizzaToppingSize(foodtronix,merchant);
        System.out.println("All items are saved");
    }
	
	@RequestMapping(value = "/foodtronix", method = RequestMethod.GET)
    @ResponseBody
    public String clover(@RequestParam("authToken") String authToken,
                    @RequestParam("company_id") String companyId, @RequestParam("store_id") String storeId,HttpServletResponse response,
                    HttpServletRequest request) throws Exception {
		FoodTronix foodtronix = new FoodTronix();
		foodtronix.setAuthToken(authToken);
		foodtronix.setCompanyId(companyId);
		foodtronix.setStoreId(storeId);
		foodtronix.setUrl(IConstant.FOODTONIX_URL);
		foodtronix.setInstantUrl(IConstant.FOODTONIX_INSTANCE_URL);
		        // merchantId = "9GK2J085R8A3A";
        Merchant merchantCheck = merchantService.findbyStoreId(storeId);
        final HttpSession session = request.getSession();
        if (null != merchantCheck && merchantCheck.getIsInstall()!=null && (merchantCheck.getIsInstall() == IConstant.BOOLEAN_TRUE ||merchantCheck.getIsInstall() == 2)) {
        	
        	
            String merchId = merchantCheck.getId().toString();
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String merchantName=merchantCheck.getName().replaceAll("\\s+","");
            String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/clover/" + base64encode;
            
            session.setAttribute("merchant", merchantCheck);
            session.setAttribute("onlineOrderLink", orderLink);
           // response.sendRedirect("adminHome");
            return "adminHome";
        } else {
        	
        	Merchant merchant = foodTronixService.saveMerchant(foodtronix);
    		
    		System.out.println("Merchant saved");
    		
    		foodTronixService.saveItemProperty(foodtronix,merchant);
    		
    		System.out.println("Item Property saved");
    		
    		foodTronixService.saveItemPropertyGroup(foodtronix,merchant);
    		
    		System.out.println("Item Property Group saved");
    		
    		foodTronixService.saveDishCategory(foodtronix,merchant);
    		
    		System.out.println("DishCatefory saved");
           
            System.out.println("Done");
            String merchId = merchant.getId().toString();
            String merchantName=merchant.getName().replaceAll("\\s+","");;
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/foodtronix/" + base64encode;
            
            session.setAttribute("onlineOrderLink", orderLink);
            session.setAttribute("merchantId", merchant.getId());
            session.setAttribute("merchant", merchant);
           // response.sendRedirect("welcome");
            return "uploadLogo";
        }
    }
	
	
}
