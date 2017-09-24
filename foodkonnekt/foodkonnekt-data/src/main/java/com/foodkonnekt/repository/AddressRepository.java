package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    /**
     * Find address by merchantId
     * 
     * @param merchantId
     * @return List<Address>
     */
    List<Address> findByMerchantId(Integer merchantId);

    /**
     * Find by customer Id
     * 
     * @param customerId
     * @return Address instance
     */
    List<Address> findByCustomerId(Integer customerId);

}
