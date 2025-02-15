package com.john.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.john.spring.entity.DrinkEntity;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Integer> {

	@Query("SELECT d FROM DrinkEntity d JOIN d.categories c WHERE c.categoryId = :categoryId")
	List<DrinkEntity> findAllByCategoryId(@Param("categoryId") Integer categoryId);

}
