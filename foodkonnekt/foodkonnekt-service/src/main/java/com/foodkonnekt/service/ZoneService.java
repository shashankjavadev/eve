package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Zone;

public interface ZoneService {

    Zone createZone(Zone zone);

    List<Zone> getAllZone();

    String deleteZone(Integer zoneId);

    Zone updateZoneDetail(Zone zone);

    /**
     * Check address for delivery zone
     * 
     * @param address
     * @return
     */
    public String checkAddressForDeliveryZone(Address address);

    /**
     * Save zone into database
     * 
     * @param zone
     * @param merchant
     */
    public void save(Zone zone, Merchant merchant);

    /**
     * Find zone by merchantId
     * 
     * @param id
     * @return List<Zone>
     */
    public List<Zone> findZoneByMerchantId(Integer id);

    public String findByMerchantIdAndDeliveryZoneName(Integer merchantId, String deliveryZoneName);

    public String findByMerchantIdAndDeliveryZoneNameAndZoneId(Integer merchantId, String deliveryZoneName,
                    Integer zoneId);

    /**
     * Find by id
     * 
     * @param deliveryZoneId
     * @return Zone
     */
    public Zone findById(int deliveryZoneId);

    /**
     * Save delivery address
     * 
     * @param customer
     */
    void saveAddress(Address address);

    /**
     * find by merchantId and status
     * 
     * @param id
     * @return List<Zone>
     */
    List<Zone> findZoneByMerchantIdAndStatus(Integer id);

    Address findAddressById(Integer addressId);

    public double checkMinDeliveryAmount(Double subTotalAmount, Integer merchantId);
}
