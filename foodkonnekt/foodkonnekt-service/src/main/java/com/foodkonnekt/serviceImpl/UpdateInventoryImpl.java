package com.foodkonnekt.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.ItemTax;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.ModifierModifierGroupDto;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.ModifierGroupRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.service.UpdateInventoryService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.InventoryUrlUtil;
import com.foodkonnekt.util.MailSendUtil;

@Service
public class UpdateInventoryImpl implements UpdateInventoryService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ItemmRepository itemRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private ModifierGroupRepository modifierGroupRepository;

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository   itemModifiersRepository;

    @Autowired
    private ModifiersRepository modifiersRepository;

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    @Autowired
    private OrderItemModifierRepository orderItemModifierRepository;

    /**
     * Update lineItem and taxRate
     */
    public void updateInverty(String merchantId, String objectType, String objectId, String actionType) {
        Merchant merchant = merchantRepository.findByPosMerchantId(merchantId);
        if (merchant != null) {
            System.out.println("==========Inside If condition UpdateInventoryImpl======" + merchant);
            String jsonOutPut = InventoryUrlUtil.getLineItemWithExpands(merchant, objectId);
            try {
                JSONObject itemJson = new JSONObject(jsonOutPut);
                boolean status = UnMapLineItem(itemJson, objectId, merchant);
                //if (status) {
                    Item item = updateLineItem(itemJson, objectId, merchant);
                    if(item!=null){
                    itemRepository.save(item);
                    System.out.println("-----saved Item---");
                    System.out.println("-----Start modifierGroup updation----");
                    Integer modifierGroupId = null;
                    if (itemJson.toString().contains("modifierGroups")) {
                        JSONObject modifierJObject = itemJson.getJSONObject("modifierGroups");
                        JSONArray modifierJSONArray = modifierJObject.getJSONArray("elements");
                        List<ItemModifierGroup> groups = itemModifierGroupRepository.findByItemId(item.getId());
                        for (ItemModifierGroup itemModifierGroup : groups) {
                            if (!(modifierJSONArray.toString().contains(itemModifierGroup.getModifierGroup()
                                            .getPosModifierGroupId()))) {
                                ItemModifierGroup itemModifierGroup2 = itemModifierGroupRepository.findByModifierGroupIdAndItemId(itemModifierGroup.getModifierGroup().getId(), item.getId());
                                List<ItemModifiers>itemModifiers=itemModifiersRepository.findByModifierGroupIdAndItemId(itemModifierGroup.getModifierGroup().getId(), item.getId());
                                for(ItemModifiers itemModifier:itemModifiers){
                                	itemModifier.setItem(null);
                                	itemModifier.setModifierGroup(null);
                                	itemModifier.setModifiers(null);
                                	itemModifiersRepository.delete(itemModifier);
                                }
                                
                                itemModifierGroup2.setModifierGroup(null);
                                itemModifierGroup2.setItem(null);
                                itemModifierGroupRepository.delete(itemModifierGroup2);
                                
                            }
                        }
                        for (Object jObj : modifierJSONArray) {
                            JSONObject modifierJsonObject = (JSONObject) jObj;
                            ModifierGroup modifierGroup = modifierGroupRepository
                                            .findByPosModifierGroupIdAndMerchantId(modifierJsonObject.getString("id"),
                                                            merchant.getId());
                            if (modifierGroup == null) {
                                modifierGroup = new ModifierGroup();
                                modifierGroupId = null;
                            } else {
                                modifierGroupId = modifierGroup.getId();
                            }
                            modifierGroup.setMerchant(merchant);
                            if (modifierJsonObject.toString().contains("name"))
                                modifierGroup.setName(modifierJsonObject.getString("name"));

                            if (modifierJsonObject.toString().contains("id"))
                                modifierGroup.setPosModifierGroupId(modifierJsonObject.getString("id"));

                            if (modifierJsonObject.toString().contains("showByDefault"))
                                if (modifierJsonObject.getBoolean("showByDefault")) {
                                    modifierGroup.setShowByDefault(IConstant.BOOLEAN_TRUE);
                                } else {
                                    modifierGroup.setShowByDefault(IConstant.BOOLEAN_FALSE);
                                }
                            modifierGroupRepository.save(modifierGroup);
                            ItemModifierGroup itemModifierGroup = null;

                            itemModifierGroup = itemModifierGroupRepository.findByModifierGroupIdAndItemId(
                                            modifierGroup.getId(), item.getId());
                            System.out.println("-----End modifierGroup updation----");
                            if (itemModifierGroup == null) {
                                ItemModifierGroup group = new ItemModifierGroup();
                                group.setItem(item);
                                group.setModifierGroup(modifierGroup);
                                group.setModifierGroupStatus(IConstant.BOOLEAN_TRUE);
                                itemModifierGroupRepository.save(group);
                                List<Modifiers> modifiers=modifierModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                                if(item!=null){
                              	  for(Modifiers modifier:modifiers){
                              		  ItemModifiers itemModifiers = new ItemModifiers();
                              		  itemModifiers.setItem(item);
                              		  itemModifiers.setModifiers(modifier);
                              		  itemModifiers.setModifierStatus(IConstant.BOOLEAN_TRUE);
                              		  itemModifiers.setModifierGroup(modifierGroup);
                              		  itemModifiersRepository.save(itemModifiers);
                              	  }
                                }
                                
                            }
                           // if (modifierGroupId == null) {
                                String modifierJsonOutput = InventoryUrlUtil.getModiferFromClover(merchant,
                                                modifierGroup.getPosModifierGroupId());
                                JSONObject modifierJson = new JSONObject(modifierJsonOutput);
                                JSONObject modifiersObject = modifierJson.getJSONObject("modifiers");
                                JSONArray modifiersArray = modifiersObject.getJSONArray("elements");
                                for (Object modiObject : modifiersArray) {
                                    JSONObject ModifierObject = (JSONObject) modiObject;
                                    String posModifierId=null;
                                    Integer modifierId=null;
                                    if (ModifierObject.toString().contains("id")){
                                    	posModifierId=ModifierObject.getString("id");
                                    }
                                    Modifiers modifier=null;
                                        if(posModifierId!=null){
                                        	modifier= modifiersRepository.findByPosModifierIdAndMerchantId(posModifierId,merchant.getId());
                                        	if(modifier!=null && modifier.getId()!=null){
                                        		modifierId=modifier.getId();
                                        	}
                                        }
                                        if(modifier==null){
                                        	  modifier = new Modifiers();
                                        }
                                   
                                        if (ModifierObject.toString().contains("id")){
                                        	modifier.setPosModifierId(ModifierObject.getString("id"));
                                        	}
                                    
                                    if (ModifierObject.toString().contains("name"))
                                        modifier.setName(ModifierObject.getString("name"));

                                    

                                    if (ModifierObject.toString().contains("price"))
                                        modifier.setPrice(ModifierObject.getDouble("price")/100);
                                    modifier.setMerchant(merchant);
                                    modifiersRepository.save(modifier);
                                   
                                    if( modifierId == null && modifierGroup!=null && modifier!=null ){
                                    	List<ItemModifierGroup> itemModifierGroups  = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                                    	 if(item!=null  ){
                                         	  for(ItemModifierGroup group:itemModifierGroups){
                                     		  ItemModifiers itemModifiers = new ItemModifiers();
                                     		  itemModifiers.setItem(group.getItem());
                                     		  itemModifiers.setModifiers(modifier);
                                     		  itemModifiers.setModifierStatus(IConstant.BOOLEAN_TRUE);
                                     		  itemModifiers.setModifierGroup(group.getModifierGroup());
                                     		  itemModifiersRepository.save(itemModifiers);
                                         	  }
                                     	  
                                       }
                                    	
                                    ModifierModifierGroupDto groupDto = new ModifierModifierGroupDto();
                                    groupDto.setModifierGroup(modifierGroup);
                                    groupDto.setModifiers(modifier);
                                    modifierModifierGroupRepository.save(groupDto);
                                    }
                                }
                                System.out.println("-----End modifiers and modifierModifierGroups saved----");
                            //}
                        }
                    }
                    System.out.println("-----Start categories----");
                    if (itemJson.toString().contains("categories")) {
                        JSONObject categoryJsonObject = itemJson.getJSONObject("categories");
                        Integer cetegoryId = null;
                        JSONArray categoriesArray = categoryJsonObject.getJSONArray("elements");
                        List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                        for (CategoryItem categoryItem : categoryItems) {
                            if (!(categoriesArray.toString().contains(categoryItem.getCategory().getPosCategoryId()))) {
                                CategoryItem categoryItem2 = categoryItemRepository.findByItemIdAndCategoryId(
                                                item.getId(), categoryItem.getCategory().getId());
                                categoryItem2.setCategory(null);
                                categoryItem2.setItem(null);
                                categoryItemRepository.delete(categoryItem2);
                            }
                        }
                        for (int i = 0; i < categoriesArray.length(); i++) {
                            try {
                                JSONObject categoryItem = categoriesArray.getJSONObject(i);
                                Category category = categoryRepository.findByMerchantIdAndPosCategoryId(
                                                merchant.getId(), categoryItem.getString("id"));
                                int mostAvailableOrder=0;
                                if (category == null) {
                                    category = new Category();
                                    category.setItemStatus(0);
                                    cetegoryId = null;
//                                  category order code starts
                                    Pageable pageable = new PageRequest(1 - 1, 10, Sort.Direction.DESC, "id");
                                	Page<Category> allCats= categoryRepository.findByMerchantIdAndItemStatusNot(merchant.getId(),pageable,IConstant.SOFT_DELETE);
                                	int totalCategories=(int)allCats.getTotalElements();
                                	List<Category> categories= categoryRepository.findByMerchantIdAndSortOrderNotOrderBySortOrderAsc(merchant.getId(), IConstant.BOOLEAN_FALSE);
                                	int cateListSize=categories.size();
                                	boolean categoryOrderStatus=false;
                                	if(categories!=null && categories.size()>0){
                                	for(int j=1;j<=totalCategories;j++){
                                		if(j<=cateListSize){
                                		Category category2=categories.get(j-1);
                                		if(category2!=null){
                                		if(category2.getSortOrder()!=j){
                                			mostAvailableOrder=j;
                                			categoryOrderStatus=true;
                                			break;
                                		}}}else{
                                			mostAvailableOrder=j;
                                			categoryOrderStatus=true;
                                			break;
                                		}
                                	}
                                	}else{
                                		mostAvailableOrder=1;
                                		categoryOrderStatus=true;
                                	}
                                	
                                	if(categoryOrderStatus)
                                	category.setSortOrder(mostAvailableOrder);
                                	else{
                                		category.setSortOrder(totalCategories+1);
                                	}
//                                    category order code ends
                                } else {
                                    cetegoryId = category.getId();
                                }

                                category.setMerchant(merchant);
                                if (categoryItem.toString().contains("name"))
                                    category.setName(categoryItem.getString("name"));

                                if (categoryItem.toString().contains("id"))
                                    category.setPosCategoryId(categoryItem.getString("id"));

                                /*if (categoryItem.toString().contains("sortOrder"))
                                    category.setSortOrder(categoryItem.getInt("sortOrder"));
                                else
                                	category.setSortOrder(0);*/

                               /* if (cetegoryId == null) {
                                    Set<Item> items = new HashSet<Item>();
                                    items.add(item);
                                    category.setItems(items);
                                }*/
                                categoryRepository.save(category);
                                CategoryItem categoryItem2 = categoryItemRepository.findByItemIdAndCategoryId(
                                                item.getId(), category.getId());
                                if (categoryItem2 == null) {
                                    categoryItem2 = new CategoryItem();
                                    categoryItem2.setCategory(category);
                                    categoryItem2.setItem(item);
                                    categoryItemRepository.save(categoryItem2);
                                }
                                System.out.println("saved category " + category);
                            
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("-----End categories saved----");
                //}
                System.out.println("-----Done----");
                    }
            } catch (Exception exception) {
                if (exception != null) {
                }
                exception.printStackTrace();
            }
        } else {
            System.out.println("==========Inside else part UpdateInventoryImpl======" + merchant);
        }
    }

    private Item updateLineItem(JSONObject itemJson, String objectId, Merchant merchant) {
        Item item = itemRepository.findByPosItemIdAndMerchantId(objectId, merchant.getId());
        if (item !=null){
          
        item.setMerchant(merchant);
        if (itemJson.toString().contains("name"))
            item.setName(itemJson.getString("name"));

        if (itemJson.toString().contains("id")&& itemJson.has("id") )
            item.setPosItemId(itemJson.getString("id"));

        if (itemJson.toString().contains("hidden"))
            item.setIsHidden(itemJson.getBoolean("hidden"));

        if (itemJson.toString().contains("price"))
            item.setPrice(itemJson.getDouble("price") / 100);

        if (itemJson.toString().contains("priceType"))
            item.setPriceType(itemJson.getString("priceType"));

        if (itemJson.toString().contains("defaultTaxRates"))
            item.setIsDefaultTaxRates(itemJson.getBoolean("defaultTaxRates"));

        if (itemJson.toString().contains("unitName"))
            item.setUnitName(itemJson.getString("unitName"));

        if (itemJson.toString().contains("isRevenue"))
            item.setIsRevenue(itemJson.getBoolean("isRevenue"));

        if (itemJson.toString().contains("modifiedTime"))
            item.setModifiedTime(new Date(itemJson.getLong("modifiedTime")));

        if (itemJson.toString().contains("taxRates")) {
            JSONObject taxRateJsonObject = itemJson.getJSONObject("taxRates");
            JSONArray taxRateJsonArray = taxRateJsonObject.getJSONArray("elements");
            List<ItemTax> itemTaxs = itemTaxRepository.findByItemId(item.getId());
            for (ItemTax itemTax : itemTaxs) {
                if (!(taxRateJsonArray.toString().contains(itemTax.getTaxRates().getPosTaxRateId()))) {
                	List<ItemTax> itemTaxRates = itemTaxRepository.findByItemIdAndTaxRatesId(item.getId(), itemTax.getId());
                    for(ItemTax itemTax2:itemTaxRates){itemTax2.setItem(null);
                    itemTax2.setTaxRates(null);
                    itemTaxRepository.delete(itemTax2);}
                }
            }
            Set<TaxRates> taxes = new HashSet<TaxRates>();
            for (Object jObj : taxRateJsonArray) {
                JSONObject taxRate = (JSONObject) jObj;
                TaxRates rate = taxRateRepository.findByMerchantIdAndPosTaxRateId(merchant.getId(),
                                taxRate.getString("id"));
                if (rate == null)
                    rate = new TaxRates();

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
                taxRateRepository.save(rate);
                taxes.add(rate);
            }
            item.setTaxes(taxes);
        }
        System.out.println("-----saved TaxRate----");
        }
        return item;
    }

    private boolean UnMapLineItem(JSONObject itemJson, String objectId, Merchant merchant) {
        boolean finalStatus = false;
        try{
        Item item = itemRepository.findByPosItemIdAndMerchantId(objectId, merchant.getId());
        if (null == item) {
            return true;
        }
        List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
        for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
        	if(itemModifierGroup.getModifierGroup()!=null){
            boolean status = checkPosIdInLineItemJson(itemJson, itemModifierGroup.getModifierGroup()
                            .getPosModifierGroupId(), merchant);
            
                
            }
        }
        
        List<ItemModifiers> itemModifiers = itemModifiersRepository.findByItemId(item.getId());
        for (ItemModifiers itemModifier : itemModifiers) {
        	if(itemModifier.getModifiers()!=null){
            boolean status = checkModifierPosIdInLineItemJson(itemJson, itemModifier.getModifiers().getPosModifierId(), merchant);
            
                
            }
        }

        List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
        for (CategoryItem categoryItem : categoryItems) {
        	if(categoryItem.getCategory()!=null){
            boolean status = checkCategoryPosIdInLineItemJson(itemJson, categoryItem.getCategory().getPosCategoryId(),
                            merchant);
            
        	}
        }

        List<ItemTax> itemTaxs = itemTaxRepository.findByItemId(item.getId());
        for (ItemTax itemTax : itemTaxs) {
            boolean status = checkTaxPosIdInLineItemJson(itemJson, itemTax.getTaxRates().getPosTaxRateId(), merchant);
            if (status) {
                finalStatus = true;
                itemTaxRepository.delete(itemTax);
            }
        }
        }catch(Exception e){
        	//MailSendUtil.webhookMail(merchant.getPosMerchantId()+" , "+objectId +" , "+e);
        }
        return finalStatus;
    }
    
    
    
    private boolean checkModifierPosIdInLineItemJson(JSONObject itemJson, String posId, Merchant merchant) {
        if (itemJson.toString().contains(posId)) {
            return false;
        } else {
           
                    Modifiers modifiers = modifiersRepository.findByPosModifierIdAndMerchantId(posId, merchant.getId());
                    if(modifiers!=null){
                    List<ItemModifiers> itemModifiers=itemModifiersRepository.findByModifiersId(modifiers.getId());
                    for(ItemModifiers itemModifier:itemModifiers){
                    	itemModifier.setItem(null);
                    	itemModifier.setModifierGroup(null);
                    	itemModifier.setModifiers(null);
                    	itemModifiersRepository.delete(itemModifier);
                    }
                    System.out.println("-------------------------------------------------");
                    //modifiers.setIsDeleted(IConstant.SOFT_DELETE);
                    modifiersRepository.save(modifiers);
                    System.out.println("====================================================");
                    	
                    }
                    return true;     
                
            }
        }
      
    

    private boolean checkPosIdInLineItemJson(JSONObject itemJson, String posId, Merchant merchant) {
        if (itemJson.toString().contains(posId)) {
            return false;
        } else {
            String modifierGroupJson = InventoryUrlUtil.checkModiferGroup(merchant, posId);
            String message = null;
            if (modifierGroupJson.contains("message")) {
                JSONObject modiferJson = new JSONObject(modifierGroupJson);
                message = modiferJson.getString("message");
                if (message.equals("Not Found")) {
                    ModifierGroup modifierGroup = modifierGroupRepository.findByPosModifierGroupIdAndMerchantId(posId,
                                    merchant.getId());
                    if(modifierGroup!=null){
                    List<ItemModifierGroup> itemModifierGroups=itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                    for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
                    	itemModifierGroup.setItem(null);
                        itemModifierGroup.setModifierGroup(null);
                        itemModifierGroupRepository.delete(itemModifierGroup);
                    }
                    System.out.println("-------------------------------------------------");
                    modifierGroup.setShowByDefault(IConstant.SOFT_DELETE);
                    modifierGroupRepository.save(modifierGroup);
                    System.out.println("====================================================");
                    	
                    }
                    
                   /* List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                    for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                        List<Modifiers> modifiers = modifierModifierGroupRepository
                                        .findByModifierGroupId(itemModifierGroup.getModifierGroup().getId());
                        for (Modifiers modifiers2 : modifiers) {
                            List<OrderItemModifier> orderItemModifiers = orderItemModifierRepository
                                            .findByModifiersId(modifiers2.getId());
                            orderItemModifierRepository.delete(orderItemModifiers);
                            List<ModifierModifierGroupDto> dtos = modifierModifierGroupRepository
                                            .findByModifiersId(modifiers2.getId());
                            modifierModifierGroupRepository.delete(dtos);
                            modifiers2.setModifierGroup(null);
                        }
                        modifiersRepository.delete(modifiers);
                    }
                    itemModifierGroupRepository.delete(itemModifierGroups);*/
                    
                    return true;

                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean checkCategoryPosIdInLineItemJson(JSONObject itemJson, String posCategoryId, Merchant merchant) {
        if (itemJson.toString().contains(posCategoryId)) {
            return false;
        } else {
        	 String categoryJson = InventoryUrlUtil.checkCategory(merchant, posCategoryId);
        	String message = null;
            if (categoryJson.contains("message")) {
                JSONObject modiferJson = new JSONObject(categoryJson);
                message = modiferJson.getString("message");
                if (message.equals("Not Found")) {
                	Category category=categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(), posCategoryId);
                	if(category!=null){
                		Integer deletedOrder=category.getSortOrder();
                		List<Category> categories= categoryRepository.findByMerchantIdAndSortOrderNotOrderBySortOrderAsc(merchant.getId(), IConstant.BOOLEAN_FALSE);
                		if(deletedOrder==null || deletedOrder==0){
                    		
                			Pageable pageable = new PageRequest(1 - 1, 10, Sort.Direction.DESC, "id");
                        	Page<Category> allCats= categoryRepository.findByMerchantIdAndItemStatusNot(merchant.getId(),pageable,IConstant.SOFT_DELETE);
                        	int totalCategories=(int)allCats.getTotalElements();
                        	int cateListSize=categories.size();
                        	if(categories!=null && categories.size()>0){
                        	for(int j=1;j<=totalCategories;j++){
                        		if(j<=cateListSize){
                        		Category category2=categories.get(j-1);
                        		if(category2!=null){
                        		if(category2.getSortOrder()!=j){
                        			deletedOrder=j;
                        			break;
                        		}}}else{
                        			deletedOrder=j;
                        			break;
                        		}
                        	}
                        	}else{
                        		deletedOrder=1;
                        	}
                        	}
                		
                		if(deletedOrder!=null && deletedOrder!=0){
                		int nextOrder=deletedOrder;
                		for(Category category2:categories){
                			if(deletedOrder!=null && category2.getSortOrder()>deletedOrder){
                				category2.setSortOrder(nextOrder++);
                				categoryRepository.save(category2);
                			}
                		}}
                		
                		
                			
                        	
                        	
                		
                	List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
                    for (CategoryItem categoryItem : categoryItems) {
                        
                    	categoryItem.setCategory(null);
                    	categoryItem.setItem(null);
                            categoryItemRepository.delete(categoryItem);
                        
                    }
                    category.setItemStatus(IConstant.SOFT_DELETE);
                    category.setSortOrder(0);
                    categoryRepository.save(category);
                }
                }
            }
            return true;
            
        }
    }

    private boolean checkTaxPosIdInLineItemJson(JSONObject itemJson, String posTaxId, Merchant merchant) {
        if (itemJson.toString().contains(posTaxId)) {
            return false;
        } else {
            return true;
        }
    }
}
