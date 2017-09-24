package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryDto;
import com.foodkonnekt.model.CategoryTiming;
import com.foodkonnekt.model.Modifiers;

public interface CategoryService {

    /**
     * Find all category
     * 
     * @return
     */
    public List<Category> findAllCategory(Integer merchantId);

    /**
     * Find category count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    
    public Long categoryCountByMerchantId(Integer merchantId);
    
    public String changeCategoryOrder();

    /**
     * Find by categoryId
     * 
     * @param categoryId
     * @return Category
     */
    public Category findCategoryById(int categoryId);

    /**
     * update category status
     * 
     * @param category
     */
    public String updateCategoryStatusById(Category category);
    
    public String updateCategoryTiming(int categoryId,String days,String startTime,String endTime,Integer allowCategoryTimings);
    
    public boolean updateCategorySortOrderById(Category category,String action);

    /**
     * Find by itemId
     * 
     * @param itemId
     * @return List<Modifiers>
     */
    public List<Modifiers> findByItemId(Integer itemId);

    /**
     * Find categories by merchantId
     * 
     * @param merchantId
     * @return List<CategoryDto>
     */
    public List<CategoryDto> findCategoriesByMerchantId(Integer merchantId);

    public String findCAtegoryInventory(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    public String searchCategoryByTxt(Integer merchantId, String searchTxt);

	public List<Category> getAllCategoriesByMerchantUId(String merchantUId);

	public List<Category> getAllCategoriesByVendorUId(String vendorUId);

	public List<CategoryTiming> getCategoryTiming(int categoryId);

	
}
