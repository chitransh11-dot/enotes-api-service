package com.springBoot_Shiv.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.springBoot_Shiv.entity.Category;
import com.springBoot_Shiv.respository.CategoryRepository;
import com.springBoot_Shiv.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepo;
	@Override
	public Boolean saveCategory(Category category) {
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		// TODO Auto-generated method stub
	Category saveCategory	= categoryRepo.save(category);
	
	if(ObjectUtils.isEmpty(saveCategory)) {
		return false;
	}
		return true;
	}

	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
	List<Category> categories = categoryRepo.findAll();
	
	return categories;
	}

}
