package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PaymentMode;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {
	
	public List<PaymentMode> findByMerchantIdAndPosPaymentModeId(Integer merchantId, String posPaymentModeId);
	
	public PaymentMode findByMerchantIdAndLabel(Integer merchantId, String label);

	public List<PaymentMode> findByMerchantId(Integer merchantId);
	
/**
     * Find by merchantId and allowPaymentMode
     * 
     * @param merchantId
     * @param allowPaymentMode
     * @return List<PaymentMode>
     */
    public List<PaymentMode> findByMerchantIdAndAllowPaymentMode(Integer merchantId, int allowPaymentMode);
	
}
