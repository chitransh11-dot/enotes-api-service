package com.springBoot_Shiv.serviceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.springBoot_Shiv.dto.CategoryResponse;
import com.springBoot_Shiv.dto.Category_Dto;
import com.springBoot_Shiv.entity.Category;
import com.springBoot_Shiv.respository.CategoryRepository;
//import com.springBoot_Shiv.service.CategoryService;
import com.springBoot_Shiv.service.CategoryService;



@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepo;
	
	
	
	@Autowired
	private ModelMapper  mapper;
	
	public Boolean saveCategory(Category_Dto category_Dto) {
		// TODO Auto-generated method stub
//	 Category category = new Category();
//	 
//	 category.setName(category_Dto.getName());
//		category.setDescription(category_Dto.getDescription());
//		category.setIsActive(category_Dto.getIsActive());
		
		Category category = mapper.map(category_Dto,Category.class);
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());

		Category saveCategory = categoryRepo.save(category);
		
		if(ObjectUtils.isEmpty(saveCategory)) {
	
			
			return false;
		}
			return true;
	}

	public List<Category_Dto> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<Category> categories = categoryRepo.findAll();
		
		List<Category_Dto> categoryDtoList = categories.stream().map(cat ->mapper.map(cat,Category_Dto.class)).toList();

		return categoryDtoList;
	}

	public List<CategoryResponse> getActiveResponse() {
		// TODO Auto-generated method stub
		
		List<Category> categories = categoryRepo.findByIsActiveTrue();
		List<CategoryResponse> categoryList = categories.stream().map(cat ->mapper.map(cat, CategoryResponse.class)).toList();
		
		return categoryList;
	}

	

	

//	@Autowired
//	CategoryRepository categoryRepo;
//	@Override
//	public Boolean saveCategory(Category_Dto category_Dto) {
//		
//		Category category = new Category();
//		
//		category.setName(category_Dto.getName());
//		category.setDescription(category_Dto.getDescription());
//		category.setIsActive(category_Dto.getIsActive());
//		
//		category.setIsDeleted(false);
//		category.setCreatedBy(1);
//		category.setCreatedOn(new Date());
//		// TODO Auto-generated method stub
//	Category saveCategory	= categoryRepo.save(category);
//	
//	if(ObjectUtils.isEmpty(saveCategory)) {
//		return false;
//	}
//		return true;
//	}

//	public List<Category_Dto> getAllCategory() {
//		// TODO Auto-generated method stub
//	List<Category> categories = categoryRepo.findAll();
//	List<Category_Dto> categoryDtoList = categories.stream().map(cat ->mapper.map(cat,Category_Dto.class)).toList();
//	
//	return categoryDtoList;
//	}

}
