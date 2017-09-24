package com.foodkonnekt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.service.CreateInventoryService;
import com.foodkonnekt.service.DeleteInventoryService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.UpdateInventoryService;
import com.foodkonnekt.service.WebhookAppService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class WebhooksController {

    @Autowired
    private UpdateInventoryService updateInventoryService;

    @Autowired
    private CreateInventoryService createInventoryService;

    @Autowired
    private DeleteInventoryService deleteInventoryService;

    @Autowired
    private WebhookAppService webhookAppService;

    @Autowired
    private CloverService cloverService;

    @Autowired
    private MerchantService merchantService;
    
    @Autowired
    private ItemmRepository itemRepository;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/webHooks", method = { RequestMethod.POST })
    public @ResponseBody void sendCode(HttpServletRequest request) {
    	 String actionType="";
         String merchantPOSID="";
         String objectId="";
         String objectType="";
        try {
            String json = IOUtils.toString(request.getInputStream());
           // MailSendUtil.webhookMail("Webhook Json --> "+json);
            if (!json.contains("verificationCode")) {
            	
            	/*if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Prod"))
            	 MailSendUtil.webhookMail("Check webhook event",json);*/
            	
                JSONObject Object = new JSONObject(json);
                String appId = Object.getString("appId");
                JSONObject merchantJsonObject = Object.getJSONObject("merchants");
                Iterator merchantIds = merchantJsonObject.keys();
                while (merchantIds.hasNext()) {
                     merchantPOSID = (String) merchantIds.next();
                    System.out.println("-------merchantPOSID----" + merchantPOSID);
                    Merchant merchant = merchantService.findbyPosId(merchantPOSID);
                    if(merchant!=null && merchant.getIsInstall()!=IConstant.SOFT_DELETE);{
                    JSONArray jsonArray = merchantJsonObject.getJSONArray(merchantPOSID);
                    for (int i = 0; i < jsonArray.length(); i++) {
                    	JSONObject objects = jsonArray.getJSONObject(i);
                         objectId = objects.getString("objectId");
                        String objectIds[] = objectId.split(":");
                         objectType = objectIds[0];
                        objectId = objectIds[1];
                         actionType = objects.getString("type");
                        webhook(appId, merchantPOSID, objectType, objectId, actionType);
                    }
                    }
                }
            } else {
                MailSendUtil.webhookMail("Webhook verificationCode from",json);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            StringWriter errors = new StringWriter();
            ioException.printStackTrace(new PrintWriter(errors));
            MailSendUtil.webhookMail("Webhook mail from","merchantId->"+merchantPOSID+" Object Id ->"+objectId+" Object Type ->"+objectType+" action type ->"+actionType+" Exception--> "+errors.toString());
        }
    }

    private void webhook(String appId, String merchantId, String objectType, String objectId, String actionType) {
        try {
            if (objectType.equals("I")) {
                if (actionType.equals("CREATE")) {
                    createInventoryService.createInventoryService(merchantId, objectType, objectId, actionType);
                }
                if (actionType.equals("UPDATE")) {
                	try{
                    System.out.println("--------Update Inventory inside WebhookController------" + merchantId);
                    updateInventoryService.updateInverty(merchantId, objectType, objectId, actionType);
                      }catch(Exception e){
                		
                	}
                }
                if (actionType.equals("DELETE")) {
                	try{
                    deleteInventoryService.deleteInventory(merchantId, objectType, objectId, actionType);
                	}catch(Exception e){
                		
                	}
                }

            } else if (objectType.equals("A")) {

                if (actionType.equals("CREATE")) {

                }
                if (actionType.equals("UPDATE")) {
                    Merchant result = merchantService.findbyPosId(merchantId);
                    Clover clover = new Clover();
                    clover.setMerchantId(merchantId);
                    clover.setAuthToken(result.getAccessToken());
                    clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
                    clover.setUrl(IConstant.CLOVER_URL);
                    Merchant merchant = cloverService.saveMerchant(clover);
                    System.out.println(merchant);
                }
                if (actionType.equals("DELETE")) {
                	
                	
                    webhookAppService.appUnInstall(merchantId);
                    System.out.println("merchant  " + merchantId + " uninstalled ");

                }
            } else if (objectType.equals("M")) {
                if (actionType.equals("UPDATE")) {
                    Merchant result = merchantService.findbyPosId(merchantId);
                    if(result!=null){
                    Clover clover = new Clover();
                    clover.setMerchantId(merchantId);
                    clover.setAuthToken(result.getAccessToken());
                    clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
                    clover.setUrl(IConstant.CLOVER_URL);
                    Merchant merchant = cloverService.saveMerchant(clover);

                    String paymentModes = CloverUrlUtil.getPaymentModes(clover);
                    cloverService.savePaymentMode(paymentModes, merchant);

                   /* String orderTypeDetail = CloverUrlUtil.getOderType(clover);
                    cloverService.saveOrderType(orderTypeDetail, merchant, clover);*/

                    String taxRates = CloverUrlUtil.getTaxRate(clover);
                    webhookAppService.saveTaxRate(taxRates, merchant);

                   
                    String openingHour = CloverUrlUtil.getOpeningHour(clover);
                    try{
                    cloverService.saveOpeningClosing(merchant, openingHour);
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
                    }
                }
            }
        } catch (Exception exception) {
            if (exception != null) {
            	exception.printStackTrace();
                StringWriter errors = new StringWriter();
                exception.printStackTrace(new PrintWriter(errors));
                MailSendUtil.webhookMail("Webhook mail from","merchantId->"+merchantId+" Object Id ->"+objectId+" Object Type ->"+objectType+" action type ->"+actionType+" Exception--> "+errors.toString());
            }
            exception.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/updateCloverMenu", method = RequestMethod.GET)
    public @ResponseBody String updateCloverMenu(@RequestParam("merchant_id") String merchantId, HttpServletResponse response) {
    	Merchant merchantCheck = merchantService.findbyPosId(merchantId);
    	
    	final Clover clover = new Clover();
        clover.setMerchantId(merchantId);
        clover.setAuthToken(merchantCheck.getAccessToken());
        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        
        String responseString = CloverUrlUtil.getAllItem(clover, merchantCheck);
        
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            List createdItemsList = new ArrayList();
            List updatedItemsList = new ArrayList();
            JSONArray jsonArray = jsonObject.getJSONArray("elements");
            int createdItems=0;
            int updatedItems=0;
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject categoryItem = jsonArray.getJSONObject(i);
                String itemName=null;
                if (categoryItem.toString().contains("name"))
                     itemName=categoryItem.getString("name");
                if (categoryItem!=null && categoryItem.toString().contains("id")){
                Item item = itemRepository.findByPosItemIdAndMerchantId(categoryItem.getString("id"), merchantCheck.getId());
                if (item == null){
                	createdItems++;
                	
                	createInventoryService.createInventoryService(merchantId, "I", categoryItem.getString("id"), "CREATE");
                	if(createdItemsList.size()%5==0){
                		itemName=itemName+"<br>";
                	}
                	createdItemsList.add(itemName+"  ");
                }else{
                	updatedItems++;
                	 updateInventoryService.updateInverty(merchantId, "I", categoryItem.getString("id"), "CREATE");
                	if(updatedItemsList.size()%5==0){
                		itemName=itemName+"<br>";
                	} 
                	updatedItemsList.add(itemName+"   ");
                }
                }
            }
            return "total item :-->"+jsonArray.length()+" new item created "+createdItems+"updated items "+updatedItems +"<br>Created items are --><br>"+createdItemsList+"<br>Updated items are --><br>"+updatedItemsList;
        } catch (Exception e) {
        	
            e.printStackTrace();
            return e.toString();
        }
    }
}
