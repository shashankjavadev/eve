package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.TaxRates;

public interface TaxRateRepository extends JpaRepository<TaxRates, Integer> {

    public TaxRates findByMerchantIdAndPosTaxRateId(Integer merchantId, String posTaxRateId);

    public List<TaxRates> findByMerchantId(Integer merchantId);
    
    public List<TaxRates> findByMerchantIdAndNameNot(Integer merchantId,String name);

    public List<TaxRates> findByMerchantIdAndIsDefault(Integer merchantId, int isDefault);
    
    public List<TaxRates> findByMerchantIdAndName(Integer merchantId, String name);
}
