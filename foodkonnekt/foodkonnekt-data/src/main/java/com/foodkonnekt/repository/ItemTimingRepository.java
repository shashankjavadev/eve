package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.model.ItemTiming;

public interface ItemTimingRepository extends JpaRepository<ItemTiming, Integer> {
    
	  List<ItemTiming> findByItemId(Integer id);
     
     ItemTiming  findByItemIdAndDay(Integer id,String day);
     
     @Modifying
     @Transactional
     @Query("delete from ItemTiming it where it.item.id=?1")
     Integer deleteByItemId(Integer id);
}
