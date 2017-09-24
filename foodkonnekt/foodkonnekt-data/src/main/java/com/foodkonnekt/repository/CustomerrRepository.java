package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Customer;

public interface CustomerrRepository extends JpaRepository<Customer, Integer> {

    /**
     * Find by email , password and vendorId
     * 
     * @param emailId
     * @param password
     * @param vendorId
     * @return
     */
    @Query("SELECT c FROM Customer c WHERE c.emailId=:emailId and c.password=:password and c.merchantt.id=:merchantId")
    List<Customer> findByEmailIdAndPasswordAndMerchanttId(@Param("emailId") final String emailId,
                    @Param("password") final String password, @Param("merchantId") final Integer merchantId);
    
    @Query("SELECT c FROM Customer c WHERE c.emailId=:emailId and c.password=:password and c.vendor.id=:vendorId")
    List<Customer> findByEmailIdAndPasswordAndVendorId(@Param("emailId") final String emailId,
                    @Param("password") final String password, @Param("vendorId") final Integer vendorId);
    
    @Query("SELECT c FROM Customer c WHERE c.emailId=:emailId and c.password=:password")
    List<Customer> findByEmailIdAndPassword(@Param("emailId") final String emailId,
                    @Param("password") final String password);
    
    

    /**
     * Find by email Id
     * 
     * @param emailId
     * @return
     */
    List<Customer> findByEmailId(String emailId);

    @Query("SELECT c FROM Customer c WHERE c.merchantt.id=:merchantId and c.emailId=:emailId")
    List<Customer> findByEmailIdAndMerchantId(@Param("emailId") final String emailId,
                    @Param("merchantId") final Integer merchantId);
    
    @Query("SELECT c FROM Customer c WHERE c.vendor.id=:vendorId and c.emailId=:emailId")
    List<Customer> findByEmailIdAndVendorId(@Param("emailId") final String emailId,
                    @Param("vendorId") final Integer vendorId);

    @Query("SELECT c FROM Customer c WHERE c.id=:id and c.emailId=:emailId")
    List<Customer> findByEmailIdAndCustomerId(@Param("emailId") final String emailId, @Param("id") final Integer id);

    Customer findByPassword(String password);

    /**
     * Find by vendorId
     * 
     * @param vendorId
     * @return List<Customer>
     */
    @Query("SELECT c FROM Customer c WHERE c.merchantt.id=:merchantId")
    List<Customer> findByMerchantId(@Param("merchantId") final Integer merchantId);

    @Query("SELECT count(c) FROM Customer c WHERE c.merchantt.id=:merchantId")
    int countByVendorId(@Param("merchantId") final Integer merchantId);

    @Query("SELECT c FROM Customer c WHERE c.merchantt.id=:merchantId ")
    Page<Customer> findByMerchantId(@Param("merchantId") final Integer merchantId, Pageable pageable);
    
    @Query("SELECT c FROM Customer c WHERE c.merchantt.id=:merchantId and c.customerType<>:customerType")
    Page<Customer> findByMerchantIdAndCustomerType(@Param("merchantId") final Integer merchantId,@Param("customerType") final String customerType ,Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.merchantt.id=:merchantId and c.firstName like %:searchTxt% ")
    List<Customer> findByFirstNameLikeIgnoreCaseAndMerchantId(@Param("searchTxt") final String searchTxt,
                    @Param("merchantId") final Integer merchantId);
    
	List<Customer> findByBirthDate(String wishDate);

	List<Customer> findByAnniversaryDate(String wishDate);
}
