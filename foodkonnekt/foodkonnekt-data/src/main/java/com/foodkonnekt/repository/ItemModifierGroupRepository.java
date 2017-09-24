package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.ItemModifierGroup;

public interface ItemModifierGroupRepository extends JpaRepository<ItemModifierGroup, Integer> {

    /**
     * Find modifierGroup by item id from database
     * 
     * @param itemId
     * @return List<ItemModifierGroup>
     */
    public List<ItemModifierGroup> findByItemId(Integer itemId);
    
    public List<ItemModifierGroup> findByItemIdOrderBySortOrderAsc(Integer itemId);
    

    /**
     * find by ModifierGroupId and itemId
     * 
     * @param modifierGroupId
     * @param itemId
     * @return ItemModifierGroup instance
     */
    public ItemModifierGroup findByModifierGroupIdAndItemId(Integer modifierGroupId, Integer itemId);
    
    public List<ItemModifierGroup> findByItemIdAndSortOrderAndIdNot(Integer itemId,Integer sortOrder,Integer id);

    /**
     * Find by modifierGroupId
     * 
     * @param modifierGroupId
     * @return List<ItemModifierGroup>
     */
    public List<ItemModifierGroup> findByModifierGroupId(Integer modifierGroupId);
    
    @Query(value = "SELECT COUNT(c) from ItemModifierGroup c WHERE c.item.id = :itemId  GROUP BY sortOrder")
	  List<Long> categoryItemModifierCount(@Param("itemId") Integer itemId);

}
