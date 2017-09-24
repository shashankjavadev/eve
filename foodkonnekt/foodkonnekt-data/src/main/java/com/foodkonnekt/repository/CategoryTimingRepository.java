package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.model.CategoryTiming;

public interface CategoryTimingRepository extends JpaRepository<CategoryTiming, Integer>  {
	
	List<CategoryTiming> findByCategoryId(Integer categoryId);
	CategoryTiming findByCategoryIdAndDay(Integer categoryId,String day);
	
	@Modifying
    @Transactional
    @Query("delete from CategoryTiming ct where ct.category.id=?1")
    Integer deleteByCategoryId(Integer id);
}
