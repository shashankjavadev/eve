package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.PickUpTime;

public interface BusinessService {

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<OpeningClosingDay>
     */
    public List<OpeningClosingDay> findBusinessHourByMerchantId(Integer merchantId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return Address
     * 
     * 
     */
    public Address findLocationByMerchantId(Integer merchantId);

    public void updateBusinessHour(String startTime, String endTime, String selectedDay);

    public ConvenienceFee findConvenienceFeeByMerchantId(Integer merchantId);

    public PickUpTime findPickUpTimeByMerchantId(Integer merchantId);

    public PaymentMode findByMerchantIdAndLabel(Integer merchantId, String label);

    public void savePaymentMode(PaymentMode mode);
    
    public void saveDefaultBusinessHours(Merchant merchant);
    
    public void saveDefaultPaymentMode(Merchant merchant);

    /**
     * Find opening hours for future order
     * 
     * @param futureDate
     * @param merchantId
     * @param orderType
     * @return String
     */
    public List<String> findFutureOrderOpeningHours(String futureDate, Integer merchantId, String orderType);
    
    public List<String> findFutureDates(Merchant merchant);
    
    
} 
