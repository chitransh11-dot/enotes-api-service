package com.springBoot_Shiv.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot_Shiv.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

	List<Category> findAll();

	List<Category> findByIsActiveTrue();

//	List<Category> findByIsActiveTrue();

}
