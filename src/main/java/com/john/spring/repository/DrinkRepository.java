package com.john.spring.repository;


import com.john.spring.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

//interface will be connected with jpa repo and will give us access to DB
public interface DrinkRepository extends JpaRepository<DrinkEntity, Integer> {


}
