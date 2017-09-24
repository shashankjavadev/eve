package com.foodkonnekt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.clover.vo.AddressVO;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.ZoneService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.google.gson.Gson;

@Controller
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
    
    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/createZone", method = RequestMethod.POST)
    public @ResponseBody Zone getAllOrderData(@RequestBody Zone zone, HttpServletResponse response) {
        System.out.println("IN controller");
        return zoneService.createZone(zone);
    }

    @RequestMapping(value = "/updateZoneDetail", method = RequestMethod.POST)
    public @ResponseBody Zone updateZoneDetail(@RequestBody Zone zone, HttpServletResponse response) {
        System.out.println("In zone controller");
        return zoneService.updateZoneDetail(zone);
    }

    @RequestMapping(value = "/getAllZone", method = RequestMethod.GET)
    public @ResponseBody List<Zone> getAllZone(HttpServletResponse response) {
        System.out.println("IN controller");
        return zoneService.getAllZone();
    }

    @RequestMapping(value = "/deleteZone", method = RequestMethod.GET)
    public @ResponseBody String deleteZone(@RequestParam(required = true) Integer zoneID) {
        System.out.println("IN controller");
        return zoneService.deleteZone(zoneID);
    }

    /**
     * Check delivery zone for address
     * 
     * @param address
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkDeliveryZone", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> update(@RequestBody Address address, HttpServletResponse response) {
        Map<Object, Object> zoneMap = new HashMap<Object, Object>();
        try {
            String status = zoneService.checkAddressForDeliveryZone(address);
            if (status != null) {

                zoneMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
                zoneMap.put(IConstant.DATA, status);
            } else {
                zoneMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                zoneMap.put(IConstant.MESSAGE, IConstant.ZONE);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
             
            }
          
        }
        return zoneMap;
    }
    
    @RequestMapping(value = "/createUpdateCloverAddress", method = RequestMethod.POST)
    public @ResponseBody String createUpdateCloverAddress(@RequestBody Address newAddress, HttpServletResponse response) {
        try {
        	if(newAddress.getCustPosId()!=null){
        	Clover clover = new Clover();
        	Merchant merchant = merchantService.findById(newAddress.getMerchId());
            clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
            clover.setUrl(IConstant.CLOVER_URL);
            clover.setMerchantId(merchant.getPosMerchantId());
            clover.setAuthToken(merchant.getAccessToken());
        	Gson gson = new Gson();
            
            AddressVO address = new AddressVO();
            
            address.setAddress1(newAddress.getAddress1());
            address.setAddress2(newAddress.getAddress2());
            address.setAddress3(newAddress.getAddress3());
            address.setZip(newAddress.getZip());
            address.setState(newAddress.getState());
            address.setCity(newAddress.getCity());
            address.setCountry("US");
            address.setId(newAddress.getAddressPosId());
            
            String addressJson = gson.toJson(address);
            
            Map<Object, Object> addressMap = new HashMap<Object, Object>();
           
            	String addressResponse=CloverUrlUtil.createUpdateCustomerAddress(clover, addressJson,newAddress.getCustPosId());
                        
            	if(addressResponse.contains("id")){
            		 return addressResponse;
            	}else{
            		return null;
            	}
                
        	  
             
        	}
        	return null;
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
             
            }
            return null;
        }
        
    }
    
    
}
