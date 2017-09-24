package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.Vouchar;

public interface VoucharRepository extends JpaRepository<Vouchar, Integer> {

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Vouchar>
     */
    List<Vouchar> findByMerchantId(Integer merchantId);

    /**
     * Find by couponCode and merchantId
     * 
     * @param couponCode
     * @param merchantId
     * @return {@link Vouchar}
     */
    Vouchar findByVoucharCodeAndMerchantId(String couponCode, Integer merchantId);
}
