package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PizzaToppingSize;

public interface PizzaToppingSizeRepository extends JpaRepository<PizzaToppingSize, Integer> {

}
