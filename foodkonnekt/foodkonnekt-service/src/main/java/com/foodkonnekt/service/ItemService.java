package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.Merchant;

public interface ItemService {

    /**
     * Save items
     * 
     * @param itemmJson
     */
    public void saveItem(String itemmJson, Merchant merchant);
    
    public boolean updateItemSortOrderById(CategoryItem categoryItem,String action);
    
    public boolean updateItemModifierGroupSortOrderById(ItemModifierGroup itemModifierGroup,String action, Integer itemModifierGroupOrder, Integer itemId);
    
    

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Item>
     */
    public List<Item> findByMerchantId(Integer merchantId);

    /**
     * Find by itemId from database
     * 
     * @param itemId
     * @return List<ItemModifierGroup>
     */
    public List<ItemModifierGroup> findByItemId(Integer itemId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Item>
     */
    public List<Item> findLineItemByMerchantId(int merchantId);

    /**
     * Find by merchantId
     * 
     * @param id
     * @return Long
     */
    public Long itemCountByMerchantId(Integer id);

    /**
     * Find Item by id
     * 
     * @param itemId
     * @return Long
     */
    public Item findItemByItemId(int itemId);
    
    //public void updateItemStatusById(Item item);
    public String updateItemStatusById(Item item, Integer categoryItemId);
    /**
     * Update item
     * 
     * @param item
     */
    public void updateLineItemValue(Item item);

    /**
     * Find by categoryId
     * 
     * @param categoryId
     * @return public List<Item>
     */
    public List<Item> findByCategoryId(Integer categoryId);

    public List<Item> findItemByCategoryId(Integer categoryId);

    public String findinventoryLineItemByMerchantId(int merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    public String filterInventoryByCategoryId(Integer merchantId, int categoryId);

    public String searchItemByTxt(Integer merchantId, String searchTxt);
    
    public void updateInventoryItemStatusById(Item item);

	public List<Item> getAllItemsByMerchantUId(String merchantUId);

	public List<Item> getAllItemsByVenderUId(String vendorUId);

}
