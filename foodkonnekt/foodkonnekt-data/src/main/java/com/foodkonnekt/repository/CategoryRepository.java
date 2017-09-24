package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByMerchantId(Integer merchantId);
    
    List<Category> findByMerchantIdOrderBySortOrderAsc(Integer merchantId);
    
    @Query(value="select c.* from category c left join item_category ic on c.id=ic.category_id left join item i on i.id=ic.item_id where c.merchant_id=:merchantId and c.item_status=0 and  i.item_status=0 GROUP BY c.id ORDER BY c.sortOrder ASC", nativeQuery = true)
    	List<Category> findByMerchantIdOrderBySortOrderAscByQuery(@Param("merchantId") Integer merchantId);
    
    /*List<Category> findByMerchantIdOrderBySortOrderAscByQuery(Integer merchantId);*/
    
 /*   @Query("select c from Category c where c.merchant.id = ?1 Order By c.sortOrder ASC LIMIT ?2,5")
    List<Category> findByMerchantIdOrderBySortOrderAscWithLimit(Integer merchantId,Integer page);*/
    
    List<Category> findByMerchantIdAndSortOrderAndIdNot(Integer merchantId,Integer sortOrder ,Integer categoryId);
    
    List<Category> findByMerchantIdAndSortOrderNotOrderBySortOrderAsc(Integer merchantId,Integer sortOrder);
    
    

    public Category findByMerchantIdAndPosCategoryId(Integer merchantId, String posCategoryId);

    @Query("select count(c) from Category c where c.merchant.id = ?1")
    Long categoryCountByMerchantId(Integer merchantId);

    @Query("SELECT c FROM Category c WHERE c.merchant.id=:merchantId")
    Page<Category> findByMerchantId(@Param("merchantId") Integer merchantId, Pageable pageable);
    
    @Query("SELECT c FROM Category c WHERE c.merchant.id=:merchantId")
    Page<Category> findByMerchantIdAndI(@Param("merchantId") Integer merchantId, Pageable pageable);
    
    Page<Category> findByMerchantIdAndItemStatusNot(Integer merchantId, Pageable pageable,Integer itemStatus);

    @Query("SELECT c FROM Category c WHERE c.merchant.id=:merchantId and c.name like %:searchTxt% ")
    List<Category> findByMerchantIdAndCategoryName(@Param("merchantId") final Integer merchantId,
                    @Param("searchTxt") final String searchTxt);
    
    List<Category> findByMerchantIdAndName(Integer merchantId,String name);


	List<Category> findByMerchantOwnerVendorUid(String vendorUId);

	List<Category> findByMerchantMerchantUid(String merchantUId);

	Page<Category> findByMerchantIdOrderBySortOrderAsc(Integer merchantId,
			Pageable topTen);


}
