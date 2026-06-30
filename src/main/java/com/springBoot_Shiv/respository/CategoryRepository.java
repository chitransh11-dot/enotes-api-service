package com.springBoot_Shiv.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot_Shiv.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

	List<Category> findAll();

	List<Category> findByIsActiveTrue();

	Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	List<Category> findByIsDeletedFalse();
	
	

//	List<Category> findByIsActiveTrue();

}
