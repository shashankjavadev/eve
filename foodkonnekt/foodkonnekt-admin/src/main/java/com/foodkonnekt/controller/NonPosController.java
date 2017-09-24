package com.foodkonnekt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class NonPosController {
	
	 @Autowired
	    private MerchantService merchantService;
	
	 @RequestMapping(value = "/adminSignUp", method = RequestMethod.GET)
	    public String adminSignUp(HttpServletRequest request,ModelMap model) {
	        return "adminSessionTimeOut";
	    }
	 
	 @RequestMapping(value = "/adminSignin", method = RequestMethod.GET)
	    public String adminSignin(HttpServletRequest request,ModelMap model,@RequestParam(required = true)Integer merchantId) {
		 
		 Merchant merchant=merchantService.findByMerchantId(merchantId);
		 if(merchant!=null){
		  HttpSession session = request.getSession();
		  session.setAttribute("merchant", merchant);
		  return "redirect:" + UrlConstant.BASE_URL + "/uploadInventory";
		  }else{
			  return null;
		  }
	    }

}
