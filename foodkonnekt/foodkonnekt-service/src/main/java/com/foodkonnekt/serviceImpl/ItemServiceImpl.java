package com.foodkonnekt.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodkonnekt.clover.vo.InventoryItemVo;
import com.foodkonnekt.clover.vo.PersonJsonObject;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.ItemTiming;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemTimingRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.service.ItemService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.JsonUtil;
import com.foodkonnekt.util.MailSendUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemmRepository itemmRepository;
    
    @Autowired
    private ItemTimingRepository itemTimingRepository;

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository itemModifiersRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;
    
    @Autowired
    private ModifiersRepository modifiersRepository;

    /**
     * Save items
     */
    public void saveItem(String itemmJson, Merchant merchant) {
        List<Item> items = JsonUtil.setItems(itemmJson, merchant);
        itemmRepository.save(items);
    }

    /**
     * Find by merchantId from database
     */
    public List<Item> findByMerchantId(Integer merchantId) {
        return itemmRepository.findByMerchantId(merchantId);
    }

    /**
     * Find modifierGroup by item id from database
     */
    public List<ItemModifierGroup> findByItemId(Integer itemId) {
        return itemModifierGroupRepository.findByItemId(itemId);
    }

    /**
     * Find by merchantId
     */
    public List<Item> findLineItemByMerchantId(int merchantId) {
        List<Item> items = itemmRepository.findByMerchantId(merchantId);
        try {
            for (Item item : items) {
                String categoryName = "";
                String modifierGroupName = "";
                List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                for (CategoryItem categoryItem : categoryItems) {
                    categoryName = categoryName + "," + categoryItem.getCategory().getName();
                }
                categoryName = categoryName.trim();
                if (!categoryName.isEmpty())
                    item.setCategoriesName(categoryName.substring(1, categoryName.length()));
                List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
                for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                    modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                }
                modifierGroupName = modifierGroupName.trim();
                if (!modifierGroupName.isEmpty())
                    item.setModifierGroups(modifierGroupName.substring(1, modifierGroupName.length() - 1));

                item.setCategories(null);
                item.setItemModifierGroups(null);
                item.setTaxes(null);
                item.setOrderItems(null);
                item.setMerchant(null);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Find by merchantId
     */
    public Long itemCountByMerchantId(Integer merchantId) {
        return itemmRepository.itemCountByMerchantId(merchantId);
    }

    public Item findItemByItemId(int itemId) {
        Item item = itemmRepository.findOne(itemId);
        try {
        	
        	List<ItemTiming> itemTimings=itemTimingRepository.findByItemId(itemId);
        	if(itemTimings!=null && itemTimings.size()>0){
        	item.setItemTimings(itemTimings);
        	String days="";
        	String startTime="00:00 AM";
        	String endTime="00:00 AM";
        	for(ItemTiming itemTiming:itemTimings){
        		days=days+","+itemTiming.getDay();
        		if(itemTiming.getStartTime()!=null && itemTiming.getEndTime()!=null && !itemTiming.isHoliday()){
        			startTime=itemTiming.getStartTime();
        			endTime=itemTiming.getEndTime();
        		}
        	}
        	if(days!=null && !days.isEmpty() && !days.equals(""))
        	days.replaceFirst(",", "");
        	
        	item.setStartTime(startTime);
        	item.setEndTime(endTime);
        	item.setDays(days);
        	}else{
        		itemTimings= new ArrayList<ItemTiming>();
        		String weekDays[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
            	for(String day:weekDays){
            		ItemTiming itemTiming= new ItemTiming();
            		itemTiming.setDay(day);
            		itemTiming.setStartTime("00:00");
            		itemTiming.setEndTime("00:00");
            		itemTiming.setHoliday(true);
            		itemTimings.add(itemTiming);
            	}
            	item.setItemTimings(itemTimings);
        	}
            List<String> categories = new ArrayList<String>();
            List<String> extras = new ArrayList<String>();
            List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
            
            System.out.println("count output is:-----"+itemModifierGroupRepository.categoryItemModifierCount(item.getId()));
         
            
            
            for (CategoryItem categoryItem : categoryItems) {
                categories.add(categoryItem.getCategory().getName());
            }
            item.setCategoryList(categories);
            List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemIdOrderBySortOrderAsc(item.getId());

            
            boolean flag=false;
            List<Long> count=itemModifierGroupRepository.categoryItemModifierCount(item.getId());
            for(Long counts:count)
            {
            	if(counts >1)
            	{
            		flag=true;
            		break;
            	}
            }
            int groupStatus=1;
            if(flag){
            	for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
            		itemModifierGroup.setSortOrder(groupStatus++);
            	}
            	itemModifierGroupRepository.save(itemModifierGroups);
            }
            
            for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {

                ModifierGroup group = itemModifierGroup.getModifierGroup();
                if (itemModifierGroup.getModifiersLimit() == null) {
                    itemModifierGroup.setModifiersLimit(1);
                }

                List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(group.getId());
                Set modifiersSet = new HashSet(modifiers);
                group.setModifiers(modifiersSet);

            }
            item.setItemModifierGroups(itemModifierGroups);

            for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                extras.add(itemModifierGroup.getModifierGroup().getName());
            }
            item.setExtras(extras);
            item.setCategories(null);
            item.setTaxes(null);
            item.setOrderItems(null);
            item.setMerchant(null);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }

        return item;
    }

    /**
     * Update line item
     */
    public void updateLineItemValue(Item item) {
        try {
        	String weekDays[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        	List<ItemTiming> itemTimings=itemTimingRepository.findByItemId(item.getId());
        	if(itemTimings!=null && itemTimings.size()>0){
        		itemTimingRepository.deleteByItemId(item.getId());
        	}
        	//
            Item resultItem = itemmRepository.findOne(item.getId());
            String modifierLimit = item.getModifierLimits();
            String days=item.getDays();
            String startTime=item.getStartTime();
            String endTime=item.getEndTime();
             itemTimings= new ArrayList<ItemTiming>();
            if((days!=null && !days.isEmpty() && !days.equals(""))&& ( item.getAllowItemTimings()!=null && item.getAllowItemTimings()!=0) ){
            	String timingDays[]=days.split(",");
            	for(String day:weekDays){
            		if(!days.contains(day)){
            		ItemTiming itemTiming= new ItemTiming();
            		itemTiming.setDay(day);
            		itemTiming.setStartTime("00:00 AM");
            		itemTiming.setEndTime("00:00 AM");
            		itemTiming.setItem(resultItem);
            		itemTiming.setHoliday(true);
            		itemTimings.add(itemTiming);
            		}else{
            			ItemTiming itemTiming= new ItemTiming();
                		itemTiming.setDay(day);
                		itemTiming.setStartTime(startTime);
                		itemTiming.setEndTime(endTime);
                		itemTiming.setItem(resultItem);
                		itemTiming.setHoliday(false);
                		itemTimings.add(itemTiming);
            		}
            	}
            	
            }else{
            	
            	for(String day:weekDays){
            		ItemTiming itemTiming= new ItemTiming();
            		itemTiming.setDay(day);
            		itemTiming.setStartTime("00:00 AM");
            		itemTiming.setEndTime("00:00 AM");
            		itemTiming.setItem(resultItem);
            		itemTiming.setHoliday(false);
            		itemTimings.add(itemTiming);
            	}
            }
            
            resultItem.setItemTimings(itemTimings);
            String itemModifierGroupsId = item.getItemModifierGroupsIds();
            String itemModifierGroupsStatusIds = item.getItemModifierGroupsStatusIds();
            String itemModifiersStatusIds=item.getItemModifiersStatusIds();
            Integer allowModifierGroupOrder = item.getAllowModifierGroupOrder();
            System.out.println("allowModifierGroupOrder--"+allowModifierGroupOrder);
            List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
            List<ItemModifiers> itemModifiers = itemModifiersRepository.findByItemId(item.getId());
            if(itemModifierGroupsStatusIds!=null && !itemModifierGroupsStatusIds.isEmpty() && !itemModifierGroupsStatusIds.equals("null") && !itemModifierGroupsStatusIds.equals("undefined")){
            
            
            for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
            	if(itemModifierGroupsStatusIds.contains(itemModifierGroup.getId().toString())){
            		itemModifierGroup.setModifierGroupStatus(IConstant.BOOLEAN_TRUE);
            	}else{
            		itemModifierGroup.setModifierGroupStatus(IConstant.BOOLEAN_FALSE);
            	}
            }
            itemModifierGroupRepository.save(itemModifierGroups);
            }else if(itemModifierGroups!=null && itemModifierGroups.size()>0){
            	for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
                	
                		itemModifierGroup.setModifierGroupStatus(IConstant.BOOLEAN_FALSE);
                	
                }
                itemModifierGroupRepository.save(itemModifierGroups);
            }
            
            if(itemModifiersStatusIds!=null && !itemModifiersStatusIds.isEmpty() && !itemModifiersStatusIds.equals("null") && !itemModifiersStatusIds.equals("undefined")){
                
                
                for(ItemModifiers itemModifier:itemModifiers){
                	if(itemModifiersStatusIds.contains(itemModifier.getId().toString())){
                		itemModifier.setModifierStatus(IConstant.BOOLEAN_TRUE);
                	}else{
                		itemModifier.setModifierStatus(IConstant.BOOLEAN_FALSE);
                	}
                }
                itemModifiersRepository.save(itemModifiers);
                }else if(itemModifiers!=null && itemModifiers.size()>0){
                	for(ItemModifiers itemModifier:itemModifiers){
                    	
                		itemModifier.setModifierStatus(IConstant.BOOLEAN_FALSE);
                    	
                    }
                	itemModifiersRepository.save(itemModifiers);
                }
            

            if (modifierLimit != null || itemModifierGroupsId != null) {
                String[] modifierLimits = modifierLimit.split(",");
                String[] itemModifierGroupsIds = itemModifierGroupsId.split(",");
                int index = 0;
                for (String id : itemModifierGroupsIds) {
                    Integer itmModiGrpid = Integer.parseInt(id);
                    ItemModifierGroup itemModifierGroup = itemModifierGroupRepository.findOne(itmModiGrpid);
                    List<Modifiers> modifiers=null;
                    if(itemModifierGroup!=null){
                    modifiers=modifierModifierGroupRepository.findByModifierGroupId(itemModifierGroup.getModifierGroup().getId());
                    }
                    if(modifierLimits.length>index&&modifierLimits[index]!=null && !modifierLimits[index].isEmpty()&& !modifierLimits[index].equals("")){
                    Integer modifierLmt = Integer.parseInt(modifierLimits[index]);
                    
                    if (item.getAllowModifierLimit() == 0) {
                        itemModifierGroup.setModifiersLimit(1);
                        itemModifierGroup.setIsMaxLimit(IConstant.BOOLEAN_FALSE);
                    } else {
                    	if(modifiers!=null && !modifiers.isEmpty()&&modifierLmt>=modifiers.size()){
                    		itemModifierGroup.setIsMaxLimit(IConstant.BOOLEAN_TRUE);
                    	}else{
                    		itemModifierGroup.setIsMaxLimit(IConstant.BOOLEAN_FALSE);
                    	}
                        itemModifierGroup.setModifiersLimit(modifierLmt);
                    }
                    itemModifierGroupRepository.save(itemModifierGroup);
                    index++;
                    }else{
                    	
                    	if(modifiers!=null && !modifiers.isEmpty()){
                    		itemModifierGroup.setModifiersLimit(modifiers.size());
                    		itemModifierGroup.setIsMaxLimit(IConstant.BOOLEAN_TRUE);
                    		itemModifierGroupRepository.save(itemModifierGroup);
                    	}
                    	index++;
                    }

                }
            }

            resultItem.setDescription(item.getDescription());
            resultItem.setAllowModifierLimit(item.getAllowModifierLimit());
            resultItem.setAllowItemTimings(item.getAllowItemTimings());
            resultItem.setItemStatus(item.getItemStatus());
            resultItem.setModifiersLimit(item.getModifiersLimit());
            resultItem.setAllowModifierGroupOrder(allowModifierGroupOrder);
            Merchant merchant=resultItem.getMerchant();
            if(merchant!=null && merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()!=1){
            	if(item.getPrice()!=null)
            	resultItem.setPrice(item.getPrice());
            	if(item.getName()!=null)
                	resultItem.setName(item.getName());
            }
            itemmRepository.save(resultItem);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
    }

    /**
     * Find by categoryId
     */
    public List<Item> findByCategoryId(Integer categoryId) {
        List<Item> items = new ArrayList<Item>();
        try {
            List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(categoryId);
            for (CategoryItem categoryItem : categoryItems) {
                categoryItem.getItem().setCategories(null);
                categoryItem.getItem().setItemModifierGroups(null);
                categoryItem.getItem().setTaxes(null);
                categoryItem.getItem().setOrderItems(null);
                categoryItem.getItem().setMerchant(null);
                items.add(categoryItem.getItem());
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Find Items by categoryId
     */
    public List<Item> findItemByCategoryId(Integer categoryId) {
        List<Item> items = new ArrayList<Item>();
        try {
            List<CategoryItem> categoryItemsList = categoryItemRepository.findByCategoryId(categoryId);
            for (CategoryItem categoryItem : categoryItemsList) {
                if(categoryItem.getItem()!=null&& categoryItem.getItem().getItemStatus()!=null && categoryItem.getItem().getItemStatus()!=IConstant.SOFT_DELETE){
                String categoryName = "";
                String modifierGroupName = "";
                List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(categoryItem.getItem().getId());
                for (CategoryItem cateItem : categoryItems) {
                    categoryName = categoryName + "," + cateItem.getCategory().getName();
                }
                categoryName = categoryName.trim();
                if (!categoryName.isEmpty())
                    categoryItem.getItem().setCategoriesName(categoryName.substring(1, categoryName.length()));
                List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(categoryItem
                                .getItem().getId());
                for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                    modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                }
                modifierGroupName = modifierGroupName.trim();
                if (!modifierGroupName.isEmpty())
                    categoryItem.getItem().setModifierGroups(
                                    modifierGroupName.substring(1, modifierGroupName.length() - 1));

                categoryItem.getItem().setCategories(null);
                categoryItem.getItem().setItemModifierGroups(null);
                categoryItem.getItem().setTaxes(null);
                categoryItem.getItem().setOrderItems(null);
                categoryItem.getItem().setMerchant(null);
                int selectedMenuOrder=0;
                Integer sortOrder=categoryItem.getSortOrder();
                if(sortOrder!=null && !sortOrder.equals("")){
                	try{
                		selectedMenuOrder=sortOrder;
                	}catch(Exception e){
                		
                	}
                }
                String menuOrder="";
                if (categoryItem.getItem().getItemStatus()!=null && categoryItem.getItem().getItemStatus() == 0) {
                 menuOrder="<select  class='category_order' id='menuOrdr_"+categoryItem.getId()+"' style='width: 45%' catid=" + categoryItem.getId()+ ">";
                
                }else {
                	menuOrder="<select class='category_order' id='menuOrdr_"+categoryItem.getId()+"' style='width: 45%' catid=" + categoryItem.getId()+ " disabled>";
                	menuOrder=menuOrder+"<option id='0' value='0' selected>0</option>";
                }
                for(int i=1;i<=categoryItemsList.size();i++){
                	if(selectedMenuOrder==i){
                		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"' selected>"+i+"</option>";
                	}else{
                		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"'>"+i+"</option>";
                	}
                	
                	}
                menuOrder=menuOrder+"</select>";
                categoryItem.getItem().setMenuOrder(menuOrder);
                items.add(categoryItem.getItem());
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return items;
    }

    public String findinventoryLineItemByMerchantId(int merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.ASC, "id");
        //Page<Item> page = itemmRepository.findByMerchantId(merchantId, pageable);
        Page<Item> page = itemmRepository.findByMerchantIdAndItemStatusNot(merchantId, pageable,IConstant.SOFT_DELETE);
        
        List<InventoryItemVo> inventoryItemVos = new ArrayList<InventoryItemVo>();
        try {
            for (Item item : page.getContent()) {
                InventoryItemVo inventoryItemVo = new InventoryItemVo();
                String categoryName = "";
                String modifierGroupName = "";
                List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                for (CategoryItem categoryItem : categoryItems) {
                    categoryName = categoryName + "," + categoryItem.getCategory().getName();
                }
                categoryName = categoryName.trim();
                if (!categoryName.isEmpty()) {
                    inventoryItemVo.setCategoriesName(categoryName.substring(1, categoryName.length()));
                } else {
                    inventoryItemVo.setCategoriesName("");
                }
                List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
                if(itemModifierGroups!=null){
                for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                	if(itemModifierGroup.getModifierGroupStatus()!=null &&itemModifierGroup.getModifierGroupStatus()==IConstant.BOOLEAN_TRUE)
                    modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                }
                }
                modifierGroupName = modifierGroupName.trim();
                if (modifierGroupName!=null && !modifierGroupName.isEmpty()) {
                    inventoryItemVo.setModifierGroups(modifierGroupName.substring(1, modifierGroupName.length() - 1));
                } else {
                    inventoryItemVo.setModifierGroups("");
                }
                inventoryItemVo.setId(item.getId());
                inventoryItemVo.setName(item.getName());
                if(item.getItemStatus()!=null && item.getItemStatus() != IConstant.SOFT_DELETE){
                if (item.getItemStatus()!=null && item.getItemStatus() == 0) {
                    //inventoryItemVo.setStatus("Active");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                    + " style='color: blue;'>Active</a></div>");
                    
                    
                } else {
                    //inventoryItemVo.setStatus("InActive");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                    + " style='color: blue;'>InActive</a></div>");
                }
                inventoryItemVo.setPrice(item.getPrice());
                inventoryItemVo.setAction("<a href=addLineItem?itemId=" + item.getId()
                                + " class='edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
                inventoryItemVos.add(inventoryItemVo);
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        inventoryItemVos = getListBasedOnSearchParameter(searchParameter, inventoryItemVos);

        PersonJsonObject personJsonObject = new PersonJsonObject();
        // Set Total display record
        //personJsonObject.setiTotalDisplayRecords(pageDisplayLength * page.getTotalPages());
        personJsonObject.setiTotalDisplayRecords((int)page.getTotalElements());
        // Set Total record
        personJsonObject.setiTotalRecords((int)page.getTotalElements());
        personJsonObject.setAaData(inventoryItemVos);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(personJsonObject);

        return json2;
    }

    private List<InventoryItemVo> getListBasedOnSearchParameter(String searchParameter,
                    List<InventoryItemVo> personsList) {

        if (null != searchParameter && !searchParameter.equals("")) {
            List<InventoryItemVo> personsListForSearch = new ArrayList<InventoryItemVo>();
            searchParameter = searchParameter.toUpperCase();
            for (InventoryItemVo person : personsList) {
                if (person.getName().toUpperCase().indexOf(searchParameter) != -1
                                || person.getCategoriesName().toUpperCase().indexOf(searchParameter) != -1
                                || person.getModifierGroups().toUpperCase().indexOf(searchParameter) != -1) {
                    personsListForSearch.add(person);
                }
            }
            personsList = personsListForSearch;
            personsListForSearch = null;
        }
        return personsList;
    }

    public String filterInventoryByCategoryId(Integer merchantId, int categoryId) {
        if (categoryId == 0) {
            List<Item> page = itemmRepository.findByMerchantId(merchantId);
            List<InventoryItemVo> inventoryItemVos = new ArrayList<InventoryItemVo>();
            try {
                for (Item item : page) {
                    InventoryItemVo inventoryItemVo = new InventoryItemVo();
                    String categoryName = "";
                    String modifierGroupName = "";
                    List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                   
                    for (CategoryItem categoryItem : categoryItems) {
                    	if(categoryItem.getCategory().getItemStatus()!=IConstant.SOFT_DELETE)
                        categoryName = categoryName + "," + categoryItem.getCategory().getName();
                    }
                    categoryName = categoryName.trim();
                    if (!categoryName.isEmpty()) {
                        inventoryItemVo.setCategoriesName(categoryName.substring(1, categoryName.length()));
                    } else {
                        inventoryItemVo.setCategoriesName("");
                    }
                    List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
                    for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                    	if(itemModifierGroup.getModifierGroup().getShowByDefault()!=IConstant.SOFT_DELETE)
                        modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                    }
                    modifierGroupName = modifierGroupName.trim();
                    if (!modifierGroupName.isEmpty()) {
                        inventoryItemVo.setModifierGroups(modifierGroupName.substring(1, modifierGroupName.length() - 1));
                    } else {
                        inventoryItemVo.setModifierGroups("");
                    }
                    inventoryItemVo.setId(item.getId());
                    inventoryItemVo.setName(item.getName());
                    if(item.getItemStatus()!=null && item.getItemStatus() != IConstant.SOFT_DELETE){
                    if (item.getItemStatus()!=null && item.getItemStatus() == 0) {
                        //inventoryItemVo.setStatus("Active");
                    	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                        + " style='color: blue;'>Active</a></div>");
                        
                        
                    } else {
                        //inventoryItemVo.setStatus("InActive");
                    	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                        + " style='color: blue;'>InActive</a></div>");
                    }
                    inventoryItemVo.setPrice(item.getPrice());
                    inventoryItemVo.setAction("<a href=addLineItem?itemId=" + item.getId()
                                    + " class='edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
                    inventoryItemVos.add(inventoryItemVo);
                    }
                }
            } catch (Exception e) {
                if (e != null) {
                    MailSendUtil.sendExceptionByMail(e);
                }
                e.printStackTrace();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(inventoryItemVos);
        } else {
        	System.out.println("filterInventoryByCategoryId");
            List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryIdOrderBySortOrderAsc(categoryId);
            System.out.println(categoryItems.size());
            
            System.out.println("count output is:-----"+categoryItemRepository.categoryItemCount(categoryId));
            boolean flag=false;
            List<Long> count=categoryItemRepository.categoryItemCount(categoryId);
            for(Long counts:count)
            {
            	if(counts >1)
            	{
            		flag=true;
            		break;
            	}
            }
            int sortOrdr=1;
            if(flag){
            	for(CategoryItem categoryItem:categoryItems){
            		categoryItem.setSortOrder(sortOrdr++);
            	}
            	categoryItemRepository.save(categoryItems);
            }
            
            List<InventoryItemVo> inventoryItemVos = new ArrayList<InventoryItemVo>();
            for (CategoryItem categoryItem : categoryItems) {
                InventoryItemVo inventoryItemVo = new InventoryItemVo();
                String modifierGroupName = "";
                inventoryItemVo.setCategoriesName(categoryItem.getCategory().getName());
                List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(categoryItem
                                .getItem().getId());
                for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                    modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                }
                modifierGroupName = modifierGroupName.trim();
                if (!modifierGroupName.isEmpty()) {
                    inventoryItemVo.setModifierGroups(modifierGroupName.substring(1, modifierGroupName.length() - 1));
                } else {
                    inventoryItemVo.setModifierGroups("");
                }
                inventoryItemVo.setId(categoryItem.getItem().getId());
                inventoryItemVo.setName(categoryItem.getItem().getName());
                if(categoryItem.getItem().getItemStatus()!=null && categoryItem.getItem().getItemStatus() != IConstant.SOFT_DELETE){
                if (categoryItem.getItem().getItemStatus()!=null && categoryItem.getItem().getItemStatus() == 0) {
                    //inventoryItemVo.setStatus("Active");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + categoryItem.getItem().getId()
                    + "  categoryItemId=" + categoryItem.getId()+ " categoryId=" + categoryItem.getCategory().getId()+ "    style='color: blue;'>Active</a></div>");
                    
                } else {
                    //inventoryItemVo.setStatus("InActive");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + categoryItem.getItem().getId()
                    + "  categoryItemId=" + categoryItem.getId()+ "      categoryId=" + categoryItem.getCategory().getId()+ "  style='color: blue;'>InActive</a></div>");
                }
                inventoryItemVo.setPrice(categoryItem.getItem().getPrice());
                inventoryItemVo.setAction("<a href=addLineItem?itemId=" + categoryItem.getItem().getId()
                                + " class='edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
                
                int selectedMenuOrder=0;
                Integer sortOrder=categoryItem.getSortOrder();
                if(sortOrder!=null && !sortOrder.equals("")){
                	try{
                		selectedMenuOrder=sortOrder;
                	}catch(Exception e){
                		
                	}
                }
                String menuOrder="";
                if (categoryItem.getItem().getItemStatus()!=null && categoryItem.getItem().getItemStatus() == 0) {
                 menuOrder="<select  class='category_order' id='menuOrdr_"+categoryItem.getId()+"' style='width: 75px;' catid=" + categoryItem.getId()+ ">";
                
                }else {
                	menuOrder="<select class='category_order' id='menuOrdr_"+categoryItem.getId()+"' style='width: 75px;' catid=" + categoryItem.getId()+ " disabled>";
                	menuOrder=menuOrder+"    <option id='0' value='0' selected>0</option>";
                }
                for(int i=1;i<=categoryItems.size();i++){
                	if(selectedMenuOrder==i){
                		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"' selected>"+i+"</option>";
                	}else{
                		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"'>"+i+"</option>";
                	}
                	
                	}
                menuOrder=menuOrder+"</select>";
                inventoryItemVo.setMenuOrder(menuOrder);
                inventoryItemVos.add(inventoryItemVo);}
                
                
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(inventoryItemVos);
        }
    }

    public String searchItemByTxt(Integer merchantId, String searchTxt) {
        List<Item> items = itemmRepository.findByMerchantIdAndItemName(merchantId, searchTxt);
        List<InventoryItemVo> inventoryItemVos = new ArrayList<InventoryItemVo>();
        try {
            for (Item item : items) {
                InventoryItemVo inventoryItemVo = new InventoryItemVo();
                String categoryName = "";
                String modifierGroupName = "";
                List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                for (CategoryItem categoryItem : categoryItems) {
                    categoryName = categoryName + "," + categoryItem.getCategory().getName();
                }
                categoryName = categoryName.trim();
                if (!categoryName.isEmpty()) {
                    inventoryItemVo.setCategoriesName(categoryName.substring(1, categoryName.length()));
                } else {
                    inventoryItemVo.setCategoriesName("");
                }
                List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
                for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                    modifierGroupName = modifierGroupName + "," + itemModifierGroup.getModifierGroup().getName();
                }
                modifierGroupName = modifierGroupName.trim();
                if (!modifierGroupName.isEmpty()) {
                    inventoryItemVo.setModifierGroups(modifierGroupName.substring(1, modifierGroupName.length() - 1));
                } else {
                    inventoryItemVo.setModifierGroups("");
                }
                inventoryItemVo.setId(item.getId());
                inventoryItemVo.setName(item.getName());
               /* if ( item.getItemStatus()!=null && item.getItemStatus() == 0) {
                    inventoryItemVo.setStatus("Active");
                } else {
                    inventoryItemVo.setStatus("InActive");
                }*/
                if (item.getItemStatus()!=null && item.getItemStatus() != IConstant.SOFT_DELETE) {
                if (item.getItemStatus()!=null && item.getItemStatus() == 0) {
                    //inventoryItemVo.setStatus("Active");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                    + " style='color: blue;'>Active</a></div>");
                    
                    
                } else {
                    //inventoryItemVo.setStatus("InActive");
                	inventoryItemVo.setStatus("<div><a href=javascript:void(0) class='nav-toggle' itmId=" + item.getId()
                    + " style='color: blue;'>InActive</a></div>");
                }
                inventoryItemVo.setPrice(item.getPrice());
                inventoryItemVo.setAction("<a href=addLineItem?itemId=" + item.getId()
                                + " class='edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>");
                inventoryItemVos.add(inventoryItemVo);}
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(inventoryItemVos);
    }

	/*public void updateItemStatusById(Item item) {
		
		
		Item result = itemmRepository.findOne(item.getId());
		
		System.out.println(result.getId()+" "+result.getItemStatus());
        result.setItemStatus(item.getItemStatus());
        itemmRepository.save(result);
		
	}*/
    //change method return type and add parameter categoryItemId
    public String updateItemStatusById(Item item, Integer categoryItemId) {
    	/*Item result = itemmRepository.findOne(item.getId());
        result.setItemStatus(item.getItemStatus());
        itemmRepository.save(result);
		*/
    	
    
    	//changes made by manish gupta for update items states and sorting order on 05/05/17
    	Map<String, Object> map= new HashMap();	
		try{
		Item result = itemmRepository.findOne(item.getId());
		CategoryItem categoryItemResult =  categoryItemRepository.findOne(categoryItemId);
		  int mostAvailableOrder=0;
		  if(item.getItemStatus()==IConstant.BOOLEAN_FALSE){
			  System.out.println("inside if item.getItemStatus()-"+item.getItemStatus());
			 Pageable pageable = new PageRequest(1 - 1, 10, Sort.Direction.DESC, "id");
			 Page<Item> allItems = itemmRepository.findByMerchantIdAndItemStatusNot(result.getMerchant().getId(),pageable,IConstant.SOFT_DELETE);
			 int totalItems=(int)allItems.getTotalElements();
			 System.out.println("getTotalItems:-"+totalItems+" result.getMerchant().getId()-"+result.getMerchant().getId()+" categoryItemId-"+categoryItemId);
			 List<CategoryItem> itemss= categoryItemRepository.findByCategoryIdAndSortOrderNotOrderBySortOrderAsc(categoryItemResult.getCategory().getId(), IConstant.BOOLEAN_FALSE);
			 int itemListSize=itemss.size();
			 System.out.println("itemListSize-"+itemListSize);
			 if(itemss!=null && itemss.size()>0){
		        	for(int i=1;i<=totalItems;i++){
		        		if(i<=itemListSize){
		        		CategoryItem categoryItem2=itemss.get(i-1);
		        		System.out.println("categoryItem2.getSortOrder()-"+categoryItem2.getSortOrder());
		        		if(categoryItem2!=null){
		        		if(categoryItem2.getSortOrder()!=i){
		        			mostAvailableOrder=i;
		        			System.out.println("i-"+i+"mostAvailableOrder-"+mostAvailableOrder);
		        			break;
		        		}}}else{
		        			mostAvailableOrder=i;
		        			System.out.println("mostAvailableOrder-"+mostAvailableOrder);
		        			break;
		        		}
		        	}
			 }else{
	        		mostAvailableOrder=1;
	        	}
			 categoryItemResult.setSortOrder(mostAvailableOrder);
			 
		  }else{
			  categoryItemResult.setSortOrder(IConstant.BOOLEAN_FALSE);
	        	mostAvailableOrder=IConstant.BOOLEAN_FALSE;
	        }
		
		System.out.println(result.getId()+" "+result.getItemStatus());
        result.setItemStatus(item.getItemStatus());
        itemmRepository.save(result);
        categoryItemRepository.save(categoryItemResult);
        map.put("result", "success");
        map.put("categoryItemResult", "success");
        map.put("menuOrder", mostAvailableOrder);
	}catch(Exception e){
 	   map.put("result", "failed");
    }
     return  new Gson().toJson(map);
	}

    

	public boolean updateItemSortOrderById(CategoryItem categoryItem,String action) {
		CategoryItem result = categoryItemRepository.findOne(categoryItem.getId());
		System.out.println("result--"+result.getCategory().getId()+" "+result.getSortOrder()+" "+result.getId() );
        if(result!=null){
        int oldOrder=0;
        if(result.getSortOrder()!=null){
        	oldOrder=result.getSortOrder();
        }
     List<CategoryItem> categoryItems= categoryItemRepository.findByCategoryIdAndSortOrderAndIdNot(result.getCategory().getId(),categoryItem.getSortOrder(),result.getId());
      // if(action!=null&&!action.isEmpty()){
    	   //if(action.equals("check")){
       if(categoryItems!=null && categoryItems.size()>0){
    	   result.setSortOrder(categoryItem.getSortOrder());
      	 categoryItemRepository.save(result);
      	for( CategoryItem categoryItem2:categoryItems){
      		categoryItem2.setSortOrder(oldOrder);
      		categoryItemRepository.save(categoryItem2);
      	}
        	return true;
        }else{
        result.setSortOrder(categoryItem.getSortOrder());
        categoryItemRepository.save(result);
        return true;
        }//}else{
        	//oldOrder=result.getSortOrder();
        	
        	//return true;
       // }
      }else{
        	return true;
        }
		
	}

	public boolean updateItemModifierGroupSortOrderById(ItemModifierGroup itemModifierGroup, String action, Integer itemModifierGroupOrder,Integer itemId) {
		ItemModifierGroup result = itemModifierGroupRepository.findOne(itemModifierGroup.getId());
		
		System.out.println(" itemId--"+itemId);
		Item item = itemmRepository.findOne(itemId);
		
		
		//itemmRepository.save(itemId);
        if(result!=null){
        int oldOrder=0;
        if(item!=null){
        	System.out.println("inside item");
        	item.setAllowModifierGroupOrder(itemModifierGroupOrder);
        	itemmRepository.save(item);
        }
        
        
        if(result.getSortOrder()!=null){
        	oldOrder=result.getSortOrder();
        }
       List<ItemModifierGroup> itemModifierGroups= itemModifierGroupRepository.findByItemIdAndSortOrderAndIdNot(result.getItem().getId(),itemModifierGroup.getSortOrder(),result.getId());
       if(action!=null&&!action.isEmpty()){
    	   if(action.equals("check")){
       if(itemModifierGroups!=null && itemModifierGroups.size()>0){
    	   result.setSortOrder(itemModifierGroup.getSortOrder());
      	 itemModifierGroupRepository.save(result);
      
      	 
      	 
      	for( ItemModifierGroup itemModifierGroup2:itemModifierGroups){
      		itemModifierGroup2.setSortOrder(oldOrder);
      		itemModifierGroupRepository.save(itemModifierGroup2);
      	}
      	return true;
        	//return false;
        }else{
        result.setSortOrder(itemModifierGroup.getSortOrder());
        itemModifierGroupRepository.save(result);
        return true;
        }}else{
        	//oldOrder=result.getSortOrder();
        	 result.setSortOrder(itemModifierGroup.getSortOrder());
        	 itemModifierGroupRepository.save(result);
        	for( ItemModifierGroup itemModifierGroup2:itemModifierGroups){
        		itemModifierGroup2.setSortOrder(oldOrder);
        		itemModifierGroupRepository.save(itemModifierGroup2);
        	}
        	return true;
        }
       }else{
        	return true;
        }}else{
        	return true;
        }
	}

		public void updateInventoryItemStatusById(Item item) {
			
			Item result = itemmRepository.findOne(item.getId());
	        result.setItemStatus(item.getItemStatus());
	        itemmRepository.save(result);
			
		}

		/*public List<Item> getAllItemsByMerchantUId(String merchantUId) {
			try {
	            return itemmRepository.findByMerchantAccessToken(merchantUId);
	        } catch (Exception exception) {
	            exception.printStackTrace();
	            return new ArrayList<Item>();
	        }
		}*/
		public List<Item> getAllItemsByMerchantUId(String merchantUId) {
			
			 List<Item> items= itemmRepository.findByMerchantMerchantUid(merchantUId);
			 
			 List<Item> finalItems = new ArrayList<Item>();
             for (Item item : items) {
             	if(item.getItemStatus()!=null && item.getItemStatus()!=IConstant.SOFT_DELETE){
                 Item item2 = new Item();
                 item2.setId(item.getId());
                 item2.setName(item.getName());
                 item2.setPrice(item.getPrice());
                 item2.setTaxes(null);
                 item2.setCategories(null);
                 //item2.setPriceType(item.getPriceType());
                 //item2.setModifiedTime(item.getModifiedTime());
                 //item2.setPosItemId(item.getPosItemId());
                 //item2.setMerchant(item.getMerchant());
                 //item2.setIsDefaultTaxRates(item.getIsDefaultTaxRates());
                 //item2.setUnitName(item.getUnitName());
                 //item2.setItemStatus(item.getItemStatus());
                 //item2.setModifierLimits(item.getModifierLimits());
                 //item2.setAllowModifierLimit(item.getAllowModifierLimit());
                 //item2.setAllowModifierGroupOrder(item.getAllowModifierGroupOrder());
                 //item2.setDescription(item.getDescription());
                 
                 finalItems.add(item2);
             	}
             }
			return finalItems;
		}
		
		

		public List<Item> getAllItemsByVenderUId(String vendorUId) {
			
			List<Item> items= itemmRepository.findByMerchantOwnerVendorUid(vendorUId);
			 List<Item> finalItems = new ArrayList<Item>();
             for (Item item : items) {
             	if(item.getItemStatus()!=null && item.getItemStatus()!=IConstant.SOFT_DELETE){
                 Item item2 = new Item();
                 item2.setId(item.getId());
                 item2.setName(item.getName());
                 item2.setPrice(item.getPrice());
                 item2.setTaxes(null);
                 item2.setCategories(null);
                /* item2.setPriceType(item.getPriceType());
                 item2.setModifiedTime(item.getModifiedTime());
                 item2.setPosItemId(item.getPosItemId());
                 //item2.setMerchant(item.getMerchant());
                 item2.setIsDefaultTaxRates(item.getIsDefaultTaxRates());
                 item2.setUnitName(item.getUnitName());
                 item2.setItemStatus(item.getItemStatus());
                 item2.setModifierLimits(item.getModifierLimits());
                 item2.setAllowModifierLimit(item.getAllowModifierLimit());
                 item2.setAllowModifierGroupOrder(item.getAllowModifierGroupOrder());
                 item2.setDescription(item.getDescription());*/
                
                 finalItems.add(item2);
             	}
             }
			return finalItems;  
		}
	

	
}
