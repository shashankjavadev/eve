package com.foodkonnekt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.clover.vo.CashVO;
import com.foodkonnekt.clover.vo.CloverOrderVO;
import com.foodkonnekt.clover.vo.PaymentVO;
import com.foodkonnekt.clover.vo.Tender;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.service.BusinessService;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.ModifierService;
import com.foodkonnekt.service.OrderService;
import com.foodkonnekt.service.WebhookAppService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.Main;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class CloverController {

    @Autowired
    private CloverService cloverService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ModifierService modifierService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private WebhookAppService webhookAppService;

    /**
     * Get merchant , payment mode, taxrate and orderType from clover
     * 
     * @param clover
     * @param response
     */
    

    
    
    @RequestMapping(value = "/getCloverData", method = RequestMethod.POST)
    public @ResponseBody void getCloverData(@RequestBody Clover clover, HttpServletResponse response) {
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        Merchant merchant = cloverService.saveMerchant(clover);
        System.out.println("-----Save Merchant Done-----" + merchant.getId());

        String paymentModes = CloverUrlUtil.getPaymentModes(clover);
        cloverService.savePaymentMode(paymentModes, merchant);
        System.out.println("-----Save PaymentModes Done-----");

        /*String orderTypeDetail = CloverUrlUtil.getOderType(clover);
        cloverService.saveOrderType(orderTypeDetail, merchant, clover);
        System.out.println("-----Save OrderType Done-----");*/

        String taxRates = CloverUrlUtil.getTaxRate(clover);
        cloverService.saveTaxRate(taxRates, merchant);
        System.out.println("-----Save TaxRate Done-----");

        String openingHour = CloverUrlUtil.getOpeningHour(clover);
        cloverService.saveOpeningClosing(merchant, openingHour);
        System.out.println("-----Save OpeningAndClosingHour Done-----");

        cloverService.saveCategory(clover, merchant);
        System.out.println("-----Save Category Done-----");

        cloverService.saveItem(clover, merchant);
        System.out.println("-----Save Item Done-----");

        String modifierJson = CloverUrlUtil.getModifierAndModifierGroup(clover);
        modifierService.saveModifierAndModifierGroup(modifierJson, merchant);
        System.out.println("-----Save ModifiersAndModifierGroups Done-----");

        System.out.println("-------final Done------");
    }

  /*  @RequestMapping(value = "/clover", method = RequestMethod.GET)
    @ResponseBody
    public String clover(@RequestParam("merchant_id") String merchantId,
                    @RequestParam("access_token") String accessToken, HttpServletResponse response,
                    HttpServletRequest request) throws Exception {
        Clover clover = new Clover();
        clover.setMerchantId(merchantId);
        clover.setAuthToken(accessToken);
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        System.out.println("---------MerchantId-----" + merchantId);
        System.out.println("---------accessToken-----" + accessToken);

        // merchantId = "9GK2J085R8A3A";
        Merchant merchantCheck = merchantService.findbyPosId(merchantId);
        HttpSession session = request.getSession();
        if (null != merchantCheck && merchantCheck.getIsInstall()!=null &&merchantCheck.getIsInstall() == IConstant.BOOLEAN_TRUE) {
            System.out.println("---Inisde if bock---" + merchantCheck.getIsInstall());
            String merchId = merchantCheck.getId().toString();
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String merchantName=merchantCheck.getName().replaceAll("\\s+","");
            String orderLink = UrlConstant.BASE_PORT + "foodkonnekt-web/"+merchantName+"/clover/" + base64encode;
            session.setAttribute("merchant", merchantCheck);
            session.setAttribute("onlineOrderLink", orderLink);
           // response.sendRedirect("adminHome");
            return "adminHome";
        } else {
        	
        	if (null != merchantCheck && (merchantCheck.getIsInstall()==null ||merchantCheck.getIsInstall() == IConstant.BOOLEAN_FALSE) ) {
        		webhookAppService.appUnInstall(merchantId);

                System.out.println("merchant  " + merchantId + " deleted ");
        	}
            System.out.println("---Inisde else bock---");
            Merchant merchant = cloverService.saveMerchant(clover);
            String paymentModes = CloverUrlUtil.getPaymentModes(clover);

            cloverService.savePaymentMode(paymentModes, merchant);

            String orderTypeDetail = CloverUrlUtil.getOderType(clover);
            cloverService.saveOrderType(orderTypeDetail, merchant, clover);
            String taxRates = CloverUrlUtil.getTaxRate(clover);
            cloverService.saveTaxRate(taxRates, merchant);

            String openingHour = CloverUrlUtil.getOpeningHour(clover);
            cloverService.saveOpeningClosing(merchant, openingHour);

            cloverService.saveItem(clover, merchant);
            cloverService.saveCategory(clover, merchant);
            String modifierJson = CloverUrlUtil.getModifierAndModifierGroup(clover);
            modifierService.saveModifierAndModifierGroup(modifierJson, merchant);
            System.out.println("Done");
            String merchId = merchant.getId().toString();
            String merchantName=merchant.getName().replaceAll("\\s+","");;
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String orderLink = UrlConstant.BASE_PORT + "foodkonnekt-web/"+merchantName+"/clover/" + base64encode;
            session.setAttribute("onlineOrderLink", orderLink);
            session.setAttribute("merchantId", merchantId);
           // response.sendRedirect("welcome");
            return "welcome";
        }
    }*/

    
    
    @RequestMapping(value = "/clover", method = RequestMethod.GET)
    @ResponseBody
    public String clover(@RequestParam("merchant_id") String merchantId,
                    @RequestParam("access_token") String accessToken, HttpServletResponse response,
                    HttpServletRequest request) throws Exception {
       final Clover clover = new Clover();
        clover.setMerchantId(merchantId);
        clover.setAuthToken(accessToken);
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        System.out.println("---------MerchantId-----" + merchantId);
        System.out.println("---------accessToken-----" + accessToken);

        // merchantId = "9GK2J085R8A3A";
        Merchant merchantCheck = merchantService.findbyPosId(merchantId);
        final HttpSession session = request.getSession();
        if (null != merchantCheck && merchantCheck.getIsInstall()!=null && (merchantCheck.getIsInstall() == IConstant.BOOLEAN_TRUE ||merchantCheck.getIsInstall() == 2)) {
        	if(merchantCheck.getIsInstall() == 2){
        	merchantCheck=cloverService.saveMerchant(clover);
        	 String orderTypeDetail = CloverUrlUtil.getOderType(clover);
	            cloverService.saveOrderType(orderTypeDetail, merchantCheck, clover);
	            
	            System.out.println("reset order types");
        	}
        	if(merchantCheck.getEmployeePosId()==null ||merchantCheck.getEmployeePosId().isEmpty() || merchantCheck.equals("")){
        	String employeePosId= CloverUrlUtil.createEmployeeOnClover(clover);
            if(employeePosId!=null){
            	merchantCheck.setEmployeePosId(employeePosId);
            	merchantCheck = merchantService.save(merchantCheck);
            }
            
            
        	}
        	System.out.println("---Inisde if bock---" + merchantCheck.getIsInstall());
            String merchId = merchantCheck.getId().toString();
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String merchantName=merchantCheck.getName().replaceAll("\\s+","");
            String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/clover/" + base64encode;
            
            session.setAttribute("merchant", merchantCheck);
            session.setAttribute("onlineOrderLink", orderLink);
           // response.sendRedirect("adminHome");
            return "adminHome";
        } else {
        	 
        	String merchantDetails = CloverUrlUtil.getMerchantDetails(clover);
        	if(merchantDetails.contains("id")){
        	/*if (null != merchantCheck && (merchantCheck.getIsInstall()==null ||merchantCheck.getIsInstall() == IConstant.BOOLEAN_FALSE ||merchantCheck.getIsInstall() == IConstant.SOFT_DELETE) ) {
        		webhookAppService.appUnInstall(merchantId);

                System.out.println("merchant  " + merchantId + " deleted ");
        	}*/
            System.out.println("---Inisde else bock---");
            final Merchant merchant = cloverService.saveMerchant(clover);
            
            MailSendUtil.productInstallationMail(merchant,"Clover","Installed");
            
           Runnable merchantDetailRunnable = new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("Clover merchantDetailThread Start");
					String paymentModes = CloverUrlUtil.getPaymentModes(clover);

		            cloverService.savePaymentMode(paymentModes, merchant);

		            /*String orderTypeDetail = CloverUrlUtil.getOderType(clover);
		            cloverService.saveOrderType(orderTypeDetail, merchant, clover);*/
		            String taxRates = CloverUrlUtil.getTaxRate(clover);
		            cloverService.saveTaxRate(taxRates, merchant);

		            String openingHour = CloverUrlUtil.getOpeningHour(clover);
		            cloverService.saveOpeningClosing(merchant, openingHour);
			            System.out.println("merchantDetailThread Done");
				}
			};
			Thread merchantDetailThread = new Thread(merchantDetailRunnable);
			merchantDetailThread.setName("merchantDetailThread");
			merchantDetailThread.start();
            
            /*Runnable inventoryRunnable = new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					session.setAttribute("inventoryThread", 0);
					System.out.println("Clover inventoryThread Start");
					 cloverService.saveItem(clover, merchant);
					 System.out.println("Clover item done");
			            cloverService.saveCategory(clover, merchant);
			            System.out.println("Clover category done");
			            String modifierJson = CloverUrlUtil.getModifierAndModifierGroup(clover);
			            modifierService.saveModifierAndModifierGroup(modifierJson, merchant);
			            System.out.println("inventoryThread Done");
			            session.setAttribute("inventoryThread", 1);
			            try{
			            	if(session.getAttribute("isInstall")!=null){
			            int isIntall=(Integer)session.getAttribute("isInstall");
			            merchant.setIsInstall(isIntall);
	                    merchantService.save(merchant);}
	                    }catch(Exception e){
	                    	System.out.println("isInstall is not set ->"+e);
	                    }
				}
			};
			Thread inventoryThread = new Thread(inventoryRunnable);
			inventoryThread.setName("inventoryThread");
			inventoryThread.start();*/
           
            System.out.println("Done");
            String merchId = merchant.getId().toString();
            String merchantName=merchant.getName().replaceAll("\\s+","");;
            String base64encode = EncryptionDecryptionUtil.encryption(merchId);
            String orderLink = UrlConstant.WEB_BASE_URL + "/"+merchantName+"/clover/" + base64encode;
            
            session.setAttribute("onlineOrderLink", orderLink);
            session.setAttribute("merchantId", merchantId);
            session.setAttribute("merchant", merchant);
           // response.sendRedirect("welcome");
            return "uploadLogo";
            }else{
            	 return "https://www.foodkonnekt.com";
            }
        	
        }
    }
    
    @RequestMapping(value = "/addPerOrderCharge", method = RequestMethod.GET)
    @ResponseBody
    public String addPerOrderCharge(@RequestParam(required = true) Integer merchantId) {
        Merchant merchant = merchantService.findById(merchantId);
        String response = CloverUrlUtil.addMteredPrice(merchant);
        return response;
    }

    @RequestMapping(value = "/getMetererCount", method = RequestMethod.GET)
    @ResponseBody
    public String getMetererCount(@RequestParam(required = true) Integer merchantId) {
        Merchant merchant = merchantService.findById(merchantId);
        String response = CloverUrlUtil.getMeteredCount(merchant);
        return response;
    }

    /**
     * Place order on clover
     * 
     * @param merchantId
     * @param accessToken
     * @param cloverOrderVO
     * @param response
     * @return
     */
    @RequestMapping(value = "/postOrderOnClover", method = RequestMethod.POST)
    public @ResponseBody String postOrderOnClover(@RequestParam("merchant_id") String merchantId,
                    @RequestParam("access_token") String accessToken, @RequestBody CloverOrderVO cloverOrderVO,
                    HttpServletResponse response) {
        Clover clover = new Clover();
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        clover.setMerchantId(merchantId);
        clover.setAuthToken(accessToken);
        return cloverService.postOrderOnClover(cloverOrderVO, clover);
    }

    /**
     * Payment API method of clover order
     * 
     * @param merchantId
     * @param accessToken
     * @param paymentVO
     * @param response
     * @return
     */
    @RequestMapping(value = "/cloverOrderPayment", method = RequestMethod.POST)
    public @ResponseBody String cloverPayment(@RequestParam("merchant_id") String merchantId,
                    @RequestParam("access_token") String accessToken, @RequestBody PaymentVO paymentVO,
                    HttpServletResponse response) {
        JsonObject jsonObject = null;
        Clover clover = new Clover();
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        clover.setMerchantId(merchantId);
        clover.setAuthToken(accessToken);
       /* double aDouble = Double.parseDouble(paymentVO.getTotal());
       // long orderTotalAmount=(long) (aDouble*100);
        double d=aDouble*100;
		Long orderTotalAmount=Math.round(d);
        System.out.println("Long"+orderTotalAmount);
        double roundOff = Math.round(aDouble * 100.0) / 100.0;
        double total = roundOff * 100;
        double grandTotal= Math.round(total);
        System.out.println("Double"+grandTotal);
        long tip = (long)paymentVO.getTip()*100;
        if (!paymentVO.getPaymentMethod().equals("Cash")) {
            try {
            	
            	
                final String MERCHANT_ID = merchantId;
                final String ACCESS_TOKEN = accessToken;
                // Test Credit Card Info
                final String CC_NUMBER = paymentVO.getCcNumber();
                final String CVV_NUMBER = paymentVO.getCcCode();
                final int EXP_MONTH = paymentVO.getExpMonth();
                final int EXP_YEAR = paymentVO.getExpYear();
                jsonObject = Main.testWebPay(MERCHANT_ID, ACCESS_TOKEN, CC_NUMBER, CVV_NUMBER, EXP_MONTH, EXP_YEAR,
                                paymentVO.getOrderPosId(), orderTotalAmount ,tip);
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            System.out.println("-----CloverController-----" + jsonObject);
            if (jsonObject != null) {
            	return jsonObject.toString();
            } else {
                return new JSONObject("{}").toString();
            }
        } else {
            PaymentMode paymentMode = null;
            if (merchantId != null && !merchantId.isEmpty()) {
                Merchant merchant = merchantService.findbyPosId(merchantId);
                paymentMode = businessService.findByMerchantIdAndLabel(merchant.getId(), "Cash");
            }

            CashVO cashVO = new CashVO();
            cashVO.setAmount(total);
            cashVO.setCashTendered(total);
            cashVO.setOffline(1);
            cashVO.setResult("SUCCESS");
            cashVO.setTipAmount(tip);

            Tender tender = new Tender();
            tender.setEditable(0);
            tender.setEnabled(0);
            if (paymentMode != null) {
                tender.setId(paymentMode.getPosPaymentModeId());
            }
            tender.setOpensCashDrawer(0);
            tender.setVisible(0);
            tender.setSupportsTipping(1);
            cashVO.setTender(tender);
            Gson gson = new Gson();
            String cashJson = gson.toJson(cashVO);
            System.out.println("----Cash input json---" + cashJson);
            String result = CloverUrlUtil.cashPayment(cashJson, merchantId, accessToken, paymentVO.getOrderPosId());
            System.out.println("---Cash result---" + result);
            return result;
        }*/
        
        Long orderTotalAmount= null;
        String orderId = paymentVO.getOrderPosId();
       // String orderResponse = CloverUrlUtil.getFeedbackClover(clover,orderId);
        if(orderId != null && !orderId.isEmpty() && orderId != ""){
          	String orderResponse = CloverUrlUtil.getFeedbackClover(clover,orderId);
          	 System.out.println("CLOVER CONTROLLER clover order payment"+orderResponse);
          	 if(orderResponse!=null && orderResponse.contains("id") ){
          		 JSONObject jObject = new JSONObject(orderResponse);
          		//Number tmp = (Number) (jObject.getLong("total"));
          		 orderTotalAmount = (jObject.getLong("total")); 
          		 System.out.println(orderTotalAmount);
          	 }
          	 
          }
        
     
         //long tip = (long)(paymentVO.getTip()*100);
        double d=paymentVO.getTip()*100;
		Long tip=Math.round(d);
         
         if(orderTotalAmount != null){
         
         if (!paymentVO.getPaymentMethod().equals("Cash")) {
             try {
             	
             	
                 final String MERCHANT_ID = merchantId;
                 final String ACCESS_TOKEN = accessToken;
                 // Test Credit Card Info
                 final String CC_NUMBER = paymentVO.getCcNumber();
                 final String CVV_NUMBER = paymentVO.getCcCode();
                 final int EXP_MONTH = paymentVO.getExpMonth();
                 final int EXP_YEAR = paymentVO.getExpYear();
                 jsonObject = Main.testWebPay(MERCHANT_ID, ACCESS_TOKEN, CC_NUMBER, CVV_NUMBER, EXP_MONTH, EXP_YEAR,
                                 paymentVO.getOrderPosId(), orderTotalAmount ,tip);
             } catch (Exception e) {
                 e.printStackTrace();
                 
             }
             System.out.println("-----CloverController-----" + jsonObject);
             if (jsonObject != null) {
             	return jsonObject.toString();
             } else {
                 return new JSONObject("{}").toString();
             }
         } else {
             PaymentMode paymentMode = null;
             if (merchantId != null && !merchantId.isEmpty()) {
                 Merchant merchant = merchantService.findbyPosId(merchantId);
                 paymentMode = businessService.findByMerchantIdAndLabel(merchant.getId(), "Cash");
             }

             CashVO cashVO = new CashVO();
             cashVO.setAmount(orderTotalAmount);
             cashVO.setCashTendered(orderTotalAmount);
             cashVO.setOffline(1);
             cashVO.setResult("SUCCESS");
             cashVO.setTipAmount(tip);

             Tender tender = new Tender();
             tender.setEditable(0);
             tender.setEnabled(0);
             if (paymentMode != null) {
                 tender.setId(paymentMode.getPosPaymentModeId());
             }
             tender.setOpensCashDrawer(0);
             tender.setVisible(0);
             tender.setSupportsTipping(1);
             cashVO.setTender(tender);
             Gson gson = new Gson();
             String cashJson = gson.toJson(cashVO);
             System.out.println("----Cash input json---" + cashJson);
             String result = CloverUrlUtil.cashPayment(cashJson, merchantId, accessToken, paymentVO.getOrderPosId());
             System.out.println("---Cash result---" + result);
             if (result != null) {
              	return result;
              } else {
                  return new JSONObject("{}").toString();
              }
         }
        
         }else {
             return new JSONObject("{}").toString();
         }
        
        
        
        
        
        
        
    }

    @RequestMapping(value = "/orderConfirmation", method = RequestMethod.GET)
    public @ResponseBody String postOrderOnClover(@RequestParam("orderId") String orderId,
                    @RequestParam("type") String type, @RequestParam(required = false) String reason,
                    HttpServletResponse response) {
        boolean status = orderService.setOrderStatus(orderId, type, reason);
        if (status) {
            return "success";
        } else {
            return "OrderId doesn't exist";
        }
    }
    
    @RequestMapping(value = "/getFutureOrders", method = RequestMethod.GET)
    public @ResponseBody String getFutureOrders(@RequestParam("merchantId") String merchantId,HttpServletResponse response) {
        String  status= orderService.getFutureOrders(merchantId);
        if (status!=null) {
            return status;
        } else {
            return "Not Found";
        }
    }
    
    /*
    @RequestMapping(value = "/getMerchantData", method = RequestMethod.GET)
    public @ResponseBody String getMerchantData(String orderId, String merchantId, HttpServletResponse response) {
    	System.out.println("inside /getMerchantData"); 
     	 String data =  cloverService.getMerchantData(orderId,merchantId);
     	//String data =  cloverService.getMerchantData("MTAHJTWSNC4N8","9GK2J085R8A3A");
     	System.out.println(data);
     	return "redirect:"+UrlConstant.WEB_BASE_URL+data;
    }
*/
    @RequestMapping(value = "/getAllFutureOrders", method = RequestMethod.GET)
    public @ResponseBody String getAllFutureOrders(@RequestParam("merchantId") String merchantId,HttpServletResponse response) {
        String  status= orderService.getAllFutureOrders(merchantId);
        if (status!=null) {
            return status;
        } else {
            return "Not Found";
        }
    }


}
