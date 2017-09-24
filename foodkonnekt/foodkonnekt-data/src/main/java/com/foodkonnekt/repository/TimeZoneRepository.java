package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.TimeZone;

public interface TimeZoneRepository extends JpaRepository<TimeZone, Integer>  {

	TimeZone findByTimeZoneCode(String timeZoneCode);
}
