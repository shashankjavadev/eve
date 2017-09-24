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
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.service.CreateInventoryService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.InventoryUrlUtil;
import com.foodkonnekt.util.MailSendUtil;

@Service
public class CreateInventoryServiceImpl implements CreateInventoryService {

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
    private ModifiersRepository modifiersRepository;

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository   itemModifiersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    public void createInventoryService(String merchantId, String objectType, String objectId, String actionType) {
        Merchant merchant = merchantRepository.findByPosMerchantId(merchantId);

        if (null != merchant) {
            String jsonOutPut = InventoryUrlUtil.getLineItemWithExpands(merchant, objectId);
            try {
                JSONObject itemJson = new JSONObject(jsonOutPut);
                Item item = createLineItem(itemJson, merchant);
                System.out.println("-----saved Item---");
                Integer modifierGroupId = null;
                if (itemJson.toString().contains("modifierGroups")) {
                    JSONObject modifierJObject = itemJson.getJSONObject("modifierGroups");
                    if(modifierJObject.toString().contains("elements")){
                    JSONArray modifierJSONArray = modifierJObject.getJSONArray("elements");
                    
                    for (Object jObj : modifierJSONArray) {
                        JSONObject modifierJsonObject = (JSONObject) jObj;
                        ModifierGroup modifierGroup = modifierGroupRepository.findByPosModifierGroupIdAndMerchantId(
                                        modifierJsonObject.getString("id"), merchant.getId());
                        if (modifierGroup == null) {
                            modifierGroup = new ModifierGroup();
                            modifierGroupId = null;
                        } else {
                            modifierGroupId = modifierGroup.getId();
                            List<Modifiers> modifiers=modifierModifierGroupRepository.findByModifierGroupId(modifierGroupId);
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
                        }
                        if (modifierGroupId == null) {
                            String modifierJsonOutput = InventoryUrlUtil.getModiferFromClover(merchant,
                                            modifierGroup.getPosModifierGroupId());
                            JSONObject modifierJson = new JSONObject(modifierJsonOutput);
                            JSONObject modifiersObject = modifierJson.getJSONObject("modifiers");
                            if(modifiersObject.toString().contains("elements")){
                            JSONArray modifiersArray = modifiersObject.getJSONArray("elements");
                            for (Object modiObject : modifiersArray) {
                                JSONObject ModifierObject = (JSONObject) modiObject;
                                Modifiers modifier = new Modifiers();

                                if (ModifierObject.toString().contains("name"))
                                    modifier.setName(ModifierObject.getString("name"));

                                if (ModifierObject.toString().contains("id"))
                                    modifier.setPosModifierId(ModifierObject.getString("id"));

                                if (ModifierObject.toString().contains("price"))
                                    modifier.setPrice(ModifierObject.getDouble("price")/100);
                                modifier.setMerchant(merchant);
                                modifiersRepository.save(modifier);
                                if(item!=null){
                                	  
                                		  ItemModifiers itemModifiers = new ItemModifiers();
                                		  itemModifiers.setItem(item);
                                		  itemModifiers.setModifiers(modifier);
                                		  itemModifiers.setModifierStatus(IConstant.BOOLEAN_TRUE);
                                		  itemModifiers.setModifierGroup(modifierGroup);
                                		  itemModifiersRepository.save(itemModifiers);
                                	  
                                  }
                                ModifierModifierGroupDto groupDto = new ModifierModifierGroupDto();
                                groupDto.setModifierGroup(modifierGroup);
                                groupDto.setModifiers(modifier);
                                modifierModifierGroupRepository.save(groupDto);
                            }
                           
                            System.out.println("-----End modifiers and modifierModifierGroups saved----");
                            }
                        }
                    } }
                }
                System.out.println("-----Start categories----");
                if (itemJson.toString().contains("categories")) {
                    JSONObject categoryJsonObject = itemJson.getJSONObject("categories");
                    Integer cetegoryId = null;
                    JSONArray categoriesArray = categoryJsonObject.getJSONArray("elements");
                   
                    for (int i = 0; i < categoriesArray.length(); i++) {
                        try {
                            JSONObject categoryItem = categoriesArray.getJSONObject(i);
                            Category category = categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),
                                            categoryItem.getString("id"));
                            int mostAvailableOrder=0;
                            if (category == null) {
                                category = new Category();
                                category.setItemStatus(0);
                                cetegoryId = null;
//                                category order code starts
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
//                                category order code ends
                            } else {
                                cetegoryId = category.getId();
                            }

                            category.setMerchant(merchant);
                            if (categoryItem.toString().contains("name"))
                                category.setName(categoryItem.getString("name"));

                            if (categoryItem.toString().contains("id"))
                                category.setPosCategoryId(categoryItem.getString("id"));

                           /* if (categoryItem.toString().contains("sortOrder"))
                                category.setSortOrder(categoryItem.getInt("sortOrder"));
                            else
                            	category.setSortOrder(0);*/

                            /*if (cetegoryId == null) {
                                Set<Item> items = new HashSet<Item>();
                                items.add(item);
                                category.setItems(items);
                            }*/
                            categoryRepository.save(category);
                            CategoryItem categoryItem2 = new CategoryItem();
                            categoryItem2.setCategory(category);
                            categoryItem2.setItem(item);
                            categoryItemRepository.save(categoryItem2);
                            System.out.println("saved category " + category);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("-----End categories saved----");
            } catch (Exception exception) {
                if (exception != null) {
                    //MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }

        } else {
            System.out.println("==========Inside else part createInventoryService======" + merchant);
        }

    }

    private Item createLineItem(JSONObject itemJson, Merchant merchant) {
        Item item = itemRepository.findByPosItemIdAndMerchantId(itemJson.getString("id"), merchant.getId());
        if (item == null){
            item = new Item();
            item.setItemStatus(0);
        }
        

        item.setMerchant(merchant);
        if (itemJson.toString().contains("name"))
            item.setName(itemJson.getString("name"));

        if (itemJson.toString().contains("id"))
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

        Set<TaxRates> taxes = new HashSet<TaxRates>();
        if (itemJson.toString().contains("taxRates")) {
            JSONObject taxRateJsonObject = itemJson.getJSONObject("taxRates");
            JSONArray taxRateJsonArray = taxRateJsonObject.getJSONArray("elements");
            for (Object jObj : taxRateJsonArray) {
                JSONObject taxRate = (JSONObject) jObj;
                TaxRates rate1 = taxRateRepository.findByMerchantIdAndPosTaxRateId(merchant.getId(),
                                taxRate.getString("id"));

                TaxRates rate = new TaxRates();
                if (rate != null) {
                    rate.setId(rate1.getId());
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
                taxRateRepository.save(rate);

                taxes.add(rate);
            }
        }
        itemRepository.save(item);
        if (itemJson.toString().contains("taxRates")) {
            for (TaxRates taxRates : taxes) {
                ItemTax itemTax = new ItemTax();
                itemTax.setItem(item);
                itemTax.setTaxRates(taxRates);
                itemTaxRepository.save(itemTax);
            }
        }
        return item;
    }
}
