package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.ItemModifiers;

public interface ItemModifiersRepository extends JpaRepository<ItemModifiers, Integer> {
	
	/**
     * Find modifierGroup by item id from database
     * 
     * @param itemId
     * @return List<ItemModifierGroup>
     */
    public List<ItemModifiers> findByItemId(Integer itemId);

    /**
     * find by ModifierGroupId and itemId
     * 
     * @param modifierGroupId
     * @param itemId
     * @return ItemModifierGroup instance
     */
    public ItemModifiers findByModifiersIdAndItemId(Integer modifiersId, Integer itemId);

    /**
     * Find by modifierGroupId
     * 
     * @param modifierGroupId
     * @return List<ItemModifierGroup>
     */
    public List<ItemModifiers> findByModifiersId(Integer modifiersId);
    
    public List<ItemModifiers> findByModifierGroupIdAndItemId(Integer modifierGroupId,Integer itemId);

}
