package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Item;

public interface ItemmRepository extends JpaRepository<Item, Integer> {

    /**
     * find by clover ItemId
     * 
     * @param posItemId
     * @return Item instance
     */
    Item findByPosItemId(String posItemId);

    /**
     * Find by merchatId
     * 
     * @param merchantId
     * @return List<Item>
     */
    List<Item> findByMerchantId(Integer merchantId);

    /**
     * find by ItemId and clover merchantId
     * 
     * @param posItemId
     * @param merchantId
     * @return Item instance
     */
    Item findByPosItemIdAndMerchantId(String posItemId, Integer merchantId);
    
    Item findByIdAndMerchantId(Integer id, Integer merchantId);

    /**
     * find by categoryId
     * 
     * @param categoryId
     * @return List<Item>
     */
    List<Item> findByCategoriesId(int categoryId);
    
   

    /**OrderBySortOrderAsc
     * Find item count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    @Query("select count(item) from Item item where item.merchant.id = ?1")
    Long itemCountByMerchantId(Integer merchantId);

    /**
     * Find Item Name by itemId
     * 
     * @param itemId
     * @return String
     */
    @Query("select item.name from Item item where item.id = ?1")
    String findItemNameByItemId(Integer itemId);

    Page<Item> findByMerchantId(Integer merchantId, Pageable pageable);
    
    Page<Item> findByMerchantIdAndItemStatusNot(Integer merchantId, Pageable pageable,Integer itemStatus);

    @Query("SELECT item FROM Item item WHERE item.merchant.id=:merchantId and item.name like %:searchTxt% ")
    List<Item> findByMerchantIdAndItemName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt);
    
    List<Item> findByMerchantIdAndName(Integer merchantId,String name);


	List<Item> findByMerchantMerchantUid(String merchantUId);

	List<Item> findByMerchantOwnerVendorUid(String vendorUId);
}
