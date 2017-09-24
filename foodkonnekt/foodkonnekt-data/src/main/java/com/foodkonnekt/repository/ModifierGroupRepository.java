package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.ModifierGroup;

public interface ModifierGroupRepository extends JpaRepository<ModifierGroup, Integer> {

    /**
     * Find by merchantId and podofierGroupsId
     * 
     * @param posModifierGroupId
     * @param merchantId
     * @return ModifierGroup
     */
    public ModifierGroup findByPosModifierGroupIdAndMerchantId(String posModifierGroupId, Integer merchantId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<ModifierGroup>
     */
    public List<ModifierGroup> findByMerchantId(int merchantId);

    /**
     * find modiferGroup count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    @Query("select count(mg) from ModifierGroup mg where mg.merchant.id = ?1")
    public Long modifierGroupCountByMerchantId(Integer merchantId);

    public Page<ModifierGroup> findByMerchantId(Integer merchantId, Pageable pageable);
    
    public Page<ModifierGroup> findByMerchantIdAndShowByDefaultNot(Integer merchantId, Pageable pageable,Integer showByDefault);

    @Query("SELECT m FROM ModifierGroup m WHERE m.merchant.id=:merchantId and m.name like %:searchTxt% ")
    public List<ModifierGroup> findByMerchantIdAndModifierGroupName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt);
    
    public List<ModifierGroup> findByMerchantIdAndName(Integer merchantId, String name);
}
