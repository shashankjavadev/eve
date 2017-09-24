package com.foodkonnekt.contoller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DuplicateCustomerController {
	
	
	
	 @RequestMapping(value = "/registerDupicateCustomer", method = { RequestMethod.POST })
	 public @ResponseBody Map<Object, Object> saveDuplicateCustomer( ModelMap model,
             HttpServletRequest request, HttpServletResponse response) {
		 
		 
		 
		 
		 
		 return null;
	 }

}
