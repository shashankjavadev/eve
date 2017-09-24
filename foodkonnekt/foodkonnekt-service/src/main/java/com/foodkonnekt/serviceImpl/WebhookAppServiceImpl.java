package com.foodkonnekt.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.CommonMail;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemTax;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.OrderType;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.ConvenienceFeeRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.OrderTypeRepository;
import com.foodkonnekt.repository.PaymentModeRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.ZoneRepository;
import com.foodkonnekt.service.WebhookAppService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Service
public class WebhookAppServiceImpl implements WebhookAppService {

	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private ItemmRepository itemmRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OpeningClosingDayRepository openingClosingDayRepository;

	@Autowired
	private OpeningClosingTimeRepository openingClosingTimeRepository;

	@Autowired
	private PaymentModeRepository paymentModeRepository;

	@Autowired
	private TaxRateRepository taxRateRepository;
	
	@Autowired
	private ItemTaxRepository itemTaxRepository;

	@Autowired
	private CustomerrRepository customerrRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ItemModifierGroupRepository itemModifierGroupRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ConvenienceFeeRepository convenienceFeeRepository;
	
	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemModifierRepository orderItemModifierRepository;
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	
	
	

	public void appUnInstall(String merchantId) {

		try{
		Merchant merchant = merchantRepository.findByPosMerchantId(merchantId);
		
		if(merchant!=null){
			merchant.setIsInstall(IConstant.SOFT_DELETE);
			merchant.setPosMerchantId(merchant.getPosMerchantId()+"_deleted");
			merchantRepository.save(merchant);
			
			
        	 MailSendUtil.productInstallationMail(merchant,"Clover","Uninstalled");
        	
		//  delete employee
			
			//String employeePosId=merchant.getEmployeePosId();
			Clover clover= new Clover();
			clover.setUrl(IConstant.CLOVER_URL);
			clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
			clover.setMerchantId(merchant.getPosMerchantId());
			clover.setAuthToken(merchant.getAccessToken());
			String employeeJson=CloverUrlUtil.getEmployeesFromClover(clover);
			//MailSendUtil.webhookMail("Employee Json ->"+employeeJson);
	        if(employeeJson.contains("{")){
	        	JSONObject employeeJsonObject = new JSONObject(employeeJson);
	        	if(employeeJsonObject.has("elements")){
		        JSONArray jSONArray = employeeJsonObject.getJSONArray("elements");
	        for (Object jObj : jSONArray) {
	            JSONObject jObject = (JSONObject) jObj;
	            

	            if(jObject.has("id") && jObject.has("name")){
					if(jObject.getString("name").equals("FoodKonnekt Employee")){
						String deleteEmployeeResponse =CloverUrlUtil.deleteEmployee(clover,jObject.getString("id"));
						System.out.println("Employee Delete Response ->"+deleteEmployeeResponse);
						//MailSendUtil.webhookMail("Employee Delete Response ->"+deleteEmployeeResponse);
					}
					
				}
	               
	            
	        }
	        	}
	        }
			
			/*if(employeeJson.contains("{")){
				JSONObject jObject = new JSONObject(employeeJson);
				if(jObject.has("id") && jObject.has("name")){
					if(jObject.getString("name").equals("FoodKonnekt Employee")){
						String deleteEmployeeResponse =CloverUrlUtil.deleteEmployee(clover,employeePosId);
						System.out.println("Employee Delete Response ->"+deleteEmployeeResponse);
					}
					
				}
			}*/
			  
			
//  order delete
		
		List<OrderType> orderTypes=orderTypeRepository.findByMerchantId(merchant.getId());
		for(OrderType orderType:orderTypes){
			String deleteZone =CloverUrlUtil.deleteOrderType(merchant.getPosMerchantId(), merchant.getAccessToken(), orderType.getPosOrderTypeId());
			System.out.println(deleteZone);
		}
		  
		/*List<OrderR> orderRs = orderRepository.findByMerchantId(merchant.getId());
        for(OrderR orderR:orderRs){
      	  List<OrderItem> orderItems=orderItemRepository.findByOrderId(orderR.getId());
      	         for(OrderItem orderItem:orderItems) {
      	        	List<OrderItemModifier> orderItemModifiers= orderItemModifierRepository.findByOrderItemId(orderItem.getId());
      	        	orderItemModifierRepository.delete(orderItemModifiers);
      	        	orderItem.setOrder(null);
      	        	orderItem.setItem(null);
      	         }
      	       orderR.setMerchant(null);
      	      	orderR.setCustomer(null);
      	      
     	  orderItemRepository.delete(orderItems);
      	
        }
        
		orderRepository.delete(orderRs);*/
		/*List<Customer> customers = customerrRepository.findByMerchantId(merchant.getId());
		for (Customer customer : customers) {
			List<Address> addresses = addressRepository.findByCustomerId(customer.getId());
			addressRepository.delete(addresses);
			customer.setAddresses(null);
			customer.setMerchantt(null);
		}

		customerrRepository.delete(customers);*/

		/*List<Item> items = itemmRepository.findByMerchantId(merchant.getId());
		for (Item item : items) {
			List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
			for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
				itemModifierGroup.setItem(null);
				itemModifierGroup.setModifierGroup(null);
				itemModifierGroupRepository.delete(itemModifierGroup);
			}
			
			item.setMerchant(null);
			item.setCategories(null);
		}
		
		//itemmRepository.delete(items);
		
		List<Category> categories = categoryRepository.findByMerchantId(merchant.getId());
		categoryRepository.delete(categories);
		System.out.println("categories");*/
		

		

		List<OpeningClosingDay> openingClosingDays = openingClosingDayRepository.findByMerchantId(merchant.getId());
		for (OpeningClosingDay openingClosingDay : openingClosingDays) {
			List<OpeningClosingTime> dayTimis = openingClosingTimeRepository
					.findByOpeningClosingDayId(openingClosingDay.getId());
			openingClosingTimeRepository.delete(dayTimis);
		}

		/*List<TaxRates> taxRates = taxRateRepository.findByMerchantId(merchant.getId());

		taxRateRepository.delete(taxRates);*/

		List<PaymentMode> paymentModes = paymentModeRepository.findByMerchantId(merchant.getId());

		paymentModeRepository.delete(paymentModes);

		openingClosingDayRepository.delete(openingClosingDays);
		/*List<Address> addresses = addressRepository.findByMerchantId(merchant.getId());

		addressRepository.delete(addresses);*/
		
		ConvenienceFee convenienceFee = convenienceFeeRepository.findByMerchantId(merchant.getId());
		if(convenienceFee!=null){
		String deleteConvFee =CloverUrlUtil.deleteItem(merchant.getPosMerchantId(), merchant.getAccessToken(), convenienceFee.getConvenienceFeeLineItemPosId());
		System.out.println(deleteConvFee);
		convenienceFeeRepository.delete(convenienceFee);
		}
		
		List<Zone> zones=zoneRepository.findByMerchantId(merchant.getId());
		for(Zone zone:zones){
			
			String deleteZone =CloverUrlUtil.deleteItem(merchant.getPosMerchantId(), merchant.getAccessToken(), zone.getDeliveryLineItemPosId());
			System.out.println(deleteZone);
		}
		zoneRepository.delete(zones);
		
		
		//orderTypeRepository.delete(orderTypes);
		//merchantRepository.delete(merchant);
		}
		}catch(Exception e){
			System.out.println("Exception During Unstallation -> "+e);
		}
	}
	
	 public void saveTaxRate(String taxRates, Merchant merchant) {
	        List<TaxRates> rates = setTaxRates(taxRates, merchant);
	        if (rates != null){
	            taxRateRepository.save(rates);
	            for(TaxRates rate:rates){
	            	if(rate.getIsDefault()==IConstant.BOOLEAN_TRUE){
	            		List<Item> items = itemmRepository.findByMerchantId(merchant.getId());
	            		for(Item item:items){
	            			if(item.getIsDefaultTaxRates()==true){
	            				List<ItemTax> itemTaxs=itemTaxRepository.findByItemIdAndTaxRatesId(item.getId(), rate.getId());
	            			if(itemTaxs==null || itemTaxs.isEmpty() || itemTaxs.size()==0){
	            				ItemTax itemTax= new ItemTax();
	            				itemTax.setItem(item);
	            				itemTax.setTaxRates(rate);
	            				itemTaxRepository.save(itemTax);
	            				
	            			}
	            			}
	            		}
	            	}
	                 
	                }
	        
	            
	        }
	    }
	 
	 public void unMapTax(String taxRates, Merchant merchant){
		 try{
		 List<TaxRates> rates=taxRateRepository.findByMerchantId(merchant.getId());
		 for(TaxRates taxRate:rates){
			 if(!taxRates.contains(taxRate.getPosTaxRateId())){
				 
				 List<ItemTax> itemTaxs=itemTaxRepository.findByTaxRatesId(taxRate.getId());
				 for(ItemTax itemTax:itemTaxs){
					 itemTax.setItem(null);
					 itemTax.setTaxRates(null);
					 itemTaxRepository.delete(itemTax);
				 }
				 
				 taxRate.setMerchant(null);
				 taxRate.setItems(null);
				 taxRateRepository.delete(taxRate);
				 CommonMail commonMail = new CommonMail();
			        commonMail.setToEmail("sumit@mkonnekt.com");
			        commonMail.setSubject("unmapped tax for "+UrlConstant.FOODKONNEKT_APP_TYPE);
			        commonMail.setBody(" MerchantId-> "+merchant.getName()+" taxrate Name"+taxRate.getName());
			        MailSendUtil.sendMail(commonMail);
			 }else{
				 
			 }
		 }
		 }catch(Exception e){
			 System.out.println(e);
			 
			 CommonMail commonMail = new CommonMail();
		        commonMail.setToEmail("sumit@mkonnekt.com");
		        commonMail.setSubject("unmapped tax Exception for "+UrlConstant.FOODKONNEKT_APP_TYPE);
		        commonMail.setBody(" MerchantId-> "+merchant.getName()+" e"+e.getMessage());
		        MailSendUtil.sendMail(commonMail);
		 }
	 }
	 
	 public List<TaxRates> setTaxRates(String taxRates, Merchant merchant) {
		 unMapTax( taxRates,  merchant);
	        JSONObject jObject = new JSONObject(taxRates);
	        if(jObject.toString().contains("elements")){
	        JSONArray jSONArray = jObject.getJSONArray("elements");
	        List<TaxRates> rates = new ArrayList<TaxRates>();
	        for (Object jObj : jSONArray) {
	            JSONObject taxRate = (JSONObject) jObj;
	            TaxRates rate = taxRateRepository
	                            .findByMerchantIdAndPosTaxRateId(merchant.getId(), taxRate.getString("id"));

	            if (rate == null){
	                rate = new TaxRates();
	                
	            }

	            if (taxRate.toString().contains("name"))
	                rate.setName(taxRate.getString("name"));

	            if (taxRate.toString().contains("id"))
	                rate.setPosTaxRateId(taxRate.getString("id"));

	            if (taxRate.toString().contains("rate"))
	                rate.setRate(taxRate.getDouble("rate") / 100000);

	            rate.setMerchant(merchant);

	            if (taxRate.toString().contains("isDefault"))
	                if (taxRate.getBoolean("isDefault")) {
	                    rate.setIsDefault(IConstant.BOOLEAN_TRUE);
	                } else {
	                    rate.setIsDefault(IConstant.BOOLEAN_FALSE);
	                }
	            rates.add(rate);
	        }
	        return rates;
	        }
	        return null;
	    }
		
}
