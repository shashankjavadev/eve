package com.foodkonnekt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.MerchantLogin;

public interface MerchantLoginRepository extends JpaRepository<MerchantLogin, Integer> {

	@Query("SELECT m FROM MerchantLogin m WHERE m.emailId=:emailId and m.password=:password")
    List<MerchantLogin> findByEmailIdAndPassword(@Param("emailId") final String emailId,
                    @Param("password") final String password);
	
	List<MerchantLogin> findByEmailId(String emailId);
}

