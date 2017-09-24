package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.SocialMediaLinks;

public interface SocialMediaLinksRepository extends JpaRepository<SocialMediaLinks, Integer> {
	
	SocialMediaLinks findByMerchantId(Integer merchantId);

}
