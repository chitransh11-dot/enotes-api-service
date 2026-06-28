package com.springBoot_Shiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot_Shiv.dto.CategoryResponse;
import com.springBoot_Shiv.dto.Category_Dto;
import com.springBoot_Shiv.service.CategoryService;

@RestController
@RequestMapping("api/vi/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("save-category")
	public ResponseEntity<?> saveCategory(@RequestBody Category_Dto  category_Dto){
		
		Boolean saveCategory = categoryService.saveCategory(category_Dto);
		
		if(saveCategory) {
			return new ResponseEntity<>("saved success", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("category")
	public ResponseEntity<?> getAllCatrgory(){
		List<Category_Dto> allCategory = categoryService.getAllCategory();
		
		if(CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<>(allCategory,HttpStatus.OK);
		}
	}
	
	@GetMapping("active-category")
	public ResponseEntity<?> getActiveCategory(){
		List<CategoryResponse> categoryList = categoryService.getActiveResponse();
		
		if(CollectionUtils.isEmpty(categoryList)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<>(categoryList, HttpStatus.OK);
		}
	}
}
