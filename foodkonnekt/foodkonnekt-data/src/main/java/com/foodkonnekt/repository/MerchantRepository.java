package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    public Merchant findByPosMerchantId(String posMerchantId);
    
    public Merchant findByStoreId(String storeId);
    
    public Merchant findById(Integer id);
    
    public List<Merchant> findByOwnerIdAndIsInstallNot(Integer ownerId,Integer isInstall);
    public List<Merchant> findByOwnerId(Integer id);

    /**
     * Find vendor id by merchantId
     * 
     * @param merchantId
     * @return vendorId
     */
    @Query("SELECT m.owner.id FROM Merchant m WHERE m.id=:merchantId")
    public Integer findOwnerByMerchantId(@Param("merchantId") final Integer merchantId);

    /**
     * Find by owner id
     * 
     * @param vendorId
     * @return
     */
    @Query("SELECT m FROM Merchant m WHERE m.owner.id=:vendorId")
    public Merchant findOwnerId(@Param("vendorId") final Integer vendorId);

	
}
