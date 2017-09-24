package com.foodkonnekt.service;

import java.util.List;
import java.util.Map;

import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.Vouchar;

public interface VoucharService {

    /**
     * Save vouchar
     * 
     * @param vouchar
     */
    void save(Vouchar vouchar);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Vouchar>
     */
    List<Vouchar> findByMerchantId(Integer merchantId);
    
    public Vouchar findById(int id);

    /**
     * Check coupon validity
     * 
     * @param couponCode
     * @param merchantId
     * @return Double
     */
    //String checkCouponVaidity(String kouponJson,String cartJson,String voucherCode, Integer merchantId);
    Map<String, Object> checkCouponVaidity(String kouponJson,String cartJson,String voucherCode, Integer merchantId,String kouponCount,Map<String,Object> appliedDiscounts,double orderLeveldiscount,Customer customer);
    
     /**
     * Find opening closing hours by merchantId
     * 
     * @param id
     * @return List<OpeningClosingTime>
     */
    public List<OpeningClosingTime> findOpeningClosingHoursByMerchantId(Integer id,String timeZoneCode);
    
    
    public String findByMerchantIdAndCouponCode(Integer merchantId,String couponCode);
    
    public String findByMerchantIdAndCouponCodeAndCouponId(Integer merchantId,String couponCode,Integer couponId);
    
    

}
