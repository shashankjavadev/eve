package com.foodkonnekt.service;

import java.sql.Date;
import java.util.List;

import org.json.JSONObject;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.CategoryDto;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroupDto;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.OrderType;

public interface OrderService {

    /**
     * Find by customerId and merchantId
     * 
     * @param id
     * @param parseInt
     * @return
     */
	
	public String getFutureOrders(String merchantId);
	 public String searchOrderByText(Integer merchantId, String searchTxt,Integer pageDisplayLength, Integer pageNumber);

    public OrderType findByMerchantIdAndLabel(Integer merchantId, String orderType);

    public List<OrderR> getAllOrder(Integer customerId, Integer merchantId);

    /**
     * Find line item categories wise
     * 
     * @param categories
     * @return
     */
    /*public List<CategoryDto> findMenuItems(List<CategoryDto> categories);*/

    public List<ItemDto> findCategoryItems(Integer categoryId,Merchant merchant);
    
    public List<CategoryDto> findCategoriesByMerchantId(Merchant merchant);
    public List<CategoryDto> findCategoriesByMerchantIdWithLimit(Integer merchantId,Integer page);

    public List<ModifierGroupDto> getModifierGroup(Integer itemId, Integer allowModifierLimit,Merchant merchant);

    /**
     * Save place order details into database
     * 
     * @param jObject
     * @param finalJson
     * @param customer
     * @param merchant
     * @param discount
     * @param convenienceFee
     * @param deliveryItemPrice
     * @param futureTime
     * @param futurdate
     * @param fututeOrderType
     * @return
     */
    public String saveOrder(JSONObject jObject, String finalJson, Customer customer, Merchant merchant,
                    Double discount, String convenienceFee, String deliveryItemPrice, String avgDeliveryTime,String orderType);

    /**
     * Update order status
     * 
     * @param orderPosId
     * @param result
     * @param customerId
     * @param paymentType
     * @param tax
     * @param name
     * @param email
     * @param merchantLogo
     * @param merchantName
     * @param orderType
     * @param futureTime
     * @param futureDate
     * @param futureOrderType
     */
    public boolean updateOrderStatus(String orderPosId, String result, Integer customerId, String paymentType,
                    String subTotal, String tax, String name, String email, String merchantName, String merchantLogo,
                    String orderType, double tipAmount, String orderPrice, String futureOrderType, String futureDate,
                    String futureTime,Merchant merchant,String listOfALLDiscounts);

    /**
     * Set order status(Accept/decline)
     * 
     * @param orderId
     * @param type
     */
    public boolean setOrderStatus(String orderId, String type, String reason);

    /**
     * Find all orders by merchantId
     * 
     * @param merchantId
     * @return List<OrderR>
     */
    public List<OrderR> findAllOrdersByMerchantId(int merchantId);

    /**
     * Find by orderType
     * 
     * @param orderType
     * @return List<OrderR>
     */
    public List<OrderR> findOrdersByOrderType(String orderType);

    /**
     * Find by OrderStatus
     * 
     * @param orderStatus
     * @return List<OrderR>
     */
    public List<OrderR> findOrdersByStatus(String orderStatus);

    /**
     * Find by orderStatus and OrderType
     * 
     * @param orderStatus
     * @param orderType
     * @return List<OrderR>
     */
    public List<OrderR> findOrdersByStatusAndOrderType(String orderStatus, String orderType);

    /**
     * Find by OrderStatus,OrderType and by Date
     * 
     * @param orderStatus
     * @param orderType
     * @param startDate
     * @param endDate
     * @return List<OrderR>
     */
    public List<OrderR> findOrdersByStatusAndOrderTypeAndDateRange(String orderStatus, String orderType,
                    String startDate, String endDate);

    /**
     * Find by start and end date
     * 
     * @param startDate
     * @param endDate
     * @return List<OrderR>
     */
    public List<OrderR> findByOrderDate(String startDate, String endDate);

    /**
     * Find by orderType and orderDate
     * 
     * @param orderType
     * @param startDate
     * @param endDate
     * @return List<OrderR>
     */
    public List<OrderR> findByOrderTypeAndOrderDate(String orderType, String startDate, String endDate);

    /**
     * Find by orderStatusAndOrderDate
     * 
     * @param orderStatus
     * @param startDate
     * @param endDate
     * @return List<OrderR>
     */
    public List<OrderR> findByOrderStatusAndOrderDate(String orderStatus, String startDate, String endDate);

    /**
     * Find customer orders
     * 
     * @param customerId
     * @return List<OrderR>
     */
    public List<OrderR> findOrderByCustomerId(int customerId);

    /**
     * Find number of delivery order
     * 
     * @param merchantId
     * 
     * @return Integer
     */
    public Integer findNoOfDeliveryOrder(Integer merchantId);

    /**
     * Find number of pick up order
     * 
     * @return Integer
     */
    public Integer findNoOfPickUpOrder(Integer merchantId);

    /**
     * Find average order value
     * 
     * @param merchantId
     * @return double
     */
    public double findAverageOrderValue(Integer merchantId);

    /**
     * Find total Order value
     * 
     * @param merchantId
     * @return double
     */
    public int findtotalOrderValue(Integer merchantId);

    /**
     * Find order frequency
     * 
     * @param merchantId
     * @param vendorId
     * @return double
     */
    public double findOrderFrequency(Integer merchantId, Integer vendorId);

    /**
     * find customer order average
     * 
     * @param merchantId
     * @param vendorId
     * @return double
     */
    public double findCustomerOrderAverage(Integer merchantId, Integer vendorId);

    /**
     * Find total number of customer
     * 
     * @param merchantId
     * @param vendorId
     * @return Integer
     */
    public Integer findTotalCustomer(Integer merchantId, Integer vendorId);

    /**
     * Find trending item
     * 
     * @param merchantId
     * @return String
     */
    public String findTrendingItem(Integer merchantId);

    /**
     * Find average number of item per order
     * 
     * @param merchantId
     * @param vendorId
     * @return double
     */
    public double findAverageNumberOfItemPerOrder(Integer merchantId, Integer vendorId);

    /**
     * Find payment mode
     * 
     * @param id
     * @return List<String>
     */
    public List<String> findPaymentMode(Integer id);

    public void deleteAnOrder(Integer customerId, String orderPOSId);

    public Double findConvenienceFeeAfterTax(String convenienceFee, Integer merchantId);

    public List<Address> findAddessByCustomerId(Integer customerId);

    public String findMerchantTaxs(Integer merchantId);

    public String findConvenienceFeeAfterMultiTax(String convenienceFee, Integer merchantId);

    public List<OrderR> findAllOrdersByFulfilledDate(Date currentDate, int merchantId);

    public OrderR findOrderByOrderID(String id);

    public OrderR findOrderByID(Integer id);

    public void saveOrder(OrderR orderR);

    public void sendMailUser(OrderR orderR, String reason, String time);

    public String findAllOrderFromDataTable(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    public String findOrderDetailsById(Integer orderId);
	public String getAllFutureOrders(String merchantId);
	

   /* public String searchOrderByText(Integer merchantId, String searchTxt);*/
}
