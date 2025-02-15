package com.john.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.john.spring.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

	@Query("SELECT m FROM MenuEntity m JOIN m.drinks d WHERE d.drinkId = :drinkId")
	List<MenuEntity> findAllByDrinkId(@Param("drinkId") Integer drinkId);

}
