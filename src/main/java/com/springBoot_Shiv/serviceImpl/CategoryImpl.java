package com.springBoot_Shiv.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.springBoot_Shiv.dto.CategoryResponse;
import com.springBoot_Shiv.dto.Category_Dto;
import com.springBoot_Shiv.entity.Category;
import com.springBoot_Shiv.exception.ResourceNotFoundException;
import com.springBoot_Shiv.respository.CategoryRepository;
//import com.springBoot_Shiv.service.CategoryService;
import com.springBoot_Shiv.service.CategoryService;
import com.springBoot_Shiv.validation.Validation;



@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private ModelMapper  mapper; 
	
	//public Boolean saveCategory(Category_Dto category_Dto) {
//		//Validation checkin
//		//Validation.categoryValidation(category_Dto);
////CORRECT: Calling it on your @Autowired bean instance
//Validation.categoryValidation(category_Dto);
////
////		Category category = mapper.map(category_Dto, Category.class);
////		if (category.getId() == null || category.getId() == 0) {
////			category.setIsDeleted(false);
////			category.setCreatedBy(1);
////			category.setCreatedOn(new Date());
////		} else {
////			updateCategory(category);
////		}
////		Category saveCategory = categoryRepo.save(category);
////		
////		if (ObjectUtils.isEmpty(saveCategory)) {
////			return false;
////		}
////		return true;
//	}
	



	private void updateCategory(Category category) {
		// TODO Auto-generated method stub
		Optional<Category> categoryId =categoryRepo.findById(category.getId());
		if(categoryId.isPresent()) {
			Category existCategory = categoryId.get();
			category.setCreatedBy(existCategory.getCreatedBy());
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
	}

	public List<Category_Dto> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<Category> categories = categoryRepo.findByIsDeletedFalse();
		
		List<Category_Dto> categoryDtoList = categories.stream().map(cat ->mapper.map(cat,Category_Dto.class)).toList();

		return categoryDtoList;
	}

	public List<CategoryResponse> getActiveResponse() {
		// TODO Auto-generated method stub
		
		List<Category> categories = categoryRepo.findByIsActiveTrue();
		List<CategoryResponse> categoryList = categories.stream().map(cat ->mapper.map(cat, CategoryResponse.class)).toList();
		
		return categoryList;
	}

	@Override
	public Category_Dto getCategoryById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		Category category  = categoryRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found with id ="+ id));
		
		if(!ObjectUtils.isEmpty(category)) {
			if(category.getName() == null) {
				throw new IllegalArgumentException("name is null");
			}
			return mapper.map(category, Category_Dto.class);
		}
		return null;
	}

	@Override
	public Boolean getDelete(Integer id) {
		// TODO Auto-generated method stub
	Optional <Category>	 findByCategory = categoryRepo.findById(id);
	if(findByCategory.isPresent()) {
		Category category = findByCategory.get();
		category.setIsDeleted(true);
		categoryRepo.save(category);
		return true;
	}
		return false;
	}

	@Override
	public Boolean saveCategory(Category_Dto category_Dto) {
		
		validation.categoryValidation(category_Dto);

		Category category = mapper.map(category_Dto, Category.class);

		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		} else {
			updateCategory(category);
		}

		Category saveCategory = categoryRepo.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
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
