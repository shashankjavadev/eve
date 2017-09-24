package com.foodkonnekt.service;

import java.util.List;
import java.util.Map;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Customer;

public interface CustomerService {

    /**
     * Find by email , password and vendorId
     * 
     * @param emailId
     * @param password
     * @param vendorId
     * @return Customer instance
     */
    Customer findByEmailAndPasswordAndVendorId(String emailId, String password, Integer vendorId,Integer merchantId);
    
    Customer findByEmailAndPasswordAndMerchantId(String emailId, String password, Integer merchantId);
    
    Customer findByEmailAndPassword(String emailId, String password);

    /**
     * Get address by customerId
     * 
     * @param id
     * @return Address instance
     */
    List<Address> getAddressByCustomerId(Integer id);

    boolean findByEmailIdAndMerchantId(String emailId, Integer merchantId);
    
    boolean findByEmailIdAndVendorId(String emailId, Integer vendorId);
    

    /**
     * Get customer by customerId
     * 
     * @param customerId
     * @return
     */
    Customer getCustomerProfile(Integer customerId);
    
    List<Map<String, Object>> checkDuplicatCouponAndRecalculate(Customer customer,List<Map<String , Object>> listOfALLDiscounts);

    /**
     * Update customer profile
     * 
     * @param customer
     * @return
     */
    Customer updateCustomerProfile(Customer customer);

    /**
     * Find by email Id
     * 
     * @param emailId
     * @return
     */
    boolean findByEmail(String emailId);
    
    boolean findAdminByEmail(String emailId);
    
    

    /**
     * Find by emailId
     * 
     * @param emailId
     * @return
     */
    boolean findByEmailId(String emailId);

    /**
     * Find by vendorId
     * 
     * @param vendorId
     * @return List<Customer>
     */
    List<Customer> findByVendorId(Integer vendorId);

    /**
     * Set guest customer password
     * 
     * @param sessionCustomer
     * @return Customer
     */
    Customer setGuestCustomerPassword(Customer sessionCustomer);

    /**
     * Find by emailId and merchantId
     * 
     * @param emailId
     * @param id
     * @return boolean
     */
    boolean findByEmailAndMerchantId(String emailId, Integer id);

    Customer findByEmailAndCustomerId(String emailId, Integer id);

    Customer findCustomerByEmailAndMerchantId(String emailId, Integer id);

    List<Customer> findByEmailIDAndMerchantId(String emailId, Integer merchantId);
    
    List<Customer> findByEmailIDAndVendorId(String emailId, Integer vendorId);

    String findCustomerInventory(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    String searchCustomerByTxt(Integer merchantId, String searchTxt);

    List<Address> findAllCustomerAddress(Integer customerId, Integer merchantId);
    
    Customer findByCustomerId(Integer id);

}
