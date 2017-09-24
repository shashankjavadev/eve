package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantLogin;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.SocialMediaLinks;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;

public interface MerchantService {

    /**
     * Find by merchantPosId
     * 
     * @param merchantId
     * @return Merchant instance
     * 
     * 
     */
	
	
	
	public List<TaxRates> findAllTaxRatesByMerchantId(Integer merchantId);
	public SocialMediaLinks saveSocialMediaLinks(SocialMediaLinks socialMediaLinks);
	
	public SocialMediaLinks getSocialMediaLinksByMerchantId(Integer merchantId);
    public Merchant getMerchantByMerchantPsoId(String posMerchantId);
    
    public List<Merchant> findAllMerchants();
    
    public List<Merchant> findAllMerchantsByVendorId(Integer vendorId );

    /**
     * Find address by merchantId
     * 
     * @param merchantId
     * @return
     */
    public List<Address> findAddressByMerchantId(Integer merchantId);

    /**
     * Add merchant logo
     * 
     * @param merchant
     * @return
     */
    public Merchant addMerchantLogo(Merchant merchant);
    
    public MerchantLogin  findByEmailAndPassword(String emailId, String password);
    
    public boolean  findByEmailId(String emailId);
    public MerchantLogin findByMerchantEmailId(String emailId);

    /**
     * Find merchant logo by merchant Id
     * 
     * @param merchantId
     * @return
     */
    public Merchant findByMerchantId(Integer merchantId);

    /**
     * Find opening closing hour by merchantId
     * 
     * @param merchantId
     * @return List<OpeningClosingTime>
     */
    public List<OpeningClosingTime> findOpeningClosingHourByMerchantId(Integer merchantId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return vendorId
     */
    public Integer findVendorIdByMerchantId(Integer merchantId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return Merchant instance
     */
    public Merchant findById(int merchantId);

    /**
     * Find by merchant posId
     * 
     * @param merchantId
     * @return Merchant instance
     */
    public Merchant findbyPosId(String merchantId);
    
    public Merchant findbyStoreId(String storeId);

    /**
     * Find by merchantId
     * 
     * @param intMerchantId
     * @return List<OpeningClosingDay>
     */
    public List<OpeningClosingDay> findOpeningClosingDayByMerchantId(Integer merchantId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<Merchant>
     */
    public List<Merchant> findMerchantById(Integer merchantId);

    /**
     * Update merchant logo
     * 
     * @param merchant
     */
    public Merchant save(Merchant merchant);
    
    public MerchantLogin saveMerchantLogin(MerchantLogin merchantLogin);

    /**
     * Save pickup time
     * 
     * @param pickUpTime
     */
    public void savePickupTime(PickUpTime pickUpTime);
    
    public PickUpTime findPickupTime(Integer merchantId);

    /**
     * Save convenience fee
     * 
     * @param convenienceFee
     */
    public void saveConvenienceFee(ConvenienceFee convenienceFee, Merchant merchant);
    
    public String  getMerchantSubscription(Integer merchantId, String status);

	public String generateLinkActiveCustomerFeedback(Integer id);
	public String addDefaultTaxByMerchantId(Integer merchantId);
	public Vendor findVendorById(Integer intVendorId);

}
