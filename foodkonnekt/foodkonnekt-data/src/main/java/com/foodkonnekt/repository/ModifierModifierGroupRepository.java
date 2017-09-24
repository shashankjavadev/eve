package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.ModifierModifierGroupDto;
import com.foodkonnekt.model.Modifiers;

public interface ModifierModifierGroupRepository extends JpaRepository<ModifierModifierGroupDto, Integer> {

    /**
     * Find modifiers based on modifierGroupId
     * 
     * @param modifierGroupId
     * @return
     */
    @Query("SELECT md.modifiers FROM ModifierModifierGroupDto md WHERE md.modifierGroup.id=:modifierGroupId")
    List<Modifiers> findByModifierGroupId(@Param("modifierGroupId") final Integer modifierGroupId);

    /**
     * find by modifierId
     * 
     * @param modifierId
     * @return List<ModifierModifierGroupDto>
     */
    List<ModifierModifierGroupDto> findByModifiersId(Integer modifierId);

}
