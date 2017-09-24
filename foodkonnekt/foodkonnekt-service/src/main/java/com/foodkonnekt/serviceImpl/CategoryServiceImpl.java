package com.foodkonnekt.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodkonnekt.clover.vo.CategoryInVo;
import com.foodkonnekt.clover.vo.CeategoryJsonVo;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryDto;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.CategoryTiming;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.CategoryTimingRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository itemModifiersRepository;
    
    @Autowired
    private CategoryTimingRepository categoryTimingRepository;
    
    

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @Autowired
    private ItemmRepository itemmRepository;
    
    @Autowired
    private ModifiersRepository modifiersRepository;

    /**
     * find category by merchantId
     */
    public List<Category> findAllCategory(Integer merchantId) {
        List<Category> categories = categoryRepository.findByMerchantId(merchantId);
        
        List<Item> items=itemmRepository.findByMerchantId(merchantId);
        for(Item item:items){
        	List<ItemModifiers> itemModifiers=itemModifiersRepository.findByItemId(item.getId());
        	List<ItemModifierGroup> itemModifierGroups=itemModifierGroupRepository.findByItemId(item.getId());
        	if((itemModifierGroups!=null && itemModifierGroups.size()>0) ){
        		if((itemModifiers==null || itemModifiers.size()==0)){
        		for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
        			List<Modifiers> modifiers=modifiersRepository.findByModifierGroupId(itemModifierGroup.getModifierGroup().getId());
        			for(Modifiers modifier:modifiers){
        				ItemModifiers itemModifier = new ItemModifiers();
        				itemModifier.setItem(item);
        				itemModifier.setModifierGroup(itemModifierGroup.getModifierGroup());
        				itemModifier.setModifiers(modifier);
        				itemModifier.setModifierStatus(IConstant.BOOLEAN_TRUE);
              		  itemModifiersRepository.save(itemModifier);
        			}
        		}
        	}else{
        		break;
        	}
        	}
        }
        List<Category> finalCategories = new ArrayList<Category>();
        for (Category category : categories) {
        	if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
            Category category2 = new Category();
            category2.setId(category.getId());
            category2.setName(category.getName());
            category2.setItemStatus(category.getItemStatus());
            List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
            if (categoryItems != null) {
                if (!categoryItems.isEmpty()) {
                    category2.setItemCount(categoryItems.size());
                }
            }
            finalCategories.add(category2);
        	}
        }
        return finalCategories;
    }

    /**
     * Find category count by merchantId
     */
    public Long categoryCountByMerchantId(Integer merchantId) {
        return categoryRepository.categoryCountByMerchantId(merchantId);
    }

    /**
     * Find by categoryId
     */
    public Category findCategoryById(int categoryId) {
        return categoryRepository.findOne(categoryId);
    }
   
    public String changeCategoryOrder(){
    	
    	List<Merchant> merchants=merchantRepository.findAll();
    	for(Merchant merchant:merchants){
    		int categoryOrder=1;
    		List<Category> categories = categoryRepository.findByMerchantId(merchant.getId());
    		for(Category category:categories){
    			if(category.getItemStatus()!=IConstant.SOFT_DELETE && category.getItemStatus()!=IConstant.BOOLEAN_TRUE){
    				category.setSortOrder(categoryOrder++);
    			}else{
    				category.setSortOrder(IConstant.BOOLEAN_FALSE);
    			}
    			categoryRepository.save(category);
    		}
    	}
    	return null;
    }
    /**
     * Update category status
     */
    public String updateCategoryStatusById(Category category) {
        Map<String, Object> map= new HashMap();
    	try{
    	   Category result = categoryRepository.findOne(category.getId());
      
        int mostAvailableOrder=0;
        if(category.getItemStatus()==IConstant.BOOLEAN_FALSE){
        	Pageable pageable = new PageRequest(1 - 1, 10, Sort.Direction.DESC, "id");
        	Page<Category> allCats= categoryRepository.findByMerchantIdAndItemStatusNot(result.getMerchant().getId(),pageable,IConstant.SOFT_DELETE);
        	int totalCategories=(int)allCats.getTotalElements();
        	List<Category> categories= categoryRepository.findByMerchantIdAndSortOrderNotOrderBySortOrderAsc(result.getMerchant().getId(), IConstant.BOOLEAN_FALSE);
        	int cateListSize=categories.size();
        	if(categories!=null && categories.size()>0){
        	for(int i=1;i<=totalCategories;i++){
        		if(i<=cateListSize){
        		Category category2=categories.get(i-1);
        		if(category2!=null){
        		if(category2.getSortOrder()!=i){
        			mostAvailableOrder=i;
        			break;
        		}}}else{
        			mostAvailableOrder=i;
        			break;
        		}
        	}
        	}else{
        		mostAvailableOrder=1;
        	}
        	result.setSortOrder(mostAvailableOrder);
        }else{
        	result.setSortOrder(IConstant.BOOLEAN_FALSE);
        	mostAvailableOrder=IConstant.BOOLEAN_FALSE;
        }
        result.setItemStatus(category.getItemStatus());
        categoryRepository.save(result);
        map.put("result", "success");
        map.put("menuOrder", mostAvailableOrder);
       }catch(Exception e){
    	   map.put("result", "failed");
       }
        return new Gson().toJson(map);
    }
    
    public boolean updateCategorySortOrderById(Category category ,String action) {
    	
        Category result = categoryRepository.findOne(category.getId());
        if(result!=null){
        int oldOrder=0;
        if(result.getSortOrder()!=null){
        	oldOrder=result.getSortOrder();
        }
       List<Category> categories= categoryRepository.findByMerchantIdAndSortOrderAndIdNot(result.getMerchant().getId(),category.getSortOrder(),category.getId());
       if(action!=null&&!action.isEmpty()){
    	   if(action.equals("check")){
       if(categories!=null && categories.size()>0){
        	return false;
        }else{
        result.setSortOrder(category.getSortOrder());
        categoryRepository.save(result);
        return true;
        }}else{
        	//oldOrder=result.getSortOrder();
        	 result.setSortOrder(category.getSortOrder());
             categoryRepository.save(result);
        	for(Category category2:categories){
        		category2.setSortOrder(oldOrder);
        		categoryRepository.save(category2);
        	}
        	return true;
        }
       }else{
        	return true;
        }}else{
        	return true;
        }
    }

    /**
     * Find modifiers by itemId
     */
    public List<Modifiers> findByItemId(Integer itemId) {
        List<Modifiers> result = new ArrayList<Modifiers>();
        List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(itemId);
        for (ItemModifierGroup group : itemModifierGroups) {
            List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(group.getModifierGroup()
                            .getId());
            for (Modifiers modifier : modifiers) {
                int count = 0;
                Set<ModifierGroup> groups = modifier.getModifierGroup();
                for (ModifierGroup modifierGroup : groups) {
                    List<ItemModifierGroup> list = itemModifierGroupRepository.findByModifierGroupId(modifierGroup
                                    .getId());
                    if (list != null) {
                        if (!list.isEmpty()) {
                            count = count + list.size();
                        }
                    }
                }
                modifier.setItemCount(count);
                modifier.setModifierGroup(null);
                result.add(modifier);
            }
        }
        return result;
    }

    /**
     * Find categories by merchantId
     */
    public List<CategoryDto> findCategoriesByMerchantId(Integer merchantId) {
        List<Category> categories = categoryRepository.findByMerchantIdOrderBySortOrderAsc(merchantId);
        List<CategoryDto> finalCategories = new ArrayList<CategoryDto>();
        for (Category category : categories) {
        	if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
            List<Item> items = itemmRepository.findByCategoriesId(category.getId());
            List<ItemDto> itemDtos=getItems(items);
            if (items != null && items.size() > 0 && itemDtos!=null && itemDtos.size() >0) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setCategoryName(category.getName());
                categoryDto.setCategoryStatus(category.getItemStatus());
                finalCategories.add(categoryDto);
            }
        	}
        }
        return finalCategories;
    }
    
    
    List<ItemDto> getItems(List<Item> items){
    	List<ItemDto> itemDtos = new ArrayList<ItemDto>();
        try {
            if (items.size() != 0) {
                for (Item item : items) {
                    if (item.getItemStatus()!=null && item.getItemStatus() == 0) {
                        ItemDto itemDto = new ItemDto();
                        itemDto.setId(item.getId());
                        itemDto.setItemName(item.getName());
                        itemDto.setPrice(item.getPrice());
                        itemDto.setItemPosId(item.getPosItemId());
                        itemDto.setAllowModifierLimit(item.getAllowModifierLimit());
                        if(item.getDescription()!=null){
                        	itemDto.setDescription(item.getDescription());
                        }else{
                        	itemDto.setDescription("");
                        }
                        itemDtos.add(itemDto);
                    }
                }
            }
            return itemDtos;
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            return null;
        }
    }

    public String findCAtegoryInventory(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.ASC, "sortOrder");
        Page<Category> categories = categoryRepository.findByMerchantIdAndItemStatusNot(merchantId, pageable,IConstant.SOFT_DELETE);
        List<CategoryInVo> finalCategories = new ArrayList<CategoryInVo>();
        for (Category category : categories.getContent()) {
        	if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
        		List<CategoryTiming> categoryTimings=categoryTimingRepository.findByCategoryId(category.getId());
        		String days="";
        		String startTime="12:00 AM";
        		String endTime="12:00 AM";
        		if(categoryTimings!=null){
        			for(CategoryTiming categoryTiming:categoryTimings){
        				if(!categoryTiming.isHoliday()){
        					days=days+","+categoryTiming.getDay();
        					startTime=categoryTiming.getStartTime();
        					endTime=categoryTiming.getEndTime();
        				}
        			}
        		}
        	CategoryInVo category2 = new CategoryInVo();
        	
        	category2.setTiming("<div><a href=javascript:void(0) id='categoryTiming_"+ category.getId()+"' class='nav-toggle-timing' catid=" + category.getId()+ " days=" + days + " startTime=" + startTime+ " endTime=" + endTime+ " allowCategoryTimings=" + category.getAllowCategoryTimings()+ "  style='color: blue;'>Set Display Timings</a></div>");
            category2.setId(category.getId());
            category2.setName(category.getName());
            int totalRecords=(int)categories.getTotalElements();
            Integer sortOrder=category.getSortOrder();
            int selectedMenuOrder=0;
            if(sortOrder!=null && !sortOrder.equals("")){
            	try{
            		selectedMenuOrder=sortOrder;
            	}catch(Exception e){
            		
            	}
            }
            String menuOrder="";
            if (category.getItemStatus()!=null && category.getItemStatus() == 0) {
             menuOrder="<select  class='category_order' id='menuOrdr_"+category.getId()+"' style='width: 45%' catid=" + category.getId()+ ">";
            
            }else {
            	menuOrder="<select class='category_order' id='menuOrdr_"+category.getId()+"' style='width: 45%' catid=" + category.getId()+ " disabled>";
            	menuOrder=menuOrder+"<option id='0' value='0' selected>0</option>";
            }
            for(int i=1;i<=totalRecords;i++){
            	if(selectedMenuOrder==i){
            		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"' selected>"+i+"</option>";
            	}else{
            		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"'>"+i+"</option>";
            	}
            	
            	}
            menuOrder=menuOrder+"</select>";
            category2.setMenuOrder(menuOrder);

            
            if (category.getItemStatus()!=null && category.getItemStatus() == 0) {
                category2.setAction("<div><a href=javascript:void(0) class='nav-toggle' catid=" + category.getId()
                                + " style='color: blue;'>Active</a></div>");
             }else {
                category2.setAction("<div><a href=javascript:void(0) class='nav-toggle' catid=" + category.getId()
                                + " style='color: blue;'>Inactive</a></div>");
            }
            
           // List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
            Long itemCount=categoryItemRepository.categoryItemCountByCategoryIdAndItemStatus(category.getId(),IConstant.SOFT_DELETE);
           /* if (categoryItems != null) {
                if (!categoryItems.isEmpty()) {*/
            if(itemCount!=null){
                    category2.setItemCount("<a href=findItemsByCategoryId?categoryId=" + category.getId()
                                    + " style='color: blue'>" + itemCount + "</a>");
                } else {
                    category2.setItemCount("<a href=findItemsByCategoryId?categoryId=" + category.getId()
                                    + " style='color: blue'></a>");
                }
            //}
            finalCategories.add(category2);}
        }
        CeategoryJsonVo customerJsonVo = new CeategoryJsonVo();
        customerJsonVo.setiTotalDisplayRecords((int)categories.getTotalElements());
        customerJsonVo.setiTotalRecords((int)categories.getTotalElements());
        customerJsonVo.setAaData(finalCategories);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(customerJsonVo);
    }

    public String searchCategoryByTxt(Integer merchantId, String searchTxt) {
    	Pageable pageable = new PageRequest(1 - 1, 10, Sort.Direction.DESC, "id");
        Page<Category> allCategories = categoryRepository.findByMerchantIdAndItemStatusNot(merchantId, pageable,IConstant.SOFT_DELETE);
        List<Category> categories = categoryRepository.findByMerchantIdAndCategoryName(merchantId, searchTxt);
        List<CategoryInVo> finalCategories = new ArrayList<CategoryInVo>();
        for (Category category : categories) {
            if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
        	CategoryInVo category2 = new CategoryInVo();
            category2.setId(category.getId());
            category2.setName(category.getName());
            int totalRecords=(int)allCategories.getTotalElements();
           // int totalRecords=(int)categories.size();
            Integer sortOrder=category.getSortOrder();
            int selectedMenuOrder=0;
            if(sortOrder!=null && !sortOrder.equals("")){
            	try{
            		selectedMenuOrder=sortOrder;
            	}catch(Exception e){
            		
            	}
            }
            String menuOrder="";
            if (category.getItemStatus()!=null && category.getItemStatus() == 0) {
             menuOrder="<select class='category_order' id='menuOrdr_"+category.getId()+"' style='width: 45%' catid=" + category.getId()+ ">";
            
            }else {
            	menuOrder="<select class='category_order' id='menuOrdr_"+category.getId()+"' style='width: 45%' catid=" + category.getId()+ " disabled>";
            	menuOrder=menuOrder+"<option id='0' value='0' selected>0</option>";
            }
            for(int i=1;i<=totalRecords;i++){
            	if(selectedMenuOrder==i){
            		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"' selected>"+i+"</option>";
            	}else{
            		menuOrder=menuOrder+"<option id='"+i+"' value='"+i+"'>"+i+"</option>";
            	}
            	
            	}
            menuOrder=menuOrder+"</select>";
            category2.setMenuOrder(menuOrder);

            if (category.getItemStatus() == 0) {
                category2.setAction("<div><a href=javascript:void(0) class='nav-toggle' catid=" + category.getId()
                                + " style='color: blue;'>Active</a></div>");
            } else {
                category2.setAction("<div><a href=javascript:void(0) class='nav-toggle' catid=" + category.getId()
                                + " style='color: blue;'>Inactive</a></div>");
            }
           // List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
            Long itemCount=categoryItemRepository.categoryItemCountByCategoryIdAndItemStatus(category.getId(),IConstant.SOFT_DELETE);
            if (itemCount != null) {
                /*if (!categoryItems.isEmpty()) {*/
                    category2.setItemCount("<a href=findItemsByCategoryId?categoryId=" + category.getId()
                                    + " style='color: blue'>" + itemCount + "</a>");
                } else {
                    category2.setItemCount("<a href=findItemsByCategoryId?categoryId=" + category.getId()
                                    + " style='color: blue'></a>");
                }
            /*}*/
            finalCategories.add(category2);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(finalCategories);
    }

	public List<Category> getAllCategoriesByMerchantUId(String merchantUId) {
		List<Category> categories=  categoryRepository.findByMerchantMerchantUid(merchantUId);
             List<Item> items=itemmRepository.findByMerchantMerchantUid(merchantUId);
             for(Item item:items){
             	List<ItemModifiers> itemModifiers=itemModifiersRepository.findByItemId(item.getId());
             	List<ItemModifierGroup> itemModifierGroups=itemModifierGroupRepository.findByItemId(item.getId());
             	if((itemModifierGroups!=null && itemModifierGroups.size()>0) ){
             		if((itemModifiers==null || itemModifiers.size()==0)){
             		for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
             			List<Modifiers> modifiers=modifiersRepository.findByModifierGroupId(itemModifierGroup.getModifierGroup().getId());
             			for(Modifiers modifier:modifiers){
             				ItemModifiers itemModifier = new ItemModifiers();
             				itemModifier.setItem(item);
             				itemModifier.setModifierGroup(itemModifierGroup.getModifierGroup());
             				itemModifier.setModifiers(modifier);
             				itemModifier.setModifierStatus(IConstant.BOOLEAN_TRUE);
                   		  itemModifiersRepository.save(itemModifier);
             			}
             		}
             	}else{
             		break;
             	}
             	}
             }
            // Merchant merchantObj = merchantRepository.findByUniqueId(merchantUId);
             List<Category> finalCategories = new ArrayList<Category>();
             for (Category category : categories) {
             	if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
                 Category category2 = new Category();
                 category2.setId(category.getId());
                 category2.setName(category.getName());
                 category2.setItems(null);
                 category2.setPizzaSizes(null);
                category2.setSortOrder(null);
                // category2.setItemStatus(category.getItemStatus());
                 /*if(merchantObj!=null){
                 category2.setMerchant(merchantObj);
                 }*/
                /* List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
                 if (categoryItems != null) {
                     if (!categoryItems.isEmpty()) {
                         category2.setItemCount(categoryItems.size());
                     }
                 }*/
                 finalCategories.add(category2);
             	}
             }
             return finalCategories; 
             
	}

	public List<Category> getAllCategoriesByVendorUId(String vendorUId) {
		
		List<Category> categories= categoryRepository.findByMerchantOwnerVendorUid(vendorUId);
             List<Item> items=itemmRepository.findByMerchantOwnerVendorUid(vendorUId);
             for(Item item:items){
              	List<ItemModifiers> itemModifiers=itemModifiersRepository.findByItemId(item.getId());
              	List<ItemModifierGroup> itemModifierGroups=itemModifierGroupRepository.findByItemId(item.getId());
              	if((itemModifierGroups!=null && itemModifierGroups.size()>0) ){
              		if((itemModifiers==null || itemModifiers.size()==0)){
              		for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
              			List<Modifiers> modifiers=modifiersRepository.findByModifierGroupId(itemModifierGroup.getModifierGroup().getId());
              			for(Modifiers modifier:modifiers){
              				ItemModifiers itemModifier = new ItemModifiers();
              				itemModifier.setItem(item);
              				itemModifier.setModifierGroup(itemModifierGroup.getModifierGroup());
              				itemModifier.setModifiers(modifier);
              				itemModifier.setModifierStatus(IConstant.BOOLEAN_TRUE);
                    		  itemModifiersRepository.save(itemModifier);
              			}
              		}
              	}else{
              		break;
              	}
              	}
              }
              List<Category> finalCategories = new ArrayList<Category>();
              for (Category category : categories) {
              	if(category.getItemStatus()!=null && category.getItemStatus()!=IConstant.SOFT_DELETE){
                  Category category2 = new Category();
                  category2.setId(category.getId());
                  category2.setName(category.getName());
                  category2.setItems(null);
                  category2.setPizzaSizes(null);
                  category2.setSortOrder(null);
                  //category2.setItemStatus(category.getItemStatus());
                 // category2.setMerchant(category.getMerchant());
                  /* List<CategoryItem> categoryItems = categoryItemRepository.findByCategoryId(category.getId());
                  if (categoryItems != null) {
                      if (!categoryItems.isEmpty()) {
                          category2.setItemCount(categoryItems.size());
                      }
                  }*/
                  finalCategories.add(category2);
              	}
              }
              return finalCategories; 
	}

	public String updateCategoryTiming(int categoryId, String days,
			String startTime, String endTime,Integer allowCategoryTimings) {
		try {
        	String weekDays[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        	List<CategoryTiming> categoryTimings=categoryTimingRepository.findByCategoryId(categoryId);
        	if(categoryTimings!=null && categoryTimings.size()>0){
        	categoryTimingRepository.deleteByCategoryId(categoryId);
        	}
        	//
            Category category = categoryRepository.findOne(categoryId);
            categoryTimings= new ArrayList<CategoryTiming>();
            if(days!=null && !days.isEmpty() && !days.equals("") && allowCategoryTimings!=null && allowCategoryTimings!=0){
            	String timingDays[]=days.split(",");
            	for(String day:weekDays){
            		if(!days.contains(day)){
            			CategoryTiming categoryTiming= new CategoryTiming();
            			categoryTiming.setDay(day);
            			categoryTiming.setStartTime("00:00 AM");
            			categoryTiming.setEndTime("00:00 AM");
            			categoryTiming.setCategory(category);
            			categoryTiming.setHoliday(true);
            		categoryTimings.add(categoryTiming);
            		}else{
            			CategoryTiming categoryTiming= new CategoryTiming();
            			categoryTiming.setDay(day);
            			categoryTiming.setStartTime(startTime);
            			categoryTiming.setEndTime(endTime);
            			categoryTiming.setCategory(category);
            			categoryTiming.setHoliday(false);
            			categoryTimings.add(categoryTiming);
            		}
            	}
            	
            }else{
            	
            	for(String day:weekDays){
            		CategoryTiming categoryTiming= new CategoryTiming();
        			categoryTiming.setDay(day);
        			categoryTiming.setStartTime("00:00 AM");
        			categoryTiming.setEndTime("00:00 AM");
        			categoryTiming.setCategory(category);
        			categoryTiming.setHoliday(false);
        			categoryTimings.add(categoryTiming);
            	}
            }
           // category.setCategoryTimings(categoryTimings);
            category.setAllowCategoryTimings(allowCategoryTimings);
            categoryRepository.save(category);
            categoryTimingRepository.save(categoryTimings);
            
		}catch(Exception exception){
			System.out.println(exception);
		}
		return null;
	}

	public List<CategoryTiming> getCategoryTiming(int categoryId) {
		List<CategoryTiming> categoryTimings=categoryTimingRepository.findByCategoryId(categoryId);
		for(CategoryTiming categoryTiming:categoryTimings){
			categoryTiming.setCategory(null);
		}
		
		return categoryTimings;
	}
}
