package com.foodkonnekt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.service.DeleteInventoryService;
import com.foodkonnekt.util.IConstant;

@Service
public class DeleteInventoryServiceImpl implements DeleteInventoryService {

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @Autowired
    private ItemmRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemModifierRepository orderItemModifierRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public void deleteInventory(String merchantId, String objectType, String objectId, String actionType) {
        Merchant merchant = merchantRepository.findByPosMerchantId(merchantId);
        if (null != merchant) {
            try {
                Item item = itemRepository.findByPosItemIdAndMerchantId(objectId, merchant.getId());
                if (item != null) {
                	
                	item.setItemStatus(IConstant.SOFT_DELETE);
                	itemRepository.save(item);
                  List<CategoryItem> categoryItems = categoryItemRepository.findByItemId(item.getId());
                    
                  for (CategoryItem categoryItem : categoryItems) {
                	  List<CategoryItem> itemsWithCategory = categoryItemRepository.findByCategoryIdOrderBySortOrderAsc(categoryItem.getCategory().getId());
                	  for(CategoryItem itemWithCategory:itemsWithCategory){
                		  if(itemWithCategory.getSortOrder()!=0 && itemWithCategory.getSortOrder()>categoryItem.getSortOrder()){
                			  itemWithCategory.setSortOrder(itemWithCategory.getSortOrder()-1);  
                		  }
                	  }
                	  categoryItemRepository.save(itemsWithCategory);
                        categoryItem.setCategory(null);
                        categoryItem.setItem(null);
                    }
                    categoryItemRepository.delete(categoryItems);

                    /*  List<ItemModifierGroup> itemModifierGroups = itemModifierGroupRepository.findByItemId(item.getId());
                    for (ItemModifierGroup itemModifierGroup : itemModifierGroups) {
                        itemModifierGroup.setModifierGroup(null);
                        itemModifierGroup.setItem(null);
                    }
                    itemModifierGroupRepository.delete(itemModifierGroups);

                    List<ItemTax> itemTaxs = itemTaxRepository.findByItemId(item.getId());
                    for (ItemTax itemTax : itemTaxs) {
                        itemTax.setItem(null);
                        itemTax.setTaxRates(null);
                    }
                    itemTaxRepository.delete(itemTaxs);

                    List<OrderItem> orderItems = orderItemRepository.findByItemId(item.getId());
                    for (OrderItem orderItem : orderItems) {
                        List<OrderItemModifier> orderItemModifiers = orderItemModifierRepository
                                        .findByOrderItemId(orderItem.getId());
                        for (OrderItemModifier orderItemModifier : orderItemModifiers) {
                            orderItemModifier.setModifiers(null);
                            orderItemModifier.setOrderItem(null);
                        }
                        orderItemModifierRepository.delete(orderItemModifiers);
                        orderItem.setItem(null);
                        orderItem.setOrderItemModifiers(null);
                        orderItem.setOrder(null);
                    }
                    orderItemRepository.delete(orderItems);
                    item.setCategories(null);
                    item.setItemModifierGroups(null);
                    item.setMerchant(null);
                    item.setOrderItems(null);
                    item.setTaxes(null);
                    itemRepository.delete(item);
                    System.out.println("---------Line Item Delete Done---------");*/
                }
            } catch (Exception exception) {
                if (exception != null) {
                   // MailSendUtil.sendExceptionByMail(exception);
                }
                exception.printStackTrace();
            }
        } else {
            System.out.println("==========Inside else part deleteInventory======" + merchant);
        }
    }
}
