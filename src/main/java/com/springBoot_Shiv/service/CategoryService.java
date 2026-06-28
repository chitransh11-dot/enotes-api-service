package com.springBoot_Shiv.service;

import java.util.List;

import com.springBoot_Shiv.dto.CategoryResponse;
import com.springBoot_Shiv.dto.Category_Dto;
//import com.springBoot_Shiv.entity.Category;
//import com.springBoot_Shiv.entity.Category;

public interface CategoryService {
	
 public Boolean saveCategory(Category_Dto category_Dto);
 
// public List<Category> getAllCatgory();

 ///public List<Category> getAllCategory();

 //public List<Category> getAllCategory1();

 public List<Category_Dto> getAllCategory();
 
 //public List<CategoryResponse> getActiveResponse();

 public List<CategoryResponse> getActiveResponse();

}
