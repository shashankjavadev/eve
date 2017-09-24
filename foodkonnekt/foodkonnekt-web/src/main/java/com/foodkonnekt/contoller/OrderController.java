package com.foodkonnekt.contoller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.clover.vo.PaymentVO;
import com.foodkonnekt.clover.vo.PlaceOrderVO;
//import com.foodkonnekt.clover.vo.PlaceOrderVO;
import com.foodkonnekt.model.CategoryDto;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.FulFilledOrder;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroupDto;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.OrderType;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.service.BusinessService;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.OrderService;
import com.foodkonnekt.service.VoucharService;
import com.foodkonnekt.service.ZoneService;
import com.foodkonnekt.serviceImpl.OrderServiceImpl;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.OrderUtil;
import com.foodkonnekt.util.ProducerUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private VoucharService voucharService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MerchantService merchantService;

    /**
     * Show place order page
     * 
     * @param model
     * @param map
     * @return
     */
   /* @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String showOrderPage(@ModelAttribute("PaymentVO") PaymentVO paymentVO, ModelMap model,
                    Map<String, Object> map, HttpServletRequest request, @RequestParam(required = false) String message) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Customer sessionCustomer = (Customer) session.getAttribute("customer");
        if (merchant != null) {
            try {
            	
            	merchant=merchantService.findById(merchant.getId());
             
            	String timeZoneCode="America/Chicago";
            	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getTimeZoneCode()!=null){
            		hourDifference=merchant.getTimeZone().getHourDifference();
            		if(merchant.getTimeZone().getMinutDifference()!=null)
            		minutDifference=merchant.getTimeZone().getMinutDifference();
            		timeZoneCode=merchant.getTimeZone().getTimeZoneCode();
            	}
            	session.setAttribute("merchant",merchant);
                List<CategoryDto> categories = orderService.findCategoriesByMerchantId(merchant);
                if(categories!=null && !categories.isEmpty()){
                String avgDeliveryTime=null;
                String avgPickUpTime=null;
                PickUpTime pickUpTime=merchantService.findPickupTime(merchant.getId());
                if(pickUpTime!=null && pickUpTime.getPickUpTime()!=null){
                	avgPickUpTime=pickUpTime.getPickUpTime();
                }
                
                //if (categories!=null && !categories.isEmpty()) {
                    List<Zone> zones = zoneService.findZoneByMerchantIdAndStatus(merchant.getId());
                    if (zones != null) {
                        if (!zones.isEmpty() && zones.size()>0) {
                            model.addAttribute("zoneStatus", "Y");
                            avgDeliveryTime=zones.get(0).getAvgDeliveryTime();
                        } else {
                            model.addAttribute("zoneStatus", "N");
                        }
                    }
                    
                    Calendar deliveryAvgTimeCalendar = Calendar.getInstance();
                    Calendar pickUpAvgTimeCalendar = Calendar.getInstance();
                    int time=30;
                    try{
                    if(avgDeliveryTime!=null){
                    java.util.Date deliveryAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgDeliveryTime+":00");
                    deliveryAvgTimeCalendar.setTime(deliveryAvgTime);
                    }if(avgPickUpTime!=null){
                        java.util.Date pickUpAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgPickUpTime+":00");
                        pickUpAvgTimeCalendar.setTime(pickUpAvgTime);
                        }
                    
                    if(avgDeliveryTime!=null && avgPickUpTime!=null){
                    	if(pickUpAvgTimeCalendar.getTimeInMillis()>deliveryAvgTimeCalendar.getTimeInMillis()){
                    		time=pickUpAvgTimeCalendar.getTime().getMinutes();
                    	}else{
                    		time=deliveryAvgTimeCalendar.getTime().getMinutes();
                    	}
                                     	
                    }else if(avgDeliveryTime!=null){
                    	time=deliveryAvgTimeCalendar.getTime().getMinutes();
                    }else if(avgPickUpTime!=null){
                    	time=pickUpAvgTimeCalendar.getTime().getMinutes();
                    }
                    }catch(Exception e){
                    	
                    }
                    System.out.println(time);
                    List<OpeningClosingTime> openingClosingTimes = voucharService
                                    .findOpeningClosingHoursByMerchantId(merchant.getId(),timeZoneCode);
                    if (openingClosingTimes != null) {
                        if (!openingClosingTimes.isEmpty()) {
                            model.addAttribute("closingDayStatus", "Y");
                            model.addAttribute("openingClosingStatus",
                                            OrderUtil.checkOpeningClosingHour(openingClosingTimes,time,timeZoneCode));
                            model.addAttribute("openingClosingHours", OrderUtil.findOperatingHour(openingClosingTimes));
                        } else {
                            model.addAttribute("closingDayStatus", "N");
                        }
                    } else {
                        model.addAttribute("closingDayStatus", "N");
                        model.addAttribute("openingClosingStatus", "N");
                    }
                    if (sessionCustomer != null) {
                        model.addAttribute("sessionCustId", sessionCustomer.getId());
                        if (sessionCustomer.getPassword() != null) {
                            if (sessionCustomer.getPassword().isEmpty()) {
                                model.addAttribute("setPassword", "Y");
                            }
                        } else {
                            model.addAttribute("setPassword", "Y");
                        }
                        Gson gson = new Gson();
                        String jsonInString = gson.toJson(orderService.findAddessByCustomerId(sessionCustomer.getId()));
                        model.addAttribute("address", jsonInString);
                    } else {
                        model.addAttribute("address", "NoData");
                    }
                    ConvenienceFee convenienceFee = businessService.findConvenienceFeeByMerchantId(merchant.getId());
                    if (convenienceFee != null) {
                        if (Double.valueOf(convenienceFee.getConvenienceFee()) > 0) {
                            if (convenienceFee.getIsTaxable() != null) {
                                if (convenienceFee.getIsTaxable() == 1) {
                                    model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                                    model.addAttribute("convenienceFeePosId",
                                                    convenienceFee.getConvenienceFeeLineItemPosId());
                                    model.addAttribute(
                                                    "convenienceFeeTax",
                                                    orderService.findConvenienceFeeAfterTax(
                                                                    convenienceFee.getConvenienceFee(),
                                                                    merchant.getId()));
                                    model.addAttribute(
                                                    "convenienceFeeTaxWithComma",
                                                    orderService.findConvenienceFeeAfterMultiTax(
                                                                    convenienceFee.getConvenienceFee(),
                                                                    merchant.getId()));
                                    model.addAttribute("convenienceFeePrice", convenienceFee.getConvenienceFee());
                                }
                            } else {
                                model.addAttribute("convenienceFeeTax", 0);
                                model.addAttribute("convenienceFeePosId",
                                                convenienceFee.getConvenienceFeeLineItemPosId());
                                model.addAttribute("convenienceFeePrice", convenienceFee.getConvenienceFee());
                                model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                            }
                        } else {
                            model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_FALSE);
                        }
                    }
                    System.out.println("AllowFutureOrder --> " + merchant.getAllowFutureOrder());
                    model.addAttribute("allowFutureOrder", merchant.getAllowFutureOrder());
                    // model.addAttribute("allowFutureOrder", 1);
                    if (merchant.getAllowFutureOrder() != null
                                    && IConstant.BOOLEAN_TRUE == merchant.getAllowFutureOrder()) {
                        model.addAttribute("futureOrderDates", DateUtil.find10FutureDates());
                    }
                    model.addAttribute("paymentModes", orderService.findPaymentMode(merchant.getId()));
                    model.addAttribute("categories", categories);
                    model.addAttribute("message", message);
                    model.addAttribute("merchantId", merchant.getId());
                    model.addAttribute("merchantTaxs", orderService.findMerchantTaxs(merchant.getId()));
                    
                }else{
                	return "categoryEmpty";
                	
                }
            } catch (Exception exception) {
                if (exception != null) {
                    MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }
            return "order";
        } else {
            return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
        }
    }*/

    
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String showOrderPage(@ModelAttribute("PaymentVO") PaymentVO paymentVO, ModelMap model,
                    Map<String, Object> map, HttpServletRequest request, @RequestParam(required = false) String message) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Customer sessionCustomer = (Customer) session.getAttribute("customer");
        if (merchant != null) {
            try {
            	
            	merchant=merchantService.findById(merchant.getId());
             
            	
            	session.setAttribute("merchant",merchant);
            	
            	// DB-----------0
                List<CategoryDto> categories = orderService.findCategoriesByMerchantId(merchant);
                if(categories!=null && !categories.isEmpty()){
               
                    if (sessionCustomer != null) {
                        model.addAttribute("sessionCustId", sessionCustomer.getId());
                        if (sessionCustomer.getPassword() != null) {
                            if (sessionCustomer.getPassword().isEmpty()) {
                                model.addAttribute("setPassword", "Y");
                            }
                        } else {
                            model.addAttribute("setPassword", "Y");
                        }
                        Gson gson = new Gson();
                        
                        // DB-------------4
                        String jsonInString = gson.toJson(orderService.findAddessByCustomerId(sessionCustomer.getId()));
                        model.addAttribute("address", jsonInString);
                    } else {
                        model.addAttribute("address", "NoData");
                    }
                    
                    // DB-------------------5
                   /* ConvenienceFee convenienceFee = businessService.findConvenienceFeeByMerchantId(merchant.getId());
                    if (convenienceFee != null) {
                        if (Double.valueOf(convenienceFee.getConvenienceFee()) > 0) {
                            if (convenienceFee.getIsTaxable() != null) {
                                if (convenienceFee.getIsTaxable() == 1) {
                                    model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                                    model.addAttribute("convenienceFeePosId",
                                                    convenienceFee.getConvenienceFeeLineItemPosId());
                                    
                                    // Db--------------6
                                    model.addAttribute(
                                                    "convenienceFeeTax",
                                                    orderService.findConvenienceFeeAfterTax(
                                                                    convenienceFee.getConvenienceFee(),
                                                                    merchant.getId()));
                                    
                                    //DB---------------7
                                    model.addAttribute(
                                                    "convenienceFeeTaxWithComma",
                                                    orderService.findConvenienceFeeAfterMultiTax(
                                                                    convenienceFee.getConvenienceFee(),
                                                                    merchant.getId()));
                                    model.addAttribute("convenienceFeePrice", convenienceFee.getConvenienceFee());
                                }
                            } else {
                                model.addAttribute("convenienceFeeTax", 0);
                                model.addAttribute("convenienceFeePosId",
                                                convenienceFee.getConvenienceFeeLineItemPosId());
                                model.addAttribute("convenienceFeePrice", convenienceFee.getConvenienceFee());
                                model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                            }
                        } else {
                            model.addAttribute("isConvenienceFeePrice", IConstant.BOOLEAN_FALSE);
                        }
                    }*/
                    System.out.println("AllowFutureOrder --> " + merchant.getAllowFutureOrder());
                    model.addAttribute("allowFutureOrder", merchant.getAllowFutureOrder());
                    // model.addAttribute("allowFutureOrder", 1);
                    if (merchant.getAllowFutureOrder() != null
                                    && IConstant.BOOLEAN_TRUE == merchant.getAllowFutureOrder()) {
                        model.addAttribute("futureOrderDates", DateUtil.find10FutureDates());
                    }
                    
                    // DB----------------8
                    model.addAttribute("paymentModes", orderService.findPaymentMode(merchant.getId()));
                    model.addAttribute("categories", categories);
                    model.addAttribute("message", message);
                    model.addAttribute("merchantId", merchant.getId());
                    
                    // DB-----------------9
                    model.addAttribute("merchantTaxs", orderService.findMerchantTaxs(merchant.getId()));
                    
                }else{
                	return "categoryEmpty";
                	
                }
            } catch (Exception exception) {
                if (exception != null) {
                    MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }
            return "order";
        } else {
            return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
        }
    }
    
    
    @RequestMapping(value = "/getConvenienceFeeStatus", method = RequestMethod.GET)
    @ResponseBody
    public String getConvenienceFeeStatus( Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(required = false) String message) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String jsonString = "Not Found";
        if (merchant != null) {
            try {
            	
            	ConvenienceFee convenienceFee = businessService.findConvenienceFeeByMerchantId(merchant.getId());
                if (convenienceFee != null) {
                    if (Double.valueOf(convenienceFee.getConvenienceFee()) > 0) {
                        if (convenienceFee.getIsTaxable() != null) {
                            if (convenienceFee.getIsTaxable() == 1) {
                                map.put("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                                map.put("convenienceFeePosId",
                                                convenienceFee.getConvenienceFeeLineItemPosId());
                                
                                // Db--------------6
                                map.put(
                                                "convenienceFeeTax",
                                                orderService.findConvenienceFeeAfterTax(
                                                                convenienceFee.getConvenienceFee(),
                                                                merchant.getId()));
                                
                                //DB---------------7
                                map.put(
                                                "convenienceFeeTaxWithComma",
                                                orderService.findConvenienceFeeAfterMultiTax(
                                                                convenienceFee.getConvenienceFee(),
                                                                merchant.getId()));
                                map.put("convenienceFeePrice", convenienceFee.getConvenienceFee());
                            }
                        } else {
                            map.put("convenienceFeeTax", 0);
                            map.put("convenienceFeePosId",
                                            convenienceFee.getConvenienceFeeLineItemPosId());
                            map.put("convenienceFeePrice", convenienceFee.getConvenienceFee());
                            map.put("isConvenienceFeePrice", IConstant.BOOLEAN_TRUE);
                        }
                    } else {
                        map.put("isConvenienceFeePrice", IConstant.BOOLEAN_FALSE);
                    }
                }
            	
            	
        Gson gson = new Gson();
        jsonString = gson.toJson(map);
            } catch (Exception exception) {
                if (exception != null) {
                    MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }
        
        
    }
        return jsonString;
    }
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/getBussinessHoursStatus", method = RequestMethod.GET)
    @ResponseBody
    public String getBussinessHoursStatus( Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(required = false) String message) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String jsonString = "Not Found";
        if (merchant != null) {
            try {
        
        
        String avgDeliveryTime=null;
        String avgPickUpTime=null;
        
        String timeZoneCode="America/Chicago";
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getTimeZoneCode()!=null){
    		/*hourDifference=merchant.getTimeZone().getHourDifference();
    		if(merchant.getTimeZone().getMinutDifference()!=null)
    		minutDifference=merchant.getTimeZone().getMinutDifference();*/
    		timeZoneCode=merchant.getTimeZone().getTimeZoneCode();
    	}
        
      
        PickUpTime pickUpTime=merchantService.findPickupTime(merchant.getId());
        if(pickUpTime!=null && pickUpTime.getPickUpTime()!=null){
        	avgPickUpTime=pickUpTime.getPickUpTime();
        }
        
        List<Zone> zones = zoneService.findZoneByMerchantIdAndStatus(merchant.getId());
        if (zones != null) {
            if (!zones.isEmpty() && zones.size()>0) {
            	
            	map.put("zoneStatus", "Y");
                avgDeliveryTime=zones.get(0).getAvgDeliveryTime();
            } else {
            	map.put("zoneStatus", "N");
            }
        }
        
        Calendar deliveryAvgTimeCalendar = Calendar.getInstance();
        Calendar pickUpAvgTimeCalendar = Calendar.getInstance();
        int time=30;
        try{
        if(avgDeliveryTime!=null){
        java.util.Date deliveryAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgDeliveryTime+":00");
        deliveryAvgTimeCalendar.setTime(deliveryAvgTime);
        }if(avgPickUpTime!=null){
            java.util.Date pickUpAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgPickUpTime+":00");
            pickUpAvgTimeCalendar.setTime(pickUpAvgTime);
            }
        
        if(avgDeliveryTime!=null && avgPickUpTime!=null){
        	if(pickUpAvgTimeCalendar.getTimeInMillis()>deliveryAvgTimeCalendar.getTimeInMillis()){
        		time=pickUpAvgTimeCalendar.getTime().getMinutes();
        	}else{
        		time=deliveryAvgTimeCalendar.getTime().getMinutes();
        	}
                         	
        }else if(avgDeliveryTime!=null){
        	time=deliveryAvgTimeCalendar.getTime().getMinutes();
        }else if(avgPickUpTime!=null){
        	time=pickUpAvgTimeCalendar.getTime().getMinutes();
        }
        }catch(Exception e){
        	
        }
        System.out.println(time);
        
        
        // DB-----------3
        List<OpeningClosingTime> openingClosingTimes = voucharService
                        .findOpeningClosingHoursByMerchantId(merchant.getId(),timeZoneCode);
        if (openingClosingTimes != null) {
            if (!openingClosingTimes.isEmpty()) {
            	map.put("closingDayStatus", "Y");
                
                //-------------------
            	map.put("openingClosingStatus",
                                OrderUtil.checkOpeningClosingHour(openingClosingTimes,time,timeZoneCode));
                
                //---------------------
            	map.put("openingClosingHours", OrderUtil.findOperatingHour(openingClosingTimes));
            } else {
            	map.put("closingDayStatus", "N");
            }
        } else {
        	map.put("closingDayStatus", "N");
        	map.put("openingClosingStatus", "N");
        }
        Gson gson = new Gson();
        jsonString = gson.toJson(map);
            } catch (Exception exception) {
                if (exception != null) {
                    MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }
        
        
    }
        return jsonString;
    }
    
    
    
    
    
    
    
    
    /**
     * Show my order page
     * 
     * @param model
     * @param map
     * @return
     */
    @RequestMapping(value = "/myOrder", method = RequestMethod.GET)
    public String showMyOrder(ModelMap model, Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(required = false) String message) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null && merchant != null) {
            String orderStatus = (String) session.getAttribute("failOrder");
            if ("Y".equals(orderStatus)) {
                model.addAttribute("orderStatus", "Y");
            }
            session.setAttribute("failOrder", "N");
            model.addAttribute("orderList", orderService.getAllOrder(customer.getId(), merchant.getId()));
            return "myOrder";
        } else {
            return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
        }
    }

    /**
     * Place order clover API call
     * 
     * @param request
     * @param orderJson
     * @param orderTotal
     * @param instruction
     * @return
     */
    @RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
    @ResponseBody
    public String placeOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam() String orderJson,
                    @RequestParam String orderTotal, @RequestParam(required = false) String instruction, @RequestParam(required = false) Double discount,@RequestParam(required = false) String discountType,@RequestParam(required = false) String itemPosIds,@RequestParam(required = false) String inventoryLevel,@RequestParam(required = false) String voucherCode ,
                    @RequestParam String orderType, @RequestParam(required = false) String convenienceFee,
                    @RequestParam String deliveryItemPrice, @RequestParam String avgDeliveryTime , @RequestParam(required = false) String itemsForDiscount, @RequestParam(required = false) String listOfALLDiscounts) {
       
    	//MailSendUtil.sendErrorMailToAdmin("inside placeOrder controller"+orderJson);
    	String orderPosId = "Failed";
        try {
            HttpSession session = request.getSession();
            session.setAttribute("orderType", orderType);

            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Customer customer = (Customer) session.getAttribute("customer");
            
            System.out.println("~~~~&&&"+itemsForDiscount);
            System.out.println("~~~~&&&"+listOfALLDiscounts);
            if (merchant != null) {
                String orderTypePosid = "";
                OrderType type = orderService.findByMerchantIdAndLabel(merchant.getId(), orderType);
                if (type != null) {
                    orderTypePosid = type.getPosOrderTypeId();
                }

                String employeePosId = null;

                if (merchant != null && merchant.getEmployeePosId() != null
                                && (!merchant.getEmployeePosId().isEmpty() || !merchant.getEmployeePosId().equals(""))) {
                    employeePosId = merchant.getEmployeePosId();
                }
                
                if(discount!=null && discount>0){
                	
                }else{
                	discount=0.0;
                }
                orderJson=  "{\n\"order\":[" + orderJson + "]}";
                String finalJson = OrderUtil.findFinalOrderJson(orderJson, orderTotal, instruction, orderTypePosid,
                                customer, employeePosId,discount,discountType,voucherCode,itemPosIds,inventoryLevel,itemsForDiscount,listOfALLDiscounts);
                if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
                String result = ProducerUtil.placeOrder(finalJson, merchant);
                if (result != null && result.contains("title")&& result.startsWith("{")) {
                    JSONObject jObject = new JSONObject(result);
                    orderPosId = orderService.saveOrder(jObject, finalJson, customer, merchant, discount,
                                    convenienceFee, deliveryItemPrice, avgDeliveryTime,orderType);
                }}else{
                	JSONObject jObject = null;
                    orderPosId = orderService.saveOrder(jObject, finalJson, customer, merchant, discount,
                                    convenienceFee, deliveryItemPrice, avgDeliveryTime,orderType);
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
        return orderPosId;
    }
    
    @RequestMapping(value = "/placeOrderOnAWS", method = { RequestMethod.POST })
    @ResponseBody
    public String placeOrderOnAWS(@RequestBody PlaceOrderVO placeOrderVO,HttpServletRequest request, HttpServletResponse response) {
    	
    	 String orderJson="";//placeOrderVO.getOrderJson();
         String orderTotal=placeOrderVO.getOrderTotal();  
         String instruction=placeOrderVO.getInstruction();  
         Double discount=placeOrderVO.getDiscount(); 
         String discountType=placeOrderVO.getDiscountType(); 
         String itemPosIds=placeOrderVO.getItemPosIds(); 
         String inventoryLevel=placeOrderVO.getInventoryLevel(); 
         String voucherCode =placeOrderVO.getVoucherCode();
         String orderType=placeOrderVO.getOrderType();  
         String convenienceFee=placeOrderVO.getConvenienceFee();
         String deliveryItemPrice=placeOrderVO.getDeliveryItemPrice();  
         String avgDeliveryTime =placeOrderVO.getAvgDeliveryTime();  
         String itemsForDiscount=placeOrderVO.getItemsForDiscount();  
         String listOfALLDiscounts=placeOrderVO.getListOfALLDiscounts();
         
         Gson gson = new Gson();
          orderJson = gson.toJson(placeOrderVO.getOrderJson());
       
    //MailSendUtil.sendErrorMailToAdmin("inside placeOrder controller"+orderJson);
    	String orderPosId = null;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("orderType", orderType);

            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Customer customer = (Customer) session.getAttribute("customer");
            
            System.out.println("~~~~&&&"+itemsForDiscount);
            System.out.println("~~~~&&&"+listOfALLDiscounts);
            if (merchant != null) {
                String orderTypePosid = "";
                OrderType type = orderService.findByMerchantIdAndLabel(merchant.getId(), orderType);
                if (type != null) {
                    orderTypePosid = type.getPosOrderTypeId();
                }

                String employeePosId = null;

                if (merchant != null && merchant.getEmployeePosId() != null
                                && (!merchant.getEmployeePosId().isEmpty() || !merchant.getEmployeePosId().equals(""))) {
                    employeePosId = merchant.getEmployeePosId();
                }
                
                if(discount!=null && discount>0){
                	
                }else{
                	discount=0.0;
                }
                orderJson=  "{\n\"order\":" + orderJson + "}";
                String finalJson = OrderUtil.findFinalOrderJson(orderJson, orderTotal, instruction, orderTypePosid,
                                customer, employeePosId,discount,discountType,voucherCode,itemPosIds,inventoryLevel,itemsForDiscount,listOfALLDiscounts);
                if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
                String result = ProducerUtil.placeOrder(finalJson, merchant);
                if (result != null && result.contains("title")&& result.startsWith("{")) {
                    JSONObject jObject = new JSONObject(result);
                    orderPosId = orderService.saveOrder(jObject, finalJson, customer, merchant, discount,
                                    convenienceFee, deliveryItemPrice, avgDeliveryTime,orderType);
                }}else{
                	JSONObject jObject = null;
                    orderPosId = orderService.saveOrder(jObject, finalJson, customer, merchant, discount,
                                    convenienceFee, deliveryItemPrice, avgDeliveryTime,orderType);
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
        return orderPosId;
    }

    /**
     * Call clover API of payment for clover order
     * 
     * @param paymentVO
     * @param model
     * @param request
     * @param response
     * @return
     */
   /* @RequestMapping(value = "/orderPayment", method = { RequestMethod.POST })
    public String orderPayment(@ModelAttribute("PaymentVO") PaymentVO paymentVO, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            if (merchant != null) {
                if (paymentVO.getOrderPosId() != null
                                && (!paymentVO.getOrderPosId().isEmpty() || !paymentVO.getOrderPosId().equals(""))) {
                    Gson gson = new Gson();
                    String paymentJson = gson.toJson(paymentVO);
                    String orderType = (String) session.getAttribute("orderType");
                    String merchantLogo = null;
                    if (merchant != null) {
                        if (merchant.getMerchantLogo() == null) {
                            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
                        } else {
                            merchantLogo = UrlConstant.BASE_PORT + merchant.getMerchantLogo();
                        }
                    }
                    String result ="";
                    if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
                     result = ProducerUtil.orderPayment(paymentJson, merchant);
                    }

                    boolean updateStatus = orderService.updateOrderStatus(paymentVO.getOrderPosId(), result,
                                    customer.getId(), paymentVO.getPaymentMethod(), paymentVO.getSubTotal(),
                                    paymentVO.getTax(), customer.getFirstName(), customer.getEmailId(),
                                    merchant.getName(), merchantLogo, orderType, paymentVO.getTip(),
                                    paymentVO.getTotal(), paymentVO.getFutureOrderType(), paymentVO.getFutureDate(),
                                    paymentVO.getFutureTime());

                    if (updateStatus) {
                        if (result.contains("result") && !result.contains("DECLINED")) {
                            try {
                                JSONObject orderResult = new JSONObject(result);
                                if (orderResult.toString().contains("result")) {
                                    String status = orderResult.getString("result");
                                    if (status.equals("APPROVED") || status.equals("SUCCESS")) {
                                        model.addAttribute("message", "Your order is placed successfully");
                                    }
                                } else {
                                    String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                                    merchant.getAccessToken(), paymentVO.getOrderPosId());
                                    orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                                    System.out.println("orderDeleteResponse" + orderDeleteResponse);
                                    MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+", Payment API response"+result);
                                    model.addAttribute("message",
                                                    "Your order was not placed. Please try again or call us");
                                    session.setAttribute("failOrder", "Y");
                                }
                            } catch (Exception exception) {
                                if (exception != null) {
                                    MailSendUtil.sendExceptionByMail(exception);
                                }
                                exception.printStackTrace();
                            }
                            OrderServiceImpl impl = new OrderServiceImpl();
                            impl.start();
                        } else {
                            String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                            merchant.getAccessToken(), paymentVO.getOrderPosId());
                            MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+", Payment API response"+result);
                            orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                            System.out.println("orderDeleteResponse" + orderDeleteResponse);
                            
                            model.addAttribute("message", "Your order was not placed. Please try again or call us");
                            session.setAttribute("failOrder", "Y");
                        }
                        if (customer.getPassword() != null) {
                            if (!customer.getPassword().isEmpty()) {
                                return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                            } else {
                                return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                            }
                        } else {
                            return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                        }
                    } else {
                        String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                        merchant.getAccessToken(), paymentVO.getOrderPosId());
                        MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+", Payment API response"+result);
                        orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                        System.out.println("orderDeleteResponse" + orderDeleteResponse);
                        model.addAttribute("message", "Your order was not placed. Please try again or call us");
                        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                    }

                } else {
                	MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId());
                    model.addAttribute("message", "Your order was not placed. Please try again or call us");
                    return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";

                }
            } else {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
                String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                merchant.getAccessToken(), paymentVO.getOrderPosId());
                orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                System.out.println("orderDeleteResponse" + orderDeleteResponse);
                model.addAttribute("message", "Your order was not placed. Please try again or call us");
                session.setAttribute("failOrder", "Y");
            }
        }
        return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
    }*/
    
    @RequestMapping(value = "/orderPaymentUsingAjax", method = { RequestMethod.POST })
    @ResponseBody
    public String orderPaymentUsingAjax(@ModelAttribute("PaymentVO") PaymentVO paymentVO, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("ORDERCONTROLLE listOfDiscount Jason:::"+paymentVO.getListOfALLDiscounts());
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Customer customer = (Customer) session.getAttribute("customer");
        String responseBody="";
        try {
            if (merchant != null) {
                if (paymentVO.getOrderPosId() != null
                                && (!paymentVO.getOrderPosId().isEmpty() || !paymentVO.getOrderPosId().equals(""))) {
                    Gson gson = new Gson();
                    String paymentJson = gson.toJson(paymentVO);
                    String orderType = (String) session.getAttribute("orderType");
                    String merchantLogo = "";
                    if (merchant != null) {
                        if (merchant.getMerchantLogo()!= null && ! merchant.getMerchantLogo().isEmpty() && !merchant.getMerchantLogo().equals("")) {
                            
                            merchantLogo = UrlConstant.BASE_PORT + merchant.getMerchantLogo();
                        } else {
                        	merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
                        }
                    }
                    String result ="";
                    if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.POS_CLOVER){
                     result = ProducerUtil.orderPayment(paymentJson, merchant);
                    }else{
                    	 result ="{\"result\":\"APPROVED\" }";
                    }
   
                    boolean updateStatus = false;
                    boolean discountSaveStatus = false;
                    
                    
                    if(paymentVO!=null && paymentVO.getOrderPosId()!=null && customer.getId()!=null && paymentVO.getPaymentMethod()!=null && paymentVO.getSubTotal()!=null && paymentVO.getTax()!=null && customer.getFirstName()!=null &&
                    		customer.getEmailId()!=null && merchant.getName()!=null  && orderType!=null  &&  paymentVO.getTotal()!=null  && merchant!=null){
                    	updateStatus=orderService.updateOrderStatus(paymentVO.getOrderPosId(), result,
                                    customer.getId(), paymentVO.getPaymentMethod(), paymentVO.getSubTotal(),
                                    paymentVO.getTax(), customer.getFirstName(), customer.getEmailId(),
                                    merchant.getName(), merchantLogo, orderType, paymentVO.getTip(),
                                    paymentVO.getTotal(), paymentVO.getFutureOrderType(), paymentVO.getFutureDate(),
                                    paymentVO.getFutureTime(),merchant,paymentVO.getListOfALLDiscounts());
                    	
                    	System.out.println("updateststus is :"+ updateStatus);
                    }
                    
                 

                    if (updateStatus) {
                        if (result.contains("result") && !result.contains("DECLINED")) {
                            try {
                                JSONObject orderResult = new JSONObject(result);
                                if (orderResult.toString().contains("result")) {
                                    String status = orderResult.getString("result");
                                    if (status.equals("APPROVED") || status.equals("SUCCESS")) {
                                        model.addAttribute("message", "Your order is placed successfully");
                                        responseBody="APPROVED";
                                    }else{
                                    	responseBody="DECLINED";
                                    }
                                } else {
                                	responseBody="FAILED";
                                    /*String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                                    merchant.getAccessToken(), paymentVO.getOrderPosId());*/
                                   // orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                                    //System.out.println("orderDeleteResponse" + orderDeleteResponse);
                                    MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+",Oder Total amount -->"+paymentVO.getTotal()+" Payment Type-->"+paymentVO.getPaymentMethod()+" Payment API response"+result);
                                    model.addAttribute("message",
                                                    "Your order was not placed. Please try again or call us");
                                    session.setAttribute("failOrder", "Y");
                                }
                            } catch (Exception exception) {
                            	responseBody="FAILED";
                                if (exception != null) {
                                    MailSendUtil.sendExceptionByMail(exception);
                                }
                                exception.printStackTrace();
                            }
                            OrderServiceImpl impl = new OrderServiceImpl();
                            impl.start();
                            
                        } else {
                        	responseBody="DECLINED";
                            /*String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                            merchant.getAccessToken(), paymentVO.getOrderPosId());*/
                        	MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+",Oder Total amount -->"+paymentVO.getTotal()+" Payment Type-->"+paymentVO.getPaymentMethod()+" Payment API response"+result);
                        	//orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                           // System.out.println("orderDeleteResponse" + orderDeleteResponse);
                            
                            model.addAttribute("message", "Your order was not placed. Please try again or call us");
                            session.setAttribute("failOrder", "Y");
                        }
                        /*if (customer.getPassword() != null) {
                            if (!customer.getPassword().isEmpty()) {
                                return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                            } else {
                                return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                            }
                        } else {
                            return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                        }*/
                    } else {
                    	responseBody="FAILED";
                        /*String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                        merchant.getAccessToken(), paymentVO.getOrderPosId());*/
                    	MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+",Oder Total amount -->"+paymentVO.getTotal()+" Payment Type-->"+paymentVO.getPaymentMethod()+" Payment API response"+result);
                    	//orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                        //System.out.println("orderDeleteResponse" + orderDeleteResponse);
                        model.addAttribute("message", "Your order was not placed. Please try again or call us");
                        //return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";
                    }

                } else {
                	responseBody="FAILED";
                	MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId()+",Oder Total amount -->"+paymentVO.getTotal()+" Payment Type-->"+paymentVO.getPaymentMethod());
                	//MailSendUtil.orderFaildMail("MerchantName -> " +merchant.getName()+" ,MerchantId-> "+merchant.getPosMerchantId()+", orderId -> "+paymentVO.getOrderPosId());
                    model.addAttribute("message", "Your order was not placed. Please try again or call us");
                   // return "redirect:" + UrlConstant.WEB_BASE_URL + "/order";

                }
            } else {
                return "redirect:" + UrlConstant.WEB_BASE_URL + "/sessionTimeOut";
            }
        } catch (Exception exception) {
            if (exception != null) {
            	responseBody="FAILED";
                MailSendUtil.sendExceptionByMail(exception);
                /*String orderDeleteResponse = CloverUrlUtil.deleteOrder(merchant.getPosMerchantId(),
                                merchant.getAccessToken(), paymentVO.getOrderPosId());*/
               // orderService.deleteAnOrder(customer.getId(), paymentVO.getOrderPosId());
                //System.out.println("orderDeleteResponse" + orderDeleteResponse);
                model.addAttribute("message", "Your order was not placed. Please try again or call us");
                session.setAttribute("failOrder", "Y");
            }
        }
        return responseBody;
    }

    @RequestMapping(value = "/getMenuItems", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuItems(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam Integer categoryId) {
        String categoryItemsJson = "Not Found";
        try {
        	 HttpSession session = request.getSession();
        	 if(session!=null){
             Merchant merchant = (Merchant) session.getAttribute("merchant");
             if (merchant != null) {
            	 List<ItemDto> itemDtos = orderService.findCategoryItems(categoryId,merchant);
                 Gson gson = new Gson();
                 categoryItemsJson = gson.toJson(itemDtos);
             }
        	 }
           
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return categoryItemsJson;
    }
    
    @RequestMapping(value = "/getMenuCategories", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuCategories(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer page) {
        String categoryItemsJson = "Not Found";
        try {
        	 HttpSession session = request.getSession();
        	 if(session!=null){
             Merchant merchant = (Merchant) session.getAttribute("merchant");
             if (merchant != null) {
            	 List<CategoryDto> categories = orderService.findCategoriesByMerchantIdWithLimit(merchant.getId(), page);
                 Gson gson = new Gson();
                 categoryItemsJson = gson.toJson(categories);
             }
        	 }
           
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return categoryItemsJson;
    }

    @RequestMapping(value = "/checkCustomerType", method = RequestMethod.GET)
    @ResponseBody
    public String checkCustomerType(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam String emailId, @RequestParam Integer merchantId) {
        String customerResponse = "Not Found";
        try {
        	HttpSession session = request.getSession();
        	Vendor vendor=null;
        	if(session!=null){
        	 vendor = (Vendor) session.getAttribute("vendor");
        	}
        	List<Customer> customers=null;
        	if(vendor!=null && vendor.getId()!=null){
        		customers = customerService.findByEmailIDAndVendorId(emailId, vendor.getId());
        	}else{
            customers = customerService.findByEmailIDAndMerchantId(emailId, merchantId);
        	}

            for (Customer customer : customers) {
                if (customer.getCustomerType() != null && customer.getCustomerType().toLowerCase().equals("php")) {
                    customerResponse = "php";
                } else {
                    customerResponse = "java";
                }
            }

        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return customerResponse;
    }

    @RequestMapping(value = "/getItemModiferGroups", method = RequestMethod.GET)
    @ResponseBody
    public String getItemModiferGroups(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam Integer itemId, @RequestParam Integer allowModifierLimit) {
        String itemModifierGroupJson = "Not Found";
        try {
        	HttpSession session = request.getSession();
        	if(session!=null){
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
            	List<ModifierGroupDto> itemModifierGroupDtos = orderService.getModifierGroup(itemId, allowModifierLimit,merchant);
                Gson gson = new Gson();
                itemModifierGroupJson = gson.toJson(itemModifierGroupDtos);
            }
        	}
            

        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return itemModifierGroupJson;
    }

    @RequestMapping(value = "/checkMinDeliveryAmount", method = RequestMethod.GET)
    @ResponseBody
    public Double checkMinDelivery(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam Double subTotalAmount) {
        HttpSession session = request.getSession();
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant != null) {
            return zoneService.checkMinDeliveryAmount(subTotalAmount, merchant.getId());
        } else
            return null;
    }

    @RequestMapping(value = "/getOrderFullFill", params = { "date", "merchantId" }, method = RequestMethod.GET)
    @ResponseBody
    public String getOrderFulFilledDate(Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(value = "date") String date, @RequestParam(value = "merchantId") String merchantId) {
        long dateValue = Long.valueOf(date);
        Date dateObj = new Date(dateValue);
        String jsonString = "Not Found";
        try {
            Merchant merchant = merchantService.findbyPosId(merchantId);
            if (merchant != null) {
                List<OrderR> orderData = orderService.findAllOrdersByFulfilledDate(dateObj, merchant.getId());
                List<FulFilledOrder> filledOrders = new ArrayList<FulFilledOrder>();
                for (OrderR orderR : orderData) {
                    FulFilledOrder filledOrder = new FulFilledOrder();
                    filledOrder.setOrderDate(orderR.getCreatedOn());
                    filledOrder.setOrderID(orderR.getId() + "");
                    filledOrder.setOrderType(orderR.getOrderType());
                    filledOrder.setPickUpTime(orderR.getFulfilled_on() + "");

                    filledOrder.setFirstName(orderR.getCustomer().getFirstName());
                    filledOrder.setLastName(orderR.getCustomer().getLastName());
                    filledOrder.setPhoneNumber(orderR.getCustomer().getPhoneNumber());
                    filledOrder.setEmailId(orderR.getCustomer().getEmailId());
                    filledOrders.add(filledOrder);
                }
                Gson gson = new Gson();
                jsonString = gson.toJson(filledOrders);
            }
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }

        return jsonString;
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    @ResponseBody
    public String updateOrder(Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(value = "date") String date, @RequestParam(value = "merchantId") String merchantId,
                    @RequestParam(value = "reason") String reason, @RequestParam(value = "orderId") Integer orderId) {
        String jsonString = "Not Found";
        try {
            long dateValue = Long.valueOf(date);
            Date dateObj = new Date(dateValue);
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
            System.out.println(df.format(dateObj));
            OrderR orderR = orderService.findOrderByID(orderId);
            orderR.setFulfilled_on(dateObj);
            orderService.saveOrder(orderR);
            orderService.sendMailUser(orderR, reason, df.format(dateObj));
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return jsonString;
    }

    /**
     * Find opening hour for future order
     * 
     * @param futureDate
     * @return String
     */
    @RequestMapping(value = "/getFutureDateOpeningTime", method = RequestMethod.GET)
    @ResponseBody
    public List<String> findFuturOpeningtime(HttpServletRequest request,
                    @RequestParam(value = "futureDate") String futureDate,
                    @RequestParam(value = "orderType") String orderType) {
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                return businessService.findFutureOrderOpeningHours(futureDate, merchant.getId(), orderType);
            }
            return new ArrayList<String>();
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
            return new ArrayList<String>();
        }
    }
    
    @RequestMapping(value = "/getFutureDates", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFutureDates(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                return businessService.findFutureDates( merchant);
            }
            return new ArrayList<String>();
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
            return new ArrayList<String>();
        }
    }
}
