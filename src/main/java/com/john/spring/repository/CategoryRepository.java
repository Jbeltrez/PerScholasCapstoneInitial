package com.john.spring.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.john.spring.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

	@Query("SELECT c FROM CategoryEntity c JOIN c.drinks d WHERE d.drinkId = :drinkId")
	List<CategoryEntity> findAllByDrinkId(@Param("drinkId") Integer drinkId);

	Optional<CategoryEntity> findByName(String name);

}
