package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Modifiers;

public interface ModifiersRepository extends JpaRepository<Modifiers, Integer> {

    /**
     * Find modifiers by modifierGroupId
     * 
     * @param modifierGroupId
     * @return List<Modifiers>
     */
    public List<Modifiers> findByModifierGroupId(Integer modifierGroupId);

    /**
     * Find by PosModifierIdAndMerchantId
     * 
     * @param posModifierId
     * @param merchantId
     * @return Modifiers
     */
    public Modifiers findByPosModifierIdAndMerchantId(String posModifierId, Integer merchantId);
    
    public Modifiers findByIdAndMerchantId(Integer id, Integer merchantId);

    /**
     * Find by PosModifierId
     * 
     * @param id
     * @return Modifiers
     */
    public Modifiers findByPosModifierId(String posModifierId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Modifiers>
     */
    public List<Modifiers> findByMerchantId(int merchantId);

    /**
     * Find modifier count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    @Query("select count(m) from Modifiers m where m.merchant.id = ?1")
    public Long modifierCountByMerchantId(Integer merchantId);
     
    
     public Page<Modifiers> findByMerchantId(Integer merchantId, Pageable pageable);
     
     
     

    @Query("SELECT m FROM Modifiers m WHERE m.merchant.id=:merchantId and m.name like %:searchTxt% ")
    public List<Modifiers> findByMerchantIdAndModifierName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt);
    
    public List<Modifiers> findByMerchantIdAndName(Integer merchantId,String name);

}
