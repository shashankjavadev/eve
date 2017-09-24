package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.CategoryItem;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Integer> {

    /**
     * Find by itemId and categoryId
     * 
     * @param itemId
     * @param categoryId
     * @return CategoryItem instance
     */
    CategoryItem findByItemIdAndCategoryId(Integer itemId, Integer categoryId);

    /**
     * find by ItemId
     * 
     * @param id
     * @return CategoryItem instance
     */
    List<CategoryItem> findByItemId(Integer id);
    
    @Query("select count(c) from CategoryItem c where c.category.id = ?1 and c.item.itemStatus!= ?2")
    Long categoryItemCountByCategoryIdAndItemStatus(Integer categoryId,Integer itemStatus);

    /**
     * Find by categoryId
     * 
     * @param categoryId
     * @return List<CategoryItem>
     */
    List<CategoryItem> findByCategoryId(Integer categoryId);
    
    List<CategoryItem> findByCategoryIdAndSortOrderAndIdNot(Integer categoryId,Integer sortOrder,Integer id);
    
    List<CategoryItem> findByCategoryIdOrderBySortOrderAsc(int categoryId);
  //  List<CategoryItem> findByItemIdOrderBySortOrder(Integer id);

	List<CategoryItem> findByCategoryIdAndSortOrderNotOrderBySortOrderAsc(Integer id, int booleanFalse);


	/* @Query(value = "SELECT COUNT(c) from CategoryItem c WHERE c.category.id = :categoryId GROUP BY sortOrder ")
	 Long categoryItemCount(Integer categoryId);*/
	 
	  @Query(value = "SELECT COUNT(c) from CategoryItem c WHERE c.category.id = :categoryId and c.sortOrder <> 0 GROUP BY sortOrder")
	  List<Long> categoryItemCount(@Param("categoryId") Integer categoryId);
}
