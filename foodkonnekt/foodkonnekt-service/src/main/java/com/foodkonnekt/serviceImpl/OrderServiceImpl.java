package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodkonnekt.clover.vo.AllOrderVo;
import com.foodkonnekt.clover.vo.CloverOrderVO;
import com.foodkonnekt.clover.vo.ItemVO;
import com.foodkonnekt.clover.vo.Modifications;
import com.foodkonnekt.clover.vo.OrderItemModifierViewVO;
import com.foodkonnekt.clover.vo.OrderItemVO;
import com.foodkonnekt.clover.vo.OrderItemViewVO;
import com.foodkonnekt.clover.vo.OrderJson;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryDto;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.CategoryTiming;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.CouponRedeemedDto;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.ItemTax;
import com.foodkonnekt.model.ItemTiming;
import com.foodkonnekt.model.Koupons;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantOrders;
import com.foodkonnekt.model.ModifierDto;
import com.foodkonnekt.model.ModifierGroupDto;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.OrderDiscount;
import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.OrderType;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.CategoryTimingRepository;
import com.foodkonnekt.repository.ConvenienceFeeRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemTimingRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantOrdersRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.MerchantSubscriptionRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.repository.OrderDiscountRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.OrderTypeRepository;
import com.foodkonnekt.repository.PaymentModeRepository;
import com.foodkonnekt.repository.PickUpTimeRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.repository.ZoneRepository;
import com.foodkonnekt.service.OrderService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.OrderUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class OrderServiceImpl extends Thread implements OrderService, Runnable {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemModifierRepository orderItemModifierRepository;

    @Autowired
    private ItemmRepository itemmRepository;
    
    @Autowired
    private CategoryItemRepository categoryItemRepository;
    
    @Autowired
    private ItemTimingRepository itemTimingRepository;
    
    @Autowired
    private CategoryTimingRepository categoryTimingRepository;
    
    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;

    @Autowired
    private ModifiersRepository modifiersRepository;

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository   itemModifiersRepository;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    @Autowired
    private OrderItemModifierRepository itemModifierRepository;

    @Autowired
    private MerchantSubscriptionRepository merchantSubscriptionRepository;

    @Autowired
    private MerchantOrdersRepository merchantOrdersRepository;

    @Autowired
    private CustomerrRepository customerrRepository;

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private ConvenienceFeeRepository convenienceFeeRepository;

    @Autowired
    private PickUpTimeRepository pickUpTimeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ZoneRepository zoneRepository;
    
    @Autowired
    private VendorRepository vendorRepository;

    public static String name1;
    public static String paymentYtpe1;
    public static String orderPosId1;
    public static String subTotal1;
    public static String tax1;
    public static String orderDetails1;
    public static String email1;
    public static Double orderPrice1;
    public static String note1;
    public static String merchantName1;
    public static String merchantLogo1;
    public static String orderType1;
    public static String convenienceFeeValue1 = "0";
    public static String avgTime = "30";
    public static String deliverFee = "0";
    public static Double orderDiscount = 0.0;
    public static Double tipAmount = 0.0;

    public List<OrderR> getAllCategory(Clover clover) {
        return orderRepo.findAll();
    }

    /**
     * Find by customerId and merchantId
     */
    public List<OrderR> getAllOrder(Integer customerId, Integer merchantId) {
        List<OrderR> orderRs = orderRepo.findByCustomerIdAndMerchantId(customerId, merchantId);
        Collections.sort(orderRs, new Comparator<OrderR>() {
            public int compare(OrderR m1, OrderR m2) {
                return m2.getCreatedOn().compareTo(m1.getCreatedOn());
            }
        });
        return findOrderDetails(orderRs);
    }

    public List<CategoryDto> findCategoriesByMerchantId(Merchant merchant) {
    	String timeZoneCode="America/Chicago";
    	if(merchant!=null && merchant.getId()!=null){
    	if( merchant.getTimeZone()!=null && merchant.getTimeZone().getTimeZoneCode()!=null){
    		
    		timeZoneCode=merchant.getTimeZone().getTimeZoneCode();
    	}
    	String currentDay=DateUtil.getCurrentDayForTimeZone(timeZoneCode);
    	String currentTime=DateUtil.getCurrentTimeForTimeZone(timeZoneCode);
    	
    	List<Category> categories = categoryRepository.findByMerchantIdOrderBySortOrderAscByQuery(merchant.getId());
        List<CategoryDto> finalCategories = new ArrayList<CategoryDto>();
        for (Category category : categories) {
        	CategoryTiming categoryTiming=categoryTimingRepository.findByCategoryIdAndDay(category.getId(), currentDay);
        	boolean categoryTimingStatus=false;
        	if(categoryTiming!=null ){
        		if(categoryTiming.isHoliday()){
        			categoryTimingStatus=false;
        		}else{
        			String startTime=DateUtil.convert12hoursTo24HourseFormate(categoryTiming.getStartTime());
        			String endTime=DateUtil.convert12hoursTo24HourseFormate(categoryTiming.getEndTime());
        			categoryTimingStatus=OrderUtil.isTimeBetweenTwoTime(startTime + ":00", endTime + ":00",currentTime);
        		}
        	}else{
        		categoryTimingStatus=true;
        	}
        	if(categoryTimingStatus && category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
           // List<Item> items = itemmRepository.findByCategoriesId(category.getId());
           /* List<ItemDto> itemDtos=findCategoryItems(category.getId(), merchant);;
            if ( itemDtos!=null && itemDtos.size() >0) {*/
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setCategoryName(category.getName());
                categoryDto.setCategoryStatus(category.getItemStatus());
                finalCategories.add(categoryDto);
            /*}*/
        	}
        }
        return finalCategories;
    	}else{
    		return null;
    	}

    }
    
    public List<CategoryDto> findCategoriesByMerchantIdWithLimit(Integer merchantId,Integer page) {
    	
    	Pageable topTen = new PageRequest(0, 5);
    	
    	Merchant merchant=merchantRepository.findById(merchantId);
    	String timeZoneCode="America/Chicago";
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getTimeZoneCode()!=null){
    		/*hourDifference=merchant.getTimeZone().getHourDifference();
    		if(merchant.getTimeZone().getMinutDifference()!=null)
    		minutDifference=merchant.getTimeZone().getMinutDifference();*/
    		timeZoneCode=merchant.getTimeZone().getTimeZoneCode();
    	}
    	String currentDay=DateUtil.getCurrentDayForTimeZone(timeZoneCode);
    	String currentTime=DateUtil.getCurrentTimeForTimeZone(timeZoneCode);
    	if(merchant!=null){
    	//List<Category> categories = categoryRepository.findByMerchantIdOrderBySortOrderAsc(merchantId);
    		Page<Category> categories = categoryRepository.findByMerchantIdOrderBySortOrderAsc(merchantId, topTen);
        List<CategoryDto> finalCategories = new ArrayList<CategoryDto>();
        for (Category category : categories) {
        	CategoryTiming categoryTiming=categoryTimingRepository.findByCategoryIdAndDay(category.getId(), currentDay);
        	boolean categoryTimingStatus=false;
        	if(categoryTiming!=null ){
        		if(categoryTiming.isHoliday()){
        			categoryTimingStatus=false;
        		}else{
        			String startTime=DateUtil.convert12hoursTo24HourseFormate(categoryTiming.getStartTime());
        			String endTime=DateUtil.convert12hoursTo24HourseFormate(categoryTiming.getEndTime());
        			categoryTimingStatus=OrderUtil.isTimeBetweenTwoTime(startTime + ":00", endTime + ":00",currentTime);
        		}
        	}else{
        		categoryTimingStatus=true;
        	}
        	if(categoryTimingStatus && category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
           // List<Item> items = itemmRepository.findByCategoriesId(category.getId());
            List<ItemDto> itemDtos=findCategoryItems(category.getId(), merchant);;
            if ( itemDtos!=null && itemDtos.size() >0) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setCategoryName(category.getName());
                categoryDto.setCategoryStatus(category.getItemStatus());
                finalCategories.add(categoryDto);
            }
        	}
        }
        return finalCategories;
    	}else{
    		return null;
    	}

    }
  
  
    /**
     * Find Items based in cetegoryId
     */

    /*public List<CategoryDto> findMenuItems(List<CategoryDto> categories) {

        ArrayList<CategoryDto> newlist = new ArrayList<CategoryDto>();
        for (CategoryDto categoryDto : categories) {
            List<Item> items = itemmRepository.findByCategoriesId(categoryDto.getId());
            List<ItemDto> itemDtos = new ArrayList<ItemDto>();
            if (items.size() != 0 && categoryDto.getCategoryStatus() == 0) {
                newlist.add(categoryDto);
                for (Item item : items) {
                    if (item.getItemStatus() != null && item.getItemStatus() == 0) {
                        ItemDto itemDto = new ItemDto();
                        itemDto.setId(item.getId());
                        itemDto.setItemName(item.getName());
                        itemDto.setItemTax(getItemTaxPrice(item.getId()));
                        itemDto.setPrice(item.getPrice());
                        itemDto.setItemPosId(item.getPosItemId());
                        itemDto.setAllowModifierLimit(item.getAllowModifierLimit());
                        List<ModifierGroupDto> groupDtos = getModifierGroup(item.getId(), item.getAllowModifierLimit());
                        itemDto.setModifierGroupDtos(groupDtos);
                        itemDtos.add(itemDto);
                    }
                }
                categoryDto.setItemDtos(itemDtos);
            }
        }

        // categories.remove(newlist);
        return newlist;
    }*/

    /**
     * Calculate tax based on item
     * 
     * @param itemId
     * @return
     */
    private double getItemTaxPrice(Integer itemId) {
        double tax = 0;
        try {
            if (itemId != null) {
                List<ItemTax> itemTaxs = itemTaxRepository.findByItemId(itemId);
                if (!itemTaxs.isEmpty()) {
                    for (ItemTax itemTax : itemTaxs) {
                        tax = tax + itemTax.getTaxRates().getRate();
                    }
                }
            }
            return tax;
        } catch (Exception e) {
            return tax;
        }
    }

    /**
     * Find modifier group based on itemId
     * 
     * @param itemId
     * @return
     */
    public List<ModifierGroupDto> getModifierGroup(Integer itemId, Integer allowModifierLimit,Merchant merchant) {
        List<ModifierGroupDto> groupDtos = new ArrayList<ModifierGroupDto>();
        List<ItemModifierGroup> groups = itemModifierGroupRepository.findByItemIdOrderBySortOrderAsc(itemId);
        for (ItemModifierGroup group : groups) {
        	if(group.getModifierGroup()!=null && group.getModifierGroup().getShowByDefault()!=IConstant.SOFT_DELETE){
        	if(group.getModifierGroupStatus()!=null && group.getModifierGroupStatus()==IConstant.BOOLEAN_TRUE){
            ModifierGroupDto groupDto = new ModifierGroupDto();
            groupDto.setId(group.getModifierGroup().getId());

            if (allowModifierLimit != null && allowModifierLimit == IConstant.BOOLEAN_TRUE)
                groupDto.setModifiersLimit(group.getModifiersLimit());
            groupDto.setIsMaxLimit(group.getIsMaxLimit());
            groupDto.setModifierGroupName(group.getModifierGroup().getName());
            
            List<ModifierDto> modifierDtos = getModifierByModifierGroup(group.getModifierGroup().getId(), itemId,merchant);
            groupDto.setModifierDtos(modifierDtos);
            groupDtos.add(groupDto);
        	} }
        }
        return groupDtos;
    }

    /**
     * Get modifiers based on modifierGroup id
     * 
     * @param modifierGroupId
     * @return
     */
    private List<ModifierDto> getModifierByModifierGroup(Integer modifierGroupId,Integer itemId,Merchant merchant) {
        List<ModifierDto> modifierDtos = new ArrayList<ModifierDto>();
        
        //List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(modifierGroupId);
        List<ItemModifiers> itemModifiers=itemModifiersRepository.findByModifierGroupIdAndItemId(modifierGroupId,itemId);
        for (ItemModifiers itemModifier : itemModifiers) {
        	if(itemModifier.getModifierStatus()==IConstant.BOOLEAN_TRUE){
            ModifierDto modifierDto = new ModifierDto();
            Modifiers mod=itemModifier.getModifiers();
            modifierDto.setId(mod.getId());
            modifierDto.setModifierName(mod.getName());
            modifierDto.setPrice(mod.getPrice());
            
            modifierDto.setModifierPosId(mod.getPosModifierId());
            if(merchant!=null &&merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.NON_POS){
            	modifierDto.setModifierPosId(Integer.toString(mod.getId()));
              }
            modifierDtos.add(modifierDto);
        	}
        }
        return modifierDtos;
    }

    /**
     * Save place order information into database
     */
    public String saveOrder(JSONObject jObject, String finalJson, Customer customer, Merchant merchant,
                    Double discount, String convenienceFee, String deliveryItemPrice, String avgDeliveryTime,String orderType) {
    	OrderR orderR=null;
    	Gson gson = new Gson();
    	 CloverOrderVO cloverOrderVO = gson.fromJson(finalJson, CloverOrderVO.class);
    	if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==1){
         orderR = OrderUtil.getObjectFromJson(jObject, customer, merchant);
         /*if (orderR!=null && discount != 0) {
             CloverUrlUtil.applyCoupon(discount, merchant, orderR.getOrderPosId());
         }*/
    	}else{
    		 orderR = new OrderR();
	        orderR.setCustomer(customer);
	        orderR.setMerchant(merchant);
	        orderR.setIsDefaults(0);
	        orderR.setOrderPrice(Double.parseDouble(cloverOrderVO.getOrderVO().getTotal())/100);
	        orderRepo.save(orderR);
	        orderR.setOrderPosId(Integer.toString(orderR.getId()));
    	}
        if (null != orderR) {
        	Integer hourDifference=0;
        	Integer minutDifference=0;
        	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getHourDifference()!=null){
        		hourDifference=merchant.getTimeZone().getHourDifference();
        		if(merchant.getTimeZone().getMinutDifference()!=null)
            		minutDifference=merchant.getTimeZone().getMinutDifference();
        	}
            Date currentDate=new Date();
            Calendar cal = Calendar.getInstance();
         // remove next line if you're always using the current time.
           cal.setTime(currentDate);
           cal.add(Calendar.HOUR, hourDifference);
           cal.add(Calendar.MINUTE, minutDifference);
           currentDate = cal.getTime();
            orderR.setCreatedOn(currentDate);
           // Gson gson = new Gson();
           // CloverOrderVO cloverOrderVO = gson.fromJson(finalJson, CloverOrderVO.class);
            orderR.setConvenienceFee(convenienceFee);
            orderR.setDeliveryFee(deliveryItemPrice);
            orderR.setOrderDiscount(discount);
            orderR.setOrderAvgTime(avgDeliveryTime);
            orderR.setOrderType(orderType);
            orderRepo.save(orderR);
            for (OrderItemVO item : cloverOrderVO.getOrderItemVOs()) {
                OrderItem orderItem = new OrderItem();
                Item item2 =null;
                if(merchant!=null &&merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.NON_POS){
                	Integer itemId=0;
                	try{
                		itemId=Integer.parseInt(item.getItem().getId());
                	}catch(Exception e){
                		
                	}
                	item2= itemmRepository.findByIdAndMerchantId(itemId,merchant.getId());
               }else{
            	    item2 = itemmRepository.findByPosItemIdAndMerchantId(item.getItem().getId(),merchant.getId());
               }
                
                orderItem.setItem(item2);
                orderItem.setOrder(orderR);
                orderItem.setQuantity(Integer.parseInt(item.getUnitQty()));
                orderItemRepository.save(orderItem);
                List<OrderItemModifier> itemModifiers = new ArrayList<OrderItemModifier>();
                for (Modifications modifications : item.getModifications()) {
                    OrderItemModifier itemModifier = new OrderItemModifier();
                    
                    if(merchant!=null &&merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.NON_POS){
                    	Integer modifierId=0;
                    	try{
                    		modifierId=Integer.parseInt(modifications.getModifier().getId());
                    	}catch(Exception e){
                    		
                    	}
                    	itemModifier.setModifiers(modifiersRepository.findByIdAndMerchantId(modifierId, merchant.getId()));
                   }else{
                	   itemModifier.setModifiers(modifiersRepository.findByPosModifierIdAndMerchantId(modifications
                               .getModifier().getId(), merchant.getId()));
                   }
                    
                    itemModifier.setOrderItem(orderItem);
                    itemModifier.setQuantity(Integer.parseInt(item.getUnitQty()));
                    itemModifiers.add(itemModifier);
                }
                itemModifierRepository.save(itemModifiers);
            }
 
            if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.POS_CLOVER ){
            
            if (orderR.getOrderPosId() != null) {

                Integer merchantSubscriptionId = merchantSubscriptionRepository.findByMerchantId(merchant.getId());
                MerchantOrders merchantOrders = merchantOrdersRepository
                                .findByMerchantSubscriptionId(merchantSubscriptionId);
                // call metered API for increase order count
                if(merchantOrders!=null){
                long difference = DateUtil.findDifferenceBetweenTwoDates(merchantOrders.getEndDate(),
                                DateUtil.currentDate());
  
                if ((difference > 0)&& (merchantOrders.getOrderCount() <= merchantOrders.getMerchantSubscription()
                                                .getSubscription().getOrderLimit())) {
                    System.out.println("-------Metered if block----");
                } else {
                    System.out.println("-------Metered else block----");
                    merchant.setSubscription(merchantOrders.getMerchantSubscription().getSubscription());
                    String response = CloverUrlUtil.addMteredPrice(merchant);
                    System.out.println("-------Metered API response-----" + response);
                }
                merchantOrders.setOrderCount(merchantOrders.getOrderCount() + 1);
                merchantOrdersRepository.save(merchantOrders);
                }else{
                	MailSendUtil.sendErrorMailToAdmin("Merchant '"+merchant.getName()+"'doesn't have any Subscription");
                }
            }
            }
            return orderR.getOrderPosId();
        } else {
            return null;
        }
    }

    /**
     * Update order status after payment
     */
    @SuppressWarnings("deprecation")
    public boolean updateOrderStatus(String orderPosId, String result, Integer customerId, String paymentYtpe,
                    String subTotal, String tax, String name, String email, String merchantName, String merchantLogo,
                    String orderType, double tip, String orderPrice, String futureOrderType, String futureDate,
                    String futureTime, Merchant merchant,String listOfALLDiscounts) {
        try {
            if (result.contains("result") && !result.contains("DECLINED")) {
                JSONObject jObject = new JSONObject(result);
                JSONArray jsonitemForDiscountArray = null;
                if(listOfALLDiscounts!= null && !listOfALLDiscounts.equals("")){
                	  jsonitemForDiscountArray = new JSONArray(listOfALLDiscounts);
                }
               
                if (jObject.toString().contains("result")) {
                    String status = jObject.getString("result");
                    String paymentPOSID = null;
                    if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.POS_CLOVER){
                       
                    if (status.equals("APPROVED")) {
                        if (jObject.toString().contains("paymentId")) {
                            paymentPOSID = jObject.getString("paymentId");
                        }
                    } else {
                        if (jObject.toString().contains("id")) {
                            paymentPOSID = jObject.getString("id");
                        }
                    }

                    }
                    
                  
                    System.out.println("payment json-----" + jObject);
                    if ("APPROVED".equals(status) || "SUCCESS".equals(status)) {
                        OrderR orderR = orderRepo.findByCustomerIdAndOrderPosId(customerId, orderPosId);

                         merchant = orderR.getMerchant();
                         
                         Vendor vendor = vendorRepository.findOne(merchant.getOwner().getId());
                        ConvenienceFee convenienceFee = null;
                        PickUpTime pickUpTime = null;
                        String convenienceFeeValue = "0";
                        String pickUpTimeValue = "0";
                        if (null != merchant) {
                            convenienceFee = convenienceFeeRepository.findByMerchantId(merchant.getId());
                            if (null != convenienceFee) {
                                convenienceFeeValue = convenienceFee.getConvenienceFee();
                            }
                            if ("pickup".equals(orderType.toLowerCase())) {
                                pickUpTime = pickUpTimeRepository.findByMerchantId(merchant.getId());
                                if (pickUpTime != null) {
                                	try {
                                		Integer.parseInt(pickUpTime.getPickUpTime());
                                		avgTime = pickUpTime.getPickUpTime();
                                	} catch (NumberFormatException e) {
                                	    avgTime = "45";
                                	}
                                    
                                }
                            } else {
                            	try {
                            		Integer.parseInt(orderR.getOrderAvgTime());
                            		avgTime = orderR.getOrderAvgTime();
                            	} catch (NumberFormatException e) {
                            	    avgTime = "45";
                            	}
                                
                            }
                        }
                        if (orderPrice != null && !orderPrice.isEmpty()) {
                            double orderTotalPrice = Math.round(Double.valueOf(orderPrice) * 100.0) / 100.0;

                            orderTotalPrice = orderTotalPrice + tip;
                            orderR.setOrderPrice(orderTotalPrice);
                        }

                        orderR.setTipAmount(tip);
                        orderR.setIsDefaults(0);
                        orderR.setSubTotal(subTotal);
                        orderR.setTax(tax);
                        orderR.setPosPaymentId(paymentPOSID);
                        orderR.setPaymentMethod(paymentYtpe);
                        orderR.setOrderType(orderType);
                        orderR.setOrderAvgTime(avgTime);
                        Integer hourDifference=0;
                    	Integer minutDifference=0;
                    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getHourDifference()!=null){
                    		hourDifference=merchant.getTimeZone().getHourDifference();
                    		if(merchant.getTimeZone().getMinutDifference()!=null)
                        		minutDifference=merchant.getTimeZone().getMinutDifference();
                    	}
                        Date currentDate=new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(currentDate);
                       cal.add(Calendar.HOUR, hourDifference);
                       cal.add(Calendar.MINUTE, minutDifference);
                       currentDate = cal.getTime();
                        orderR.setCreatedOn(currentDate);
                        if (("on".equals(futureOrderType)) && (!"select".equals(futureDate))) {
                            orderR.setIsFutureOrder(IConstant.BOOLEAN_TRUE);
                            Date futureDateTime = DateUtil.futureDateAndTime(futureDate, futureTime);
                            orderR.setFulfilled_on(futureDateTime);
                        } else {
                            orderR.setIsFutureOrder(IConstant.BOOLEAN_FALSE);
                            Date fulfilled_on = new Date();
                            fulfilled_on.setMinutes(fulfilled_on.getMinutes() + Integer.valueOf(avgTime));
                            orderR.setFulfilled_on(fulfilled_on);
                        }
                        final OrderR savedOrder=orderRepo.save(orderR);

                        if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Prod")){
                        final Merchant merchantForThread=merchant;
                        
                        new  Thread(new Runnable() {
							
							public void run() {
								if(merchantForThread!=null && merchantForThread.getActiveCustomerFeedback()!=null && merchantForThread.getActiveCustomerFeedback()==1){
								
								DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDD);
								DateFormat dateFormat1 = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
								java.util.Date date = new java.util.Date();
								
								String date1 = dateFormat.format(date).toString();
								System.out.println("date1----"+date1);
								String s1 = date1+IConstant.STARTDATE;
								String s2 = date1+IConstant.ENDDATE;
								
								
								java.util.Date startDate;
								try {
									startDate = dateFormat1.parse(s1);
									Date endDate = dateFormat1.parse(s2);
								Integer customerId=	savedOrder.getCustomer().getId();
								List<OrderR> orderList=	orderRepo.findByStartDateAndEndDateAndCustomerId(startDate, endDate, customerId);
									System.out.println("order list -->"+orderList.size());	
										MailSendUtil.sendOrderReceiptAndFeedbackEmail(savedOrder, orderList);
								}catch(Exception e){
									
								}
								}else{
									if(merchantForThread!=null)
									MailSendUtil.webhookMail("Kritiq Mail faied", merchantForThread.getActiveCustomerFeedback()+" ");
									else
										MailSendUtil.webhookMail("Kritiq Mail faied", "merchant is null for thread ");
								}
								
							}
						}).start();
                        
                        }
                       
                        if(jsonitemForDiscountArray!= null ){
                        	List<Koupons> koupons = new ArrayList<Koupons>();
                        	Date date = orderR.getCreatedOn();
                        for(int i=0; i<jsonitemForDiscountArray.length();i++){
                        	Koupons koupon = new Koupons();
                        	  OrderDiscount orderDiscount1 = new OrderDiscount();
                        	JSONObject object = jsonitemForDiscountArray.getJSONObject(i);
                        	orderDiscount1.setOrder(orderR);
                        	orderDiscount1.setCouponDate(date.toString());
                        	orderDiscount1.setCouponCode(object.getString("couponUID"));
                        	orderDiscount1.setDiscount(object.getDouble("discount"));
                        	orderDiscount1.setInvenotryType(object.getString("inventoryLevel"));
                        	if(orderR.getCustomer()!=null)
                        	orderDiscount1.setCustomer(orderR.getCustomer());
                        	orderDiscountRepository.save(orderDiscount1);
                        	koupon.setKouponCode(object.getString("couponUID"));
                        	koupon.setDiscount(object.getDouble("discount"));
                        	koupons.add(koupon);
                        }
                        //Koupons koupons = new Koupons();
                        
                        CouponRedeemedDto couponRedeemedDto = new CouponRedeemedDto();
                        couponRedeemedDto.setAppliedDate(date.toString());
                        couponRedeemedDto.setKoupons(koupons);
                        couponRedeemedDto.setMerchantUId(merchant.getMerchantUid());
                        couponRedeemedDto.setOrderId(orderR.getId().toString());
                        couponRedeemedDto.setVendorUId(vendor.getVendorUid());
                        couponRedeemedDto.setCustomerContactNo(orderR.getCustomer().getPhoneNumber());
                        
                        String url = "http://localhost:8080/koupons/couponRedeemed";
           	    	 Gson gson = new Gson();
           	    	 String couponRedeemedJson = gson.toJson(couponRedeemedDto);
           	    	 System.out.println("couponRedeemedJson::::-"+couponRedeemedJson);
           	    	 String response = OrderUtil.postCouponData(url, couponRedeemedJson);
           	    	 System.out.println("couponRedeemedJson RESPONSE::::-"+response);
                        } 
                        
                        String orderDetails = "";
                        Map<String, Map<String, String>> notf = new HashMap<String, Map<String, String>>();
                        Map<String, String> notification = new HashMap<String, String>();
                        notification.put("appEvent", "notification");
                        Map<String, String> order = new HashMap<String, String>();
                        order.put("total", Double.toString(orderR.getOrderPrice()));
                        order.put("tax", tax);
                        List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
                        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
                        for (OrderItem orderItem : orderItems) {
                            if (null != orderItem) {
                                if (null != orderItem.getItem()) {
                                    String items = "<tr style='font-weight:600;text-transform:capitalize;font-family:arial'><td width='200px;'>"
                                                    + orderItem.getItem().getName()
                                                    + "</td><td width='100px;' style='text-align:center'>"
                                                    + orderItem.getQuantity()
                                                    + "</td><td width='100px;' style='text-align:center'>"
                                                    + "$"
                                                    + orderItem.getItem().getPrice() + "</td></tr>";
                                    orderDetails = orderDetails + "" + "<b>" + items + "</b>";
                                    List<Map<String, String>> extraList = new ArrayList<Map<String, String>>();
                                    Map<String, String> poduct = new HashMap<String, String>();
                                    poduct.put("product_id", orderItem.getItem().getPosItemId());
                                    poduct.put("name", orderItem.getItem().getName());
                                    poduct.put("price", Double.toString(orderItem.getItem().getPrice()));
                                    poduct.put("qty", Integer.toString(orderItem.getQuantity()));
                                    List<OrderItemModifier> itemModifiers = itemModifierRepository
                                                    .findByOrderItemId(orderItem.getId());
                                    for (OrderItemModifier itemModifier : itemModifiers) {
                                        String modifiers = "<tr style='text-transform:capitalize;font-family:arial;margin-top:5px;font-size:12px'><td width='200px;'>"
                                                        + itemModifier.getModifiers().getName()
                                                        + "</td><td width='100px;' style='text-align:center'>"
                                                        + itemModifier.getQuantity()
                                                        + " </td><td width='100px;' style='text-align:center'> "
                                                        + "$"
                                                        + itemModifier.getModifiers().getPrice() + "</td></tr>";
                                        orderDetails = orderDetails + "" + modifiers;
                                        Map<String, String> exctra = new HashMap<String, String>();
                                        exctra.put("id", itemModifier.getModifiers().getPosModifierId());
                                        exctra.put("price", Double.toString(itemModifier.getModifiers().getPrice()));
                                        exctra.put("name", itemModifier.getModifiers().getName());
                                        exctra.put("qty", Integer.toString(orderItem.getQuantity()));
                                        extraList.add(exctra);
                                    }
                                    Gson gson = new Gson();
                                    String extraJson = gson.toJson(extraList);
                                    poduct.put("extras", extraJson);
                                    productList.add(poduct);
                                }
                            }
                        }
                        Gson gson = new Gson();
                        String productJson = gson.toJson(productList);
                        order.put("productItems", productJson);
                        // order.put("productItems", productList.toString());
                        String orderJson = gson.toJson(order);
                        notification.put("payload", orderPosId + "@#You got a new order(" + orderPosId + ") at "
                                        + orderR.getCreatedOn() + "@# $" + orderR.getOrderPrice() + "@# " + orderType
                                        + "@#" + orderJson + "@#" + orderR.getMerchant().getPosMerchantId() + "@#"
                                        + orderR.getOrderNote() + " @#" + paymentPOSID);

                        // +"@#"+tip add this to notification payload if we add tip amount to show on popup on
                        // notification app side
                        notf.put("notification", notification);
                        String notificationJson = gson.toJson(notf);
                        notificationJson = notificationJson.trim().replaceAll("\\\\+\"", "'").replace("'[", "[")
                                        .replace("]'", "]");
                        // notificationJson=notificationJson.trim().replace("'[", "[").replace("]'", "]");
                        // notificationJson=notificationJson.trim().replace("]'", "]");
                        System.out.println(notificationJson);
                        orderDetails = "<table width='300px;'><tbody>" + orderDetails + "</table></tbody>";
                        if (("on".equals(futureOrderType)||futureOrderType.equals("")||futureOrderType.isEmpty()) && ("select".equals(futureDate))) {
                            sendNotification(notificationJson, orderR.getMerchant().getPosMerchantId(), orderR
                                            .getMerchant().getAccessToken());
                        }
                        convenienceFeeValue1 = convenienceFeeValue;
                        name1 = name;
                        paymentYtpe1 = paymentYtpe;
                        orderPosId1 = orderPosId;
                        subTotal1 = subTotal;
                        tax1 = tax;
                        orderDetails1 = orderDetails;
                        email1 = email;
                        orderPrice1 = orderR.getOrderPrice();
                        note1 = orderR.getOrderNote();
                        merchantName1 = merchantName;
                        merchantLogo1 = merchantLogo;
                        orderType1 = orderType;
                        tipAmount = orderR.getTipAmount();
                        if (orderR.getDeliveryFee() != null && !orderR.getDeliveryFee().isEmpty())
                            deliverFee = orderR.getDeliveryFee();
                        if (orderR.getOrderDiscount() != null)
                            orderDiscount = orderR.getOrderDiscount();
                    }
                }
            }
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private void sendNotification(String notificationJson, String merchantId, String accessToken) {
        HttpPost postRequest = new HttpPost(IConstant.CLOVER_V2_URL + merchantId + "/apps/" + IConstant.CLOVER_APP_ID
                        + "/notify?access_token=" + accessToken);
        System.out.println("=====" + convertToStringJson(postRequest, notificationJson));
    }

    public static String convertToStringJson(HttpPost postRequest, String customerJson) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            StringEntity input = new StringEntity(customerJson);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);
            System.out.println("Output from Server .... \n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

    public String getFutureOrders(String merchantId){
    	
    	Merchant merchant=merchantRepository.findByPosMerchantId(merchantId);
    	if(merchant!=null){
    		String avgDeliveryTime=null;
            String avgPickUpTime=null;
            PickUpTime pickUpTime=pickUpTimeRepository.findByMerchantId(merchant.getId());
            if(pickUpTime!=null && pickUpTime.getPickUpTime()!=null){
            	avgPickUpTime=pickUpTime.getPickUpTime();
            }
            
                List<Zone> zones = zoneRepository.findByMerchantIdAndZoneStatus(merchant.getId(), 0);
                if (zones != null) {
                    if (!zones.isEmpty() && zones.size()>0) {
                       
                        avgDeliveryTime=zones.get(0).getAvgDeliveryTime();
                    } 
                }
                Calendar deliveryAvgTimeCalendar = Calendar.getInstance();
                Calendar pickUpAvgTimeCalendar = Calendar.getInstance();
                int time=45;
                try{
                if(avgDeliveryTime!=null){
                java.util.Date deliveryAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgDeliveryTime+":00");
                deliveryAvgTimeCalendar.setTime(deliveryAvgTime);
                }if(avgPickUpTime!=null){
                    java.util.Date pickUpAvgTime = new SimpleDateFormat("HH:mm:ss").parse("00:"+avgPickUpTime+":00");
                    pickUpAvgTimeCalendar.setTime(pickUpAvgTime);
                    }
                if(avgDeliveryTime!=null && avgPickUpTime!=null){
                	if(pickUpAvgTimeCalendar.getTimeInMillis()<deliveryAvgTimeCalendar.getTimeInMillis()){
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
                int timeInterval=time+15;
    	final long ONE_MINUTE_IN_MILLIS = 60000;
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingTenMins = new Date(t + (time * ONE_MINUTE_IN_MILLIS));
        afterAddingTenMins.setSeconds(0);
        Date betWeenWithTime = new Date(t + (timeInterval * ONE_MINUTE_IN_MILLIS));
        betWeenWithTime.setSeconds(0);
   System.out.println("-------" + afterAddingTenMins+"------"+betWeenWithTime);
        List<OrderR> orderRs = orderRepo.findByFulFilledDateAndIsFutureOrderAndMerchantIdAndIsDefaults(afterAddingTenMins,
        		IConstant.BOOLEAN_TRUE, merchant.getId(), betWeenWithTime, IConstant.Order_Status_Pending );
        List<String> notifications=new ArrayList<String>();
        for (OrderR orderR : orderRs) {
            //String orderDetails = "";
        	Map<String, Map<String, String>> notf = new HashMap<String, Map<String, String>>();
            Map<String, String> notification = new HashMap<String, String>();
            notification.put("appEvent", "notification");
            Map<String, String> order = new HashMap<String, String>();
            order.put("total", Double.toString(orderR.getOrderPrice()));
            order.put("tax", orderR.getTax());
            List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        
                        List<Map<String, String>> extraList = new ArrayList<Map<String, String>>();
                        Map<String, String> poduct = new HashMap<String, String>();
                        poduct.put("product_id", orderItem.getItem().getPosItemId());
                        poduct.put("name", orderItem.getItem().getName());
                        poduct.put("price", Double.toString(orderItem.getItem().getPrice()));
                        poduct.put("qty", Integer.toString(orderItem.getQuantity()));
                        List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem.getId());
                        for (OrderItemModifier itemModifier : itemModifiers) {
                            
                            Map<String, String> exctra = new HashMap<String, String>();
                            exctra.put("id", itemModifier.getModifiers().getPosModifierId());
                            exctra.put("price", Double.toString(itemModifier.getModifiers().getPrice()));
                            exctra.put("name", itemModifier.getModifiers().getName());
                            exctra.put("qty", Integer.toString(orderItem.getQuantity()));
                            extraList.add(exctra);
                        }
                        Gson gson = new Gson();
                        String extraJson = gson.toJson(extraList);
                        poduct.put("extras", extraJson);
                        productList.add(poduct);
                    }
                }
            }
            Gson gson = new Gson();
            String productJson = gson.toJson(productList);
            order.put("productItems", productJson);
            // order.put("productItems", productList.toString());
            
            Date fulFilledTime = orderR.getFulfilled_on();
            Calendar calobjs = Calendar.getInstance();
              calobjs.setTime(fulFilledTime);
            calobjs.add(Calendar.MINUTE, -Integer.parseInt(orderR.getOrderAvgTime()));
            Date orderFulFilledTime = calobjs.getTime();
            System.out.println("orderFulFilledTime-"+orderFulFilledTime);
            long timeInMillis = orderFulFilledTime.getTime();
           // DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
            //String orderFulTime = dateFormat.format(orderFulFilledTime).toString();
            String orderJson = gson.toJson(order);
            notification.put(
                            "payload",
                            orderR.getOrderPosId() + "@#You got a new order(" + orderR.getOrderPosId() + ") at "
                                            + orderR.getCreatedOn() + "@# $" + orderR.getOrderPrice() + "@# "
                                            + orderR.getOrderType() + "@#" + orderJson + "@#"
                                            + orderR.getMerchant().getPosMerchantId() + "@#"
                                            + orderR.getOrderNote() + " @#" + orderR.getPosPaymentId()+ " @#" + timeInMillis);

            notf.put("notification", notification);
            String notificationJson = gson.toJson(notf);
            notificationJson = notificationJson.trim().replaceAll("\\\\+\"", "'").replace("'[", "[")
                            .replace("]'", "]");
            notifications.add(notificationJson);
        }
        if(notifications.size()>0){
        	return 	notifications.toString();
        }else{
        	return null;
        }
        
         
    	}else{
    		return null;
    	}
    }
    
    public boolean setOrderStatus(String orderId, String type, String reason) {
        if (null == orderId) {
            return false;
        }
        OrderR orderR = orderRepo.findByOrderPosId(orderId);
        if (orderR == null) {
            return false;
        }

        Merchant merchant = orderR.getMerchant();
        String merchantLogo = "";
        ConvenienceFee convenienceFee = null;
        PickUpTime pickUpTime = null;
        String convenienceFeeValue = "0";
        String orderAvgTime = "0";
        if (merchant != null) {
            convenienceFee = convenienceFeeRepository.findByMerchantId(merchant.getId());
            pickUpTime = pickUpTimeRepository.findByMerchantId(merchant.getId());
            System.out.println(pickUpTime);
            if (orderR != null) {
                orderAvgTime = orderR.getOrderAvgTime();
            }
            if (convenienceFee != null) {
                convenienceFeeValue = convenienceFee.getConvenienceFee();
            }
            if (merchant.getMerchantLogo() == null) {
                merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
            } else {
                merchantLogo = UrlConstant.BASE_PORT + merchant.getMerchantLogo();
            }
        }

        if (type.equals("Accept")) {
            orderR.setIsDefaults(1);
            orderRepo.save(orderR);
            String orderDetails = "";
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItem() != null) {
                    String items = "<tr style='font-weight:600;text-transform:capitalize;font-family:arial'><td width='200px;'>"
                                    + orderItem.getItem().getName()
                                    + "</td><td width='100px;' style='text-align:center'>"
                                    + orderItem.getQuantity()
                                    + "</td><td width='100px;' style='text-align:center'>"
                                    + "$"
                                    + orderItem.getItem().getPrice() + "</td></tr>";
                    orderDetails = orderDetails + "" + "<b>" + items + "</b>";
                }
                List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem.getId());
                for (OrderItemModifier itemModifier : itemModifiers) {
                    String modifiers = "<tr style='text-transform:capitalize;font-family:arial;margin-top:5px;font-size:12px'><td width='200px;'>"
                                    + itemModifier.getModifiers().getName()
                                    + "</td><td width='100px;' style='text-align:center'>"
                                    + itemModifier.getQuantity()
                                    + " </td><td width='100px;' style='text-align:center'> "
                                    + "$"
                                    + itemModifier.getModifiers().getPrice() + "</td></tr>";
                    orderDetails = orderDetails + "" + modifiers;
                }
            }
            orderDetails = "<table width='300px;'><tbody>" + orderDetails + "</table></tbody>";
            System.out.println(orderDetails);
            String deliveryFee = "0";
            if (orderR.getDeliveryFee() != null && !orderR.getDeliveryFee().isEmpty())
                deliveryFee = orderR.getDeliveryFee();

            Double orderDiscount = 0.0;

            if (orderR.getOrderDiscount() != null)
                orderDiscount = orderDiscount;

            MailSendUtil.sendConfirmMail(orderR.getCustomer().getFirstName(), orderR.getPaymentMethod(), orderId,
                            orderR.getSubTotal(), orderR.getTax(), orderDetails, orderR.getCustomer().getEmailId(),
                            orderR.getOrderPrice(), orderR.getOrderNote(), orderR.getMerchant().getName(),
                            merchantLogo, orderR.getOrderType(), convenienceFeeValue, orderAvgTime, deliveryFee,
                            orderDiscount, orderR.getTipAmount());
        } else {
            orderR.setIsDefaults(2);
            orderRepo.save(orderR);
            MailSendUtil.sendOrderCancellationMail(orderR.getCustomer().getFirstName(), orderId, orderR.getCustomer()
                            .getEmailId(), orderR.getMerchant().getName(), merchantLogo, orderR.getMerchant()
                            .getPhoneNumber(), orderR.getMerchant().getOwner().getEmail(), reason);
        }
        return true;
    }

    /**
     * find all orders by merchantId
     */
    public List<OrderR> findAllOrdersByMerchantId(int merchantId) {
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -12);
        Date today90 = cal.getTime();
        String startDate = dateFormat.format(new Date());
        String endDate = dateFormat.format(today90);
        System.out.println(startDate + endDate);
        Collections.sort(orderRs, new Comparator<OrderR>() {
            public int compare(OrderR m1, OrderR m2) {
                return m2.getCreatedOn().compareTo(m1.getCreatedOn());
            }
        });
        return findOrderDetails(orderRs);

    }

    private List<OrderR> findOrderDetails(List<OrderR> orderRs) {
        for (OrderR orderR : orderRs) {
            List<OrderItemViewVO> orderItemViewVOs = new ArrayList<OrderItemViewVO>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        if (!orderItem.getItem().getName().equals("Convenience Fee")
                                        && !orderItem.getItem().getName().equals("Delivery Fee")) {
                            List<OrderItemModifierViewVO> itemModifierViewVOs = new ArrayList<OrderItemModifierViewVO>();
                            OrderItemViewVO orderItemViewVO = new OrderItemViewVO();
                            orderItemViewVO.setQuantity(orderItem.getQuantity());
                            orderItem.getItem().setCategories(null);
                            orderItem.getItem().setItemModifierGroups(null);
                            orderItem.getItem().setTaxes(null);
                            orderItem.getItem().setOrderItems(null);
                            orderItemViewVO.setItem(orderItem.getItem());
                            List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem
                                            .getId());
                            for (OrderItemModifier itemModifier : itemModifiers) {
                                OrderItemModifierViewVO itemModifierViewVO = new OrderItemModifierViewVO();
                                itemModifierViewVO.setQuantity(itemModifier.getQuantity());

                                if (itemModifier.getModifiers() != null)
                                    itemModifier.getModifiers().setModifierGroup(null);

                                itemModifierViewVO.setModifiers(itemModifier.getModifiers());
                                itemModifierViewVOs.add(itemModifierViewVO);
                            }
                            orderItemViewVO.setItemModifierViewVOs(itemModifierViewVOs);
                            orderItemViewVOs.add(orderItemViewVO);
                        }
                    }
                }
            }
            orderR.setOrderItemViewVOs(orderItemViewVOs);
        }
        return orderRs;
    }

    /**
     * Find by orderType
     */
    public List<OrderR> findOrdersByOrderType(String orderType) {
        return orderRepo.findByOrderType(orderType);
    }

    /**
     * Find by orderStatus
     */
    public List<OrderR> findOrdersByStatus(String orderStatus) {
        int status = 0;
        if (orderStatus.equals("Pending")) {
            status = 0;
        } else {
            status = 1;
        }
        return orderRepo.findByIsDefaults(status);
    }

    /**
     * Find by orderStatus and OrderType
     */
    public List<OrderR> findOrdersByStatusAndOrderType(String orderStatus, String orderType) {
        int status = 0;
        if (orderStatus.equals("Pending")) {
            status = 0;
        } else {
            status = 1;
        }
        return orderRepo.findByIsDefaultsAndOrderType(status, orderType);
    }

    public List<OrderR> findOrdersByStatusAndOrderTypeAndDateRange(String orderStatus, String orderType,
                    String startDate, String endDate) {
        List<OrderR> orderRs = new ArrayList<OrderR>();
        int status = 0;
        if (orderStatus.equals("Pending")) {
            status = 0;
        } else {
            status = 1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<OrderR> orders = orderRepo.findByIsDefaultsAndOrderType(status, orderType);
        try {
            for (OrderR orderR : orders) {
                Date sDate = sdf.parse(startDate);
                Date eDate = sdf.parse(endDate);
                if ((orderR.getCreatedOn().compareTo(sDate) > 0) && (eDate.compareTo(orderR.getCreatedOn()) > 0)) {
                    orderRs.add(orderR);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderRs;
    }

    /**
     * Find orders between two date
     */
    public List<OrderR> findByOrderDate(String startDate, String endDate) {
        List<OrderR> orderRs = new ArrayList<OrderR>();
        List<OrderR> orders = orderRepo.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (OrderR orderR : orders) {
                Date sDate = sdf.parse(startDate);
                Date eDate = sdf.parse(endDate);
                if ((orderR.getCreatedOn().compareTo(sDate) > 0) && (eDate.compareTo(orderR.getCreatedOn()) > 0)) {
                    orderRs.add(orderR);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderRs;
    }

    /**
     * Find by orderType and orderDate
     */
    public List<OrderR> findByOrderTypeAndOrderDate(String orderType, String startDate, String endDate) {
        List<OrderR> list = orderRepo.findByOrderType(orderType);
        List<OrderR> orderRs = new ArrayList<OrderR>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (OrderR orderR : list) {
                Date sDate = sdf.parse(startDate);
                Date eDate = sdf.parse(endDate);
                if ((orderR.getCreatedOn().compareTo(sDate) > 0) && (eDate.compareTo(orderR.getCreatedOn()) > 0)) {
                    orderRs.add(orderR);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderRs;
    }

    /**
     * Find by orderStatus and oderDate
     */
    public List<OrderR> findByOrderStatusAndOrderDate(String orderStatus, String startDate, String endDate) {
        int status = 0;
        if (orderStatus.equals("Pending")) {
            status = 0;
        } else {
            status = 1;
        }
        List<OrderR> orderRs = new ArrayList<OrderR>();
        List<OrderR> list = orderRepo.findByIsDefaults(status);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (OrderR orderR : list) {
                Date sDate = sdf.parse(startDate);
                Date eDate = sdf.parse(endDate);
                if ((orderR.getCreatedOn().compareTo(sDate) > 0) && (eDate.compareTo(orderR.getCreatedOn()) > 0)) {
                    orderRs.add(orderR);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderRs;
    }

    /**
     * Find by customerId
     */
    public List<OrderR> findOrderByCustomerId(int customerId) {
        List<OrderR> orderRs = orderRepo.findByCustomerId(customerId);
        return findOrderDetails(orderRs);
    }

    public Integer findNoOfDeliveryOrder(Integer merchantId) {
        return orderRepo.countUsersByMerchantIdAndOrderType(merchantId, "Delivery");
    }

    public Integer findNoOfPickUpOrder(Integer merchantId) {
    	Integer noOfPickUpOrder=orderRepo.countUsersByMerchantIdAndOrderType(merchantId, "Pickup");
    	if(noOfPickUpOrder==null){
    		noOfPickUpOrder=0;
    	}
        return noOfPickUpOrder;
    }

    public double findAverageOrderValue(Integer merchantId) {
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        double roundOff = 0;
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                double totalValue = orderRepo.findSum(merchantId);
                int orderCount = orderRepo.countUsersByMerchantId(merchantId);
                double oValue = totalValue / orderCount;
                roundOff = Math.round(oValue * 100.0) / 100.0;
            }
        }
        return roundOff;
    }

    public int findtotalOrderValue(Integer merchantId) {
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                /*
                 * double totalValue = orderRepo.findSum(merchantId); roundOff = Math.round(totalValue * 100.0) / 100.0;
                 */
                return orderRs.size();
            }
        }
        return IConstant.IS_NOT_APPROVED;
    }

    public double findOrderFrequency(Integer merchantId, Integer vendorId) {
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        double roundOff = 0;
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                int orderCount = orderRepo.countUsersByMerchantId(merchantId);
                int totalCustomer = customerrRepository.countByVendorId(merchantId);
                double oValue = 0;
                if (totalCustomer > 0)
                    oValue = orderCount / totalCustomer;

                roundOff = Math.round(oValue * 100.0) / 100.0;
            }
        }
        return roundOff;
    }

    public double findCustomerOrderAverage(Integer merchantId, Integer vendorId) {
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        double roundOff = 0;
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                double totalValue = orderRepo.findSum(merchantId);
                int totalCustomer = customerrRepository.countByVendorId(merchantId);
                double oValue = totalValue / totalCustomer;
                roundOff = Math.round(oValue * 100.0) / 100.0;
            }
        }
        return Math.round(roundOff);
    }

    public Integer findTotalCustomer(Integer merchantId, Integer vendorId) {
        return customerrRepository.countByVendorId(merchantId);
    }

    /**
     * Find trending item name
     */
    public String findTrendingItem(Integer merchantId) {
        String itemName = null;
        List<Object> orderRs = orderRepo.findByMerId(merchantId);
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                Object[] obj = (Object[]) orderRs.get(0);
                Integer itemId = (Integer) obj[0];
                itemName = itemmRepository.findItemNameByItemId(itemId);
            }
        }
        return itemName;
    }

    public double findAverageNumberOfItemPerOrder(Integer merchantId, Integer vendorId) {
        int numberOfItem = 0;
        double averageNumberOfItemPerOrder = 0;
        List<OrderR> orderRs = orderRepo.findByMerchantId(merchantId);
        if (orderRs != null) {
            if (!orderRs.isEmpty()) {
                for (OrderR orderR : orderRs) {
                    List<Integer> orderItems = orderItemRepository.findDistinctItemIdByOrderId(orderR.getId());
                    if (orderItems != null) {
                        if (!orderItems.isEmpty()) {
                            numberOfItem = numberOfItem + orderItems.size();
                        }
                    }
                }
                System.out.println(numberOfItem);
                double result = numberOfItem / (orderRs.size());
                averageNumberOfItemPerOrder = Math.round(result);
            }
        }
        return averageNumberOfItemPerOrder;
    }

    public List<String> findPaymentMode(Integer merchantId) {
        List<PaymentMode> modes = paymentModeRepository.findByMerchantIdAndAllowPaymentMode(merchantId, 1);
        List<String> paymentModes = new ArrayList<String>();
        if (modes != null) {
            if (!modes.isEmpty()) {
                for (PaymentMode mode : modes) {
                    if ( (mode.getLabelKey()!=null && mode.getLabelKey().contains("cash")) || (mode.getLabel()!=null &&mode.getLabel().equals("Cash"))) {
                        paymentModes.add(mode.getLabel());
                    }
                    if ( (mode.getLabelKey()!=null && mode.getLabelKey().contains("credit_card")) || (mode.getLabel()!=null &&mode.getLabel().equals("Credit Card"))) {
                        paymentModes.add(mode.getLabel());
                    }
                }
            }
        }
        return paymentModes;
    }

    public OrderType findByMerchantIdAndLabel(Integer merchantId, String orderType) {

        OrderType orderTypes = null;
        if (orderType.toLowerCase().equals("pickup")) {
            orderTypes = orderTypeRepository.findByMerchantIdAndLabel(merchantId, "Foodkonnekt Online Pickup");
        } else if (orderType.toLowerCase().equals("delivery")) {
            orderTypes = orderTypeRepository.findByMerchantIdAndLabel(merchantId, "Foodkonnekt Online Delivery");
        }

        return orderTypes;
    }

    public void deleteAnOrder(Integer customerId, String orderPOSId) {
        OrderR orderR = orderRepository.findByCustomerIdAndOrderPosId(customerId, orderPOSId);
        if (orderR != null) {
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            if (null != orderItems && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    List<OrderItemModifier> orderItemModifiers = orderItemModifierRepository
                                    .findByOrderItemId(orderItem.getId());
                    orderItemModifierRepository.delete(orderItemModifiers);
                    orderItem.setOrder(null);
                    orderItem.setItem(null);
                }
            }
            orderR.setMerchant(null);
            orderR.setCustomer(null);
            orderItemRepository.delete(orderItems);
            orderRepository.delete(orderR);
        }
    }

    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        try {
            MailSendUtil.placeOrderMail(name1, paymentYtpe1, orderPosId1, subTotal1, tax1, orderDetails1, email1,
                            orderPrice1, note1, merchantName1, merchantLogo1, orderType1, convenienceFeeValue1,
                            avgTime, deliverFee, orderDiscount, tipAmount);
            System.out.println(" Order mail thread over");
        } catch (Exception e) {
            System.out.println(" Invalid Emailid");
        }
    }

    /**
     * Find calculated convenience fee with tax
     */
    public Double findConvenienceFeeAfterTax(String convenienceFee, Integer merchantId) {
        Double totalConvenienceFeeTax = 0.0;
        try {
            List<TaxRates> taxRates = taxRateRepository
                            .findByMerchantIdAndIsDefault(merchantId, IConstant.BOOLEAN_TRUE);
            for (TaxRates rates : taxRates) {
                Double conveniceFeeTax = (Double.valueOf(convenienceFee) * rates.getRate()) / 100;
                totalConvenienceFeeTax = totalConvenienceFeeTax + Math.round(conveniceFeeTax * 100.0) / 100.0;
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return totalConvenienceFeeTax;
    }

    public String findConvenienceFeeAfterMultiTax(String convenienceFee, Integer merchantId) {
        String totalConvenienceFeeTax = "";
        try {
            List<TaxRates> taxRates = taxRateRepository
                            .findByMerchantIdAndIsDefault(merchantId, IConstant.BOOLEAN_TRUE);
            for (TaxRates rates : taxRates) {
                totalConvenienceFeeTax = totalConvenienceFeeTax + "," + rates.getName();
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return totalConvenienceFeeTax;
    }

    public List<ItemDto> findCategoryItems(Integer categoryId,Merchant merchant) {
    	
    	String timeZoneCode="America/Chicago";
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getTimeZoneCode()!=null){
    		/*hourDifference=merchant.getTimeZone().getHourDifference();
    		if(merchant.getTimeZone().getMinutDifference()!=null)
    		minutDifference=merchant.getTimeZone().getMinutDifference();*/
    		timeZoneCode=merchant.getTimeZone().getTimeZoneCode();
    	}
    	String currentDay=DateUtil.getCurrentDayForTimeZone(timeZoneCode);
    	String currentTime=DateUtil.getCurrentTimeForTimeZone(timeZoneCode);
    	
    	List<CategoryItem> categoryItems =categoryItemRepository.findByCategoryIdOrderBySortOrderAsc(categoryId);
    	
        //List<Item> items = itemmRepository.findByCategoriesId(categoryId);
        List<ItemDto> itemDtos = new ArrayList<ItemDto>();
        try {
            if (categoryItems.size() != 0) {
                for (CategoryItem categoryItem : categoryItems) {
                	
                	Item item=categoryItem.getItem();
                	if(item!=null){
                	ItemTiming itemTiming=itemTimingRepository.findByItemIdAndDay(item.getId(), currentDay);
                	boolean itemTimingStatus=false;
                	if(itemTiming!=null ){
                		if(itemTiming.isHoliday()){
                			itemTimingStatus=false;
                		}else{
                			String startTime=DateUtil.convert12hoursTo24HourseFormate(itemTiming.getStartTime());
                			String endTime=DateUtil.convert12hoursTo24HourseFormate(itemTiming.getEndTime());
                	 itemTimingStatus=OrderUtil.isTimeBetweenTwoTime(startTime + ":00", endTime + ":00",currentTime);
                		}
                	}else{
                		itemTimingStatus=true;
                	}
                	if(itemTimingStatus){
                	String taxName=getItemTaxName(item.getId());
                    if (item.getItemStatus() != null && item.getItemStatus() == 0 && taxName!=null && !taxName.isEmpty()) {
                        ItemDto itemDto = new ItemDto();
                        itemDto.setId(item.getId());
                        itemDto.setItemName(item.getName());
                        itemDto.setItemTax(getItemTaxPrice(item.getId()));
                        itemDto.setPrice(item.getPrice());
                        itemDto.setItemPosId(item.getPosItemId());
                        if(merchant!=null &&merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.NON_POS){
                        	 itemDto.setItemPosId(Integer.toString(item.getId()));
                        }
                        itemDto.setAllowModifierLimit(item.getAllowModifierLimit());
                        itemDto.setItemTaxName(taxName);
                        if (item.getDescription() != null) {
                            itemDto.setDescription(item.getDescription());
                        } else {
                            itemDto.setDescription("");
                        }
                        itemDtos.add(itemDto);
                    }
                	}}
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return itemDtos;
    }

    private String getItemTaxName(Integer itemId) {
        String taxNames = null;
        try {
            if (itemId != null) {
                 List<ItemTax> itemTaxs = itemTaxRepository.findByItemId(itemId);
                if (itemTaxs!=null && !itemTaxs.isEmpty()) {
                	taxNames="";
                    for (ItemTax itemTax : itemTaxs) {
                        taxNames = taxNames + "," + itemTax.getTaxRates().getName();
                    }
                    taxNames= taxNames.trim();
                }
            }
            return taxNames;
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            return taxNames;
        }
    }

    public List<ModifierGroupDto> findItemModifierGroups(Integer itemId, Integer allowModifierLimit) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Address> findAddessByCustomerId(Integer customerId) {
        Customer customer = customerrRepository.findOne(customerId);
        List<Address> finalAddresses = new ArrayList<Address>();
        try {
            List<Address> addresses = addressRepository.findByCustomerId(customerId);
            for (Address address : addresses) {
                Address address2 = new Address();
                address2.setId(address.getId());
                address2.setAddress1(address.getAddress1());
                address2.setAddress2(address.getAddress2());
                address2.setAddress3(address.getAddress3());
                address2.setCity(address.getCity());
                address2.setState(address.getState());
                address2.setZip(address.getZip());

                List<Zone> zones = zoneRepository.findByMerchantId(customer.getMerchantt().getId());
                double distance;
                final double MILES_PER_KILOMETER = 0.621;
                for (Zone zone : zones) {
                    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
                    distance = customerServiceImpl.checkDelivery(address, zone.getAddress());
                    double miles = distance * MILES_PER_KILOMETER;
                    if (miles <= zone.getZoneDistance()) {
                        address2.setDeliveryFee(zone.getDeliveryFee());
                        address2.setDeliveryPosId(zone.getDeliveryLineItemPosId());
                    }
                }
                finalAddresses.add(address2);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return finalAddresses;
    }

    public String findMerchantTaxs(Integer merchantId) {
        String merchantTaxs = "";
        try {
            List<TaxRates> taxRates = taxRateRepository.findByMerchantId(merchantId);
            for (TaxRates rates : taxRates) {
                rates.setMerchant(null);
                rates.setItems(null);
            }
            Gson gson = new Gson();
            merchantTaxs = gson.toJson(taxRates);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return merchantTaxs;
    }

    public List<OrderR> findAllOrdersByFulfilledDate(java.sql.Date currentDate, int merchantId) {
        List<OrderR> orderRs = orderRepo.findByFulFilledDateAndMerchantId(currentDate, merchantId);
        return orderRs;
    }

    public OrderR findOrderByOrderID(String id) {
        return orderRepository.findByOrderPosId(String.valueOf(id));
    }

    public OrderR findOrderByID(Integer id) {
        return orderRepository.findOne(id);
    }

    public void saveOrder(OrderR order) {
        orderRepo.save(order);
    }

    public void sendMailUser(OrderR orderR, String reason, String time) {
        MailSendUtil.sendUpdatedTime(orderR, reason, time);
    }

    public String findAllOrderFromDataTable(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.DESC, "id");
        Page<OrderR> orderRs = orderRepo.findByMerchantId(merchantId, pageable);
        List<AllOrderVo> allOrderVos = new ArrayList<AllOrderVo>();
        for (OrderR orderR : orderRs.getContent()) {
            AllOrderVo allOrderVo = new AllOrderVo();
            allOrderVo.setDT_RowId("row_" + orderR.getId());
            allOrderVo.setId(orderR.getId());
            allOrderVo.setFirstName(orderR.getCustomer().getFirstName());
            allOrderVo.setCreatedOn(orderR.getCreatedOn());
            Double roundOff = Math.round(orderR.getOrderPrice()*100)/100.0;
            allOrderVo.setOrderPrice(roundOff);
            allOrderVo.setConvenienceFee(orderR.getConvenienceFee());
            allOrderVo.setDeliveryFee(orderR.getDeliveryFee());
            allOrderVo.setTax(orderR.getTax());
            if(orderR.getOrderDiscount()!=null && orderR.getOrderDiscount()>0)
            allOrderVo.setDiscount(orderR.getOrderDiscount());
            allOrderVo.setSubTotal(orderR.getSubTotal());
            allOrderVo.setTipAmount(orderR.getTipAmount());
            if (orderR.getOrderNote()==null || orderR.getOrderNote().isEmpty()) {
                allOrderVo.setOrderName("");
            } else {
                allOrderVo.setOrderName(orderR.getOrderNote());
            }
            allOrderVo.setOrderType(orderR.getOrderType());
            if (orderR.getIsDefaults() == 1) {
                allOrderVo.setStatus("Confirmed");
            } else {
                if (orderR.getIsDefaults() == 2) {
                    allOrderVo.setStatus("Cancelled");
                } else {
                    allOrderVo.setStatus("Pending");
                }
            }

            // order items
            List<OrderItemViewVO> orderItemViewVOs = new ArrayList<OrderItemViewVO>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        if (!orderItem.getItem().getName().equals("Convenience Fee")
                                        && !orderItem.getItem().getName().equals("Delivery Fee")) {
                            List<OrderItemModifierViewVO> itemModifierViewVOs = new ArrayList<OrderItemModifierViewVO>();
                            OrderItemViewVO orderItemViewVO = new OrderItemViewVO();
                            orderItemViewVO.setQuantity(orderItem.getQuantity());
                            ItemVO itemVO = new ItemVO();
                            itemVO.setPrice(orderItem.getItem().getPrice());
                            itemVO.setName(orderItem.getItem().getName());
                            orderItemViewVO.setItemVO(itemVO);
                            orderItem.setItem(null);

                            List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem
                                            .getId());
                            for (OrderItemModifier itemModifier : itemModifiers) {
                                if (itemModifier.getModifiers() != null) {
                                    OrderItemModifierViewVO itemModifierViewVO = new OrderItemModifierViewVO();
                                    itemModifierViewVO.setQuantity(itemModifier.getQuantity());

                                    itemModifier.getModifiers().setModifierGroup(null);
                                    itemModifier.getModifiers().setMerchant(null);

                                    itemModifierViewVO.setModifiers(itemModifier.getModifiers());
                                    itemModifierViewVOs.add(itemModifierViewVO);
                                }
                            }
                            orderItemViewVO.setItemModifierViewVOs(itemModifierViewVOs);
                            orderItemViewVOs.add(orderItemViewVO);
                        }
                    }
                }
            }
            allOrderVo.setOrderItemViewVOs(orderItemViewVOs);
            // order items end

            allOrderVo.setView("<p><button class='md-trigger custom-sd-button-dialog' id='viewOrderDetailPopup' data-modal= modal-15-"
                            + orderR.getId() + " orderAttr=" + orderR.getId() + ">View</button></p>");

            allOrderVos.add(allOrderVo);
        }
        OrderJson orderJosn = new OrderJson();
        orderJosn.setiTotalDisplayRecords((int)orderRs.getTotalElements());
        orderJosn.setiTotalRecords((int)orderRs.getTotalElements());
        orderJosn.setAaData(allOrderVos);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(orderJosn);
    }

    public String findOrderDetailsById(Integer orderId) {
        OrderR orderRs = orderRepo.findOne(orderId);
        return findOrderDetail(orderRs);
    }

    private String findOrderDetail(OrderR orderRs) {
        OrderR orderR = new OrderR();
        orderR.setId(orderRs.getId());
        orderR.setConvenienceFee(orderRs.getConvenienceFee());
        orderR.setCreatedOn(orderRs.getCreatedOn());
        orderR.setDeliveryFee(orderRs.getDeliveryFee());
        orderR.setIsDefaults(orderRs.getIsDefaults());
        orderR.setOrderName(orderRs.getOrderName());
        orderR.setOrderNote(orderRs.getOrderNote());
        orderR.setOrderPrice(orderRs.getOrderPrice());
        orderR.setSubTotal(orderRs.getSubTotal());
        orderR.setOrderPrice(orderRs.getOrderPrice());
        orderR.setTax(orderRs.getTax());
        List<OrderItemViewVO> orderItemViewVOs = new ArrayList<OrderItemViewVO>();
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem != null) {
                if (orderItem.getItem() != null) {
                    if (!orderItem.getItem().getName().equals("Convenience Fee")
                                    && !orderItem.getItem().getName().equals("Delivery Fee")) {
                        List<OrderItemModifierViewVO> itemModifierViewVOs = new ArrayList<OrderItemModifierViewVO>();
                        OrderItemViewVO orderItemViewVO = new OrderItemViewVO();
                        orderItemViewVO.setQuantity(orderItem.getQuantity());
                        orderItem.getItem().setCategories(null);
                        orderItem.getItem().setItemModifierGroups(null);
                        orderItem.getItem().setTaxes(null);
                        orderItem.getItem().setOrderItems(null);
                        orderItemViewVO.setItem(orderItem.getItem());
                        List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem
                                        .getId());
                        for (OrderItemModifier itemModifier : itemModifiers) {
                            OrderItemModifierViewVO itemModifierViewVO = new OrderItemModifierViewVO();
                            itemModifierViewVO.setQuantity(itemModifier.getQuantity());

                            if (itemModifier.getModifiers() != null)
                                itemModifier.getModifiers().setModifierGroup(null);

                            itemModifierViewVO.setModifiers(itemModifier.getModifiers());
                            itemModifierViewVOs.add(itemModifierViewVO);
                        }
                        orderItemViewVO.setItemModifierViewVOs(itemModifierViewVOs);
                        orderItemViewVOs.add(orderItemViewVO);
                    }
                }
            }
        }
        orderR.setOrderItemViewVOs(orderItemViewVOs);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(orderR);
    }
    
 public String searchOrderByText(Integer merchantId, String searchTxt,Integer pageDisplayLength, Integer pageNumber) {
    	
	 Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.DESC, "id");
	 Page<OrderR> orderRs = orderRepo.findByMerchantIdAndCustomerName(merchantId, searchTxt,pageable);
        List<AllOrderVo> allOrderVos = new ArrayList<AllOrderVo>();
        for (OrderR orderR : orderRs) {
            AllOrderVo allOrderVo = new AllOrderVo();
            allOrderVo.setDT_RowId("row_" + orderR.getId());
            allOrderVo.setId(orderR.getId());
            allOrderVo.setFirstName(orderR.getCustomer().getFirstName());
            allOrderVo.setCreatedOn(orderR.getCreatedOn());
            allOrderVo.setOrderPrice(orderR.getOrderPrice());
            allOrderVo.setConvenienceFee(orderR.getConvenienceFee());
            allOrderVo.setDeliveryFee(orderR.getDeliveryFee());
            allOrderVo.setTax(orderR.getTax());
            allOrderVo.setSubTotal(orderR.getSubTotal());
            allOrderVo.setTipAmount(orderR.getTipAmount());
            if (orderR.getOrderNote().isEmpty()) {
                allOrderVo.setOrderName("");
            } else {
                allOrderVo.setOrderName(orderR.getOrderNote());
            }
            allOrderVo.setOrderType(orderR.getOrderType());
            if (orderR.getIsDefaults() == 1) {
                allOrderVo.setStatus("Confirmed");
            } else {
                if (orderR.getIsDefaults() == 2) {
                    allOrderVo.setStatus("Cancelled");
                } else {
                    allOrderVo.setStatus("Pending");
                }
            }

            // order items
            List<OrderItemViewVO> orderItemViewVOs = new ArrayList<OrderItemViewVO>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        if (!orderItem.getItem().getName().equals("Convenience Fee")
                                        && !orderItem.getItem().getName().equals("Delivery Fee")) {
                            List<OrderItemModifierViewVO> itemModifierViewVOs = new ArrayList<OrderItemModifierViewVO>();
                            OrderItemViewVO orderItemViewVO = new OrderItemViewVO();
                            orderItemViewVO.setQuantity(orderItem.getQuantity());
                            ItemVO itemVO = new ItemVO();
                            itemVO.setPrice(orderItem.getItem().getPrice());
                            itemVO.setName(orderItem.getItem().getName());
                            orderItemViewVO.setItemVO(itemVO);
                            orderItem.setItem(null);

                            List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem
                                            .getId());
                            for (OrderItemModifier itemModifier : itemModifiers) {
                                if (itemModifier.getModifiers() != null) {
                                    OrderItemModifierViewVO itemModifierViewVO = new OrderItemModifierViewVO();
                                    itemModifierViewVO.setQuantity(itemModifier.getQuantity());

                                    itemModifier.getModifiers().setModifierGroup(null);
                                    itemModifier.getModifiers().setMerchant(null);

                                    itemModifierViewVO.setModifiers(itemModifier.getModifiers());
                                    itemModifierViewVOs.add(itemModifierViewVO);
                                }
                            }
                            orderItemViewVO.setItemModifierViewVOs(itemModifierViewVOs);
                            orderItemViewVOs.add(orderItemViewVO);
                        }
                    }
                }
            }
            allOrderVo.setOrderItemViewVOs(orderItemViewVOs);
            // order items end

            allOrderVo.setView("<p><button class='md-trigger custom-sd-button-dialog' id='viewOrderDetailPopup' data-modal= modal-15-"
                            + orderR.getId() + " orderAttr=" + orderR.getId() + ">View</button></p>");

            allOrderVos.add(allOrderVo);
        }
        OrderJson orderJosn = new OrderJson();
        orderJosn.setiTotalDisplayRecords((int)orderRs.getTotalElements());
        orderJosn.setiTotalRecords((int)orderRs.getTotalElements());
        orderJosn.setAaData(allOrderVos);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(orderJosn);
        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(allOrderVos);*/
    }

   /* public String searchOrderByText(Integer merchantId, String searchTxt) {
    	
    	
        List<OrderR> orderRs = orderRepo.findByMerchantIdAndCustomerName(merchantId, searchTxt);
        List<AllOrderVo> allOrderVos = new ArrayList<AllOrderVo>();
        for (OrderR orderR : orderRs) {
            AllOrderVo allOrderVo = new AllOrderVo();
            allOrderVo.setDT_RowId("row_" + orderR.getId());
            allOrderVo.setId(orderR.getId());
            allOrderVo.setFirstName(orderR.getCustomer().getFirstName());
            allOrderVo.setCreatedOn(orderR.getCreatedOn());
            allOrderVo.setOrderPrice(orderR.getOrderPrice());
            allOrderVo.setConvenienceFee(orderR.getConvenienceFee());
            allOrderVo.setDeliveryFee(orderR.getDeliveryFee());
            allOrderVo.setTax(orderR.getTax());
            allOrderVo.setSubTotal(orderR.getSubTotal());
            allOrderVo.setTipAmount(orderR.getTipAmount());
            if (orderR.getOrderNote().isEmpty()) {
                allOrderVo.setOrderName("");
            } else {
                allOrderVo.setOrderName(orderR.getOrderNote());
            }
            allOrderVo.setOrderType(orderR.getOrderType());
            if (orderR.getIsDefaults() == 1) {
                allOrderVo.setStatus("Confirmed");
            } else {
                if (orderR.getIsDefaults() == 2) {
                    allOrderVo.setStatus("Cancelled");
                } else {
                    allOrderVo.setStatus("Pending");
                }
            }

            // order items
            List<OrderItemViewVO> orderItemViewVOs = new ArrayList<OrderItemViewVO>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        if (!orderItem.getItem().getName().equals("Convenience Fee")
                                        && !orderItem.getItem().getName().equals("Delivery Fee")) {
                            List<OrderItemModifierViewVO> itemModifierViewVOs = new ArrayList<OrderItemModifierViewVO>();
                            OrderItemViewVO orderItemViewVO = new OrderItemViewVO();
                            orderItemViewVO.setQuantity(orderItem.getQuantity());
                            ItemVO itemVO = new ItemVO();
                            itemVO.setPrice(orderItem.getItem().getPrice());
                            itemVO.setName(orderItem.getItem().getName());
                            orderItemViewVO.setItemVO(itemVO);
                            orderItem.setItem(null);

                            List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem
                                            .getId());
                            for (OrderItemModifier itemModifier : itemModifiers) {
                                if (itemModifier.getModifiers() != null) {
                                    OrderItemModifierViewVO itemModifierViewVO = new OrderItemModifierViewVO();
                                    itemModifierViewVO.setQuantity(itemModifier.getQuantity());

                                    itemModifier.getModifiers().setModifierGroup(null);
                                    itemModifier.getModifiers().setMerchant(null);

                                    itemModifierViewVO.setModifiers(itemModifier.getModifiers());
                                    itemModifierViewVOs.add(itemModifierViewVO);
                                }
                            }
                            orderItemViewVO.setItemModifierViewVOs(itemModifierViewVOs);
                            orderItemViewVOs.add(orderItemViewVO);
                        }
                    }
                }
            }
            allOrderVo.setOrderItemViewVOs(orderItemViewVOs);
            // order items end

            allOrderVo.setView("<p><button class='md-trigger custom-sd-button-dialog' id='viewOrderDetailPopup' data-modal= modal-15-"
                            + orderR.getId() + " orderAttr=" + orderR.getId() + ">View</button></p>");

            allOrderVos.add(allOrderVo);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(allOrderVos);
    }*/
 
 
 public String getAllFutureOrders(String merchantId) {
	 Merchant merchant=merchantRepository.findByPosMerchantId(merchantId);
 	if(merchant!=null){
 		Integer hourDifference=0;
    	Integer minutDifference=0;
    	if(merchant!=null && merchant.getTimeZone()!=null && merchant.getTimeZone().getHourDifference()!=null){
    		hourDifference=merchant.getTimeZone().getHourDifference();
    	}
    	if(merchant.getTimeZone().getMinutDifference()!=null){
			minutDifference=merchant.getTimeZone().getMinutDifference();
    	}
    	//DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
    Calendar calobj = Calendar.getInstance();
    calobj.add(Calendar.HOUR, hourDifference);
    calobj.add(Calendar.MINUTE, minutDifference);
    	System.out.println("calobj-"+calobj.getTime());
    	
    	
    	Date date = calobj.getTime();
    
 		
 		List<OrderR> orderRs = orderRepo.findByFulFilledOnAndIsFutureOrderAndMerchantIdAndIsDefaults(date,
                IConstant.BOOLEAN_TRUE, merchant.getId(), IConstant.Order_Status_Pending);
 		 List<String> notifications=new ArrayList<String>();
 		
 		for (OrderR orderR : orderRs) {
 			 //String orderDetails = "";
        	Map<String, Map<String, String>> notf = new HashMap<String, Map<String, String>>();
            Map<String, String> notification = new HashMap<String, String>();
            notification.put("appEvent", "notification");
            Map<String, String> order = new HashMap<String, String>();
            order.put("total", Double.toString(orderR.getOrderPrice()));
            order.put("tax", orderR.getTax());
            List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderR.getId());
            
            for (OrderItem orderItem : orderItems) {
                 if (orderItem != null) {
                    if (orderItem.getItem() != null) {
                        
                        List<Map<String, String>> extraList = new ArrayList<Map<String, String>>();
                        Map<String, String> poduct = new HashMap<String, String>();
                        poduct.put("product_id", orderItem.getItem().getPosItemId());
                        poduct.put("name", orderItem.getItem().getName());
                        poduct.put("price", Double.toString(orderItem.getItem().getPrice()));
                        poduct.put("qty", Integer.toString(orderItem.getQuantity()));
                        List<OrderItemModifier> itemModifiers = itemModifierRepository.findByOrderItemId(orderItem.getId());
                        for (OrderItemModifier itemModifier : itemModifiers) {
                            
                            Map<String, String> exctra = new HashMap<String, String>();
                            exctra.put("id", itemModifier.getModifiers().getPosModifierId());
                            exctra.put("price", Double.toString(itemModifier.getModifiers().getPrice()));
                            exctra.put("name", itemModifier.getModifiers().getName());
                            exctra.put("qty", Integer.toString(orderItem.getQuantity()));
                            extraList.add(exctra);
                        }
                        Gson gson = new Gson();
                        String extraJson = gson.toJson(extraList);
                        poduct.put("extras", extraJson);
                        productList.add(poduct);
                    }
                }
            }
            Gson gson = new Gson();
            String productJson = gson.toJson(productList);
            order.put("productItems", productJson);
            // order.put("productItems", productList.toString());
            String orderJson = gson.toJson(order);
            System.out.println(orderJson);
            Date fulFilledTime = orderR.getFulfilled_on();
            Calendar calobjs = Calendar.getInstance();
              calobjs.setTime(fulFilledTime);
            calobjs.add(Calendar.MINUTE, -Integer.parseInt(orderR.getOrderAvgTime()));
            Date orderFulFilledTime = calobjs.getTime();
           
            long timeInMillis = orderFulFilledTime.getTime();
           // String orderFulTime = dateFormat.format(orderFulFilledTime).toString();
            notification.put(
                    "payload",
                    orderR.getOrderPosId() + "@#You got a new order(" + orderR.getOrderPosId() + ") at "
                                    + orderR.getCreatedOn() + "@# $" + orderR.getOrderPrice() + "@# "
                                    + orderR.getOrderType() + "@#" + orderJson + "@#"
                                    + orderR.getMerchant().getPosMerchantId() + "@#"
                                    + orderR.getOrderNote() + " @#" + orderR.getPosPaymentId()+ " @#" + timeInMillis);

    notf.put("notification", notification);
    String notificationJson = gson.toJson(notf);
    notificationJson = notificationJson.trim().replaceAll("\\\\+\"", "'").replace("'[", "[")
                    .replace("]'", "]");
    notifications.add(notificationJson);
 			
 		}
 		 if(notifications.size()>0){
         	return 	notifications.toString();
         }else{
         	return null;
         }
 	}else{
		return null;
	}
		
	}


 
}