package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    /**
     * Find by email
     * 
     * @param email
     * @return Vendor instance
     */
    public Vendor findByEmail(String email);

}
