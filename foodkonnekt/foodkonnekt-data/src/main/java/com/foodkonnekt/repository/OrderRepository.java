package com.foodkonnekt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.OrderR;

public interface OrderRepository extends JpaRepository<OrderR, Integer> {

    /**
     * Find by customerId and merchantId
     * 
     * @param customerId
     * @param merchantId
     * @return List<OrderR> orderRs
     */
    public List<OrderR> findByCustomerIdAndMerchantId(Integer customerId, Integer merchantId);
    

    /**
     * Find by customerId and orderPosId
     * 
     * @param customerId
     * @param orderPosId
     * @return {@link OrderR}
     */
    public OrderR findByCustomerIdAndOrderPosId(Integer customerId, String orderPosId);

    /**
     * Find by orderPosId
     * 
     * @param orderId
     * @return OrderR instance
     */
    public OrderR findByOrderPosId(String orderId);

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return List<OrderR>
     */
    public List<OrderR> findByMerchantId(int merchantId);

    @Query("SELECT c FROM OrderR c WHERE c.createdOn BETWEEN :startDate AND :endDate and c.merchant.id=:merchantId")
    public List<OrderR> findByStartDateAndEndDateAndMerchantId(@Param("startDate") final Date startDate,
                    @Param("endDate") final Date endDate, @Param("merchantId") final Integer merchantId);

    @Query("SELECT c FROM OrderR c WHERE c.createdOn = :startDate and c.merchant.id=:merchantId")
    public List<OrderR> findByStartDateAndMerchantId(@Param("startDate") final Date startDate,
                    @Param("merchantId") final Integer merchantId);

    @Query("SELECT c FROM OrderR c WHERE c.fulfilled_on > :currentDate and c.merchant.id=:merchantId and c.isDefaults=1")
    public List<OrderR> findByFulFilledDateAndMerchantId(@Param("currentDate") final Date currentDate,
                    @Param("merchantId") final Integer merchantId);

   /* @Query("SELECT c FROM OrderR c WHERE c.createdOn = :startDate and c.merchant.activeCustomerFeedback=:activeCustomerFeedback")
    public List<OrderR> findByStartDateAndMerchantActiveCustomerFeedback(@Param("startDate") final Date startDate,
                    @Param("activeCustomerFeedback") final Integer activeCustomerFeedback);*/

    /*@Query("SELECT c FROM OrderR c WHERE c.createdOn  like ':startDate%' and c.merchant.activeCustomerFeedback=:activeCustomerFeedback")
    public List<OrderR> findByCreatedOnAndMerchantActiveCustomerFeedback(@Param("startDate") final Date startDate,
                    @Param("activeCustomerFeedback") final Integer activeCustomerFeedback);*/
    
   /* @Query("SELECT c FROM OrderR c WHERE c.createdOn  LIKE '%:startDate%' ")
    public List<OrderR> findByCreatedOn(@Param("startDate") final Date startDate);*/
    
   /* @Query("SELECT c FROM OrderR c WHERE c.createdOn  BETWEEN :startDate AND :endDate ")
    public List<OrderR> findByStartDateAndEndDate(@Param("startDate") final Date startDate, @Param("endDate") final Date endDate);*/
    
   /* @Query("SELECT c FROM OrderR c WHERE c.createdOn BETWEEN :startDate AND :endDate and c.merchant.activeCustomerFeedback=:activeCustomerFeedback")
    public List<OrderR> findByStartDateAndEndDateAndMerchantActiveCustomerFeedback(@Param("startDate") final Date startDate,
            @Param("endDate") final Date endDate,
            @Param("activeCustomerFeedback") final Integer activeCustomerFeedback);*/
    @Query("SELECT c FROM OrderR c WHERE c.createdOn BETWEEN :startDate AND :endDate and c.merchant.activeCustomerFeedback=:activeCustomerFeedback GROUP BY customer")
    public List<OrderR> findByStartDateAndEndDateAndMerchantActiveCustomerFeedback(@Param("startDate") final Date startDate,
            @Param("endDate") final Date endDate,
            @Param("activeCustomerFeedback") final Integer activeCustomerFeedback);
    @Query("SELECT c FROM OrderR c WHERE c.createdOn BETWEEN :startDate AND :endDate and c.customer.id=:id")
    public List<OrderR> findByStartDateAndEndDateAndCustomerId(@Param("startDate") final Date startDate,
    															@Param("endDate") final Date endDate,
    															 @Param("id") final Integer id);	
    
    
    /**
     * Find by orderType
     * 
     * @param orderType
     * @return List<OrderR>
     */
    public List<OrderR> findByOrderType(String orderType);

    /**
     * Find by OrderStatus
     * 
     * @param status
     * @return List<OrderR>
     */
    public List<OrderR> findByIsDefaults(int status);

    /**
     * Find by orderStatus and OrderType
     * 
     * @param status
     * @param orderType
     * @return List<OrderR>
     */
    public List<OrderR> findByIsDefaultsAndOrderType(int status, String orderType);

    /**
     * Find by customerId
     * 
     * @param customerId
     * @return List<OrderR>
     */
    public List<OrderR> findByCustomerId(int customerId);

    /**
     * Find by merchantId and order type
     * 
     * @param merchantId
     * @param orderType
     * @return Integer
     */
    public Integer countUsersByMerchantIdAndOrderType(Integer merchantId, String orderType);
    
    
    /**
     * Find by customerId and merchantId
     * 
     * @param customerId
     * @param orderId
     * @return List<OrderR> orderRs
     */
    public OrderR findByCustomerIdAndId(Integer customerId, Integer orderId);

    

    @Query(value = "SELECT sum(orderPrice) FROM order_r WHERE merchant_id = :merchantId", nativeQuery = true)
    public double findSum(@Param("merchantId") Integer merchantId);

    public int countUsersByMerchantId(Integer merchantId);

    @Query(value = "SELECT item_id , COUNT(item_id) as value_occurrence from order_r INNER JOIN order_item on order_r.id = order_item.order_id WHERE order_r.merchant_id = :merchantId GROUP BY item_id ORDER BY value_occurrence desc LIMIT 1", nativeQuery = true)
    public List<Object> findByMerId(@Param("merchantId") Integer merchantId);

    public Page<OrderR> findByMerchantId(Integer merchantId, Pageable pageable);

    @Query("SELECT c FROM OrderR c WHERE  c.fulfilled_on BETWEEN :afterAddingTenMins AND :betWeenWithTime  and c.isFutureOrder=:isFutureOrder and c.merchant.id=:merchantId")
    public List<OrderR> findByFulFilledDateAndIsFutureOrderAndMerchantId(
                    @Param("afterAddingTenMins") final Date afterAddingTenMins,
                    @Param("isFutureOrder") final Integer isFutureOrder, @Param("merchantId") final Integer merchantId,
                    @Param("betWeenWithTime") final Date betWeenWithTime);
    
    @Query("SELECT c FROM OrderR c WHERE  c.fulfilled_on BETWEEN :afterAddingTenMins AND :betWeenWithTime  and c.isFutureOrder=:isFutureOrder and c.merchant.id=:merchantId and c.isDefaults= :isDefault")
    public List<OrderR> findByFulFilledDateAndIsFutureOrderAndMerchantIdAndIsDefaults(
                    @Param("afterAddingTenMins") final Date afterAddingTenMins,
                    @Param("isFutureOrder") final Integer isFutureOrder, @Param("merchantId") final Integer merchantId,
                    @Param("betWeenWithTime") final Date betWeenWithTime, @Param("isDefault") Integer isDefaults);

    /*@Query("SELECT o FROM OrderR o WHERE o.merchant.id=:merchantId and o.customer.firstName like %:searchTxt%")
    public List<OrderR> findByMerchantIdAndCustomerName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt);*/
    
    @Query("SELECT o FROM OrderR o WHERE o.merchant.id=:merchantId and o.customer.firstName like %:searchTxt%")
    public Page<OrderR> findByMerchantIdAndCustomerName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt, Pageable pageable);
    
    
    @Query("SELECT c FROM OrderR c WHERE  c.fulfilled_on > :date  and c.isFutureOrder=:isFutureOrder and c.merchant.id=:merchantId")
    public List<OrderR> findByFulFilledOnAndIsFutureOrderAndMerchantId(
                    @Param("date") final Date date,
                    @Param("isFutureOrder") final Integer isFutureOrder, @Param("merchantId") final Integer merchantId);

    @Query("SELECT c FROM OrderR c WHERE  c.fulfilled_on > :date  and c.isFutureOrder=:isFutureOrder and c.merchant.id=:merchantId and c.isDefaults= :isDefault")
    public List<OrderR> findByFulFilledOnAndIsFutureOrderAndMerchantIdAndIsDefaults(
                    @Param("date") final Date date,
                    @Param("isFutureOrder") final Integer isFutureOrder, @Param("merchantId") final Integer merchantId, @Param("isDefault") Integer isDefaults);
    
}
