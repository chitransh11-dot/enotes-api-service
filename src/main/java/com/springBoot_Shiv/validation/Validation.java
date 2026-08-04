package com.springBoot_Shiv.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.springBoot_Shiv.dto.Category_Dto;
import com.springBoot_Shiv.exception.ValidationException;


@Component
public class Validation {

	public void categoryValidation(Category_Dto category_Dto) {
		
		Map<String, Object> error = new HashMap<>();
		
		if(ObjectUtils.isEmpty(category_Dto)) {
			throw new IllegalArgumentException("Category object / JASON should not be empty  or null");
			
		}
		else {
			 //Validation Name field
			
			if(ObjectUtils.isEmpty(category_Dto.getName())) {
				error.put("name", "name field should not be blank");
			}
			else {
				if(category_Dto.getName().length()<10) {
					error.put("name", "name length should be minimum 10");
				}
				if(category_Dto.getName().length()>100) {
					error.put("name", "name length should be maximum 100");
				}
				if(ObjectUtils.isEmpty(category_Dto.getDescription())) {
					error.put("description", "description field is empty or null");
				}
				if(ObjectUtils.isEmpty(category_Dto.getIsActive())) {
					error.put("isActive", "active is empty or null");
				}
				else {
					if(category_Dto.getIsActive() != Boolean.TRUE   &&  category_Dto.getIsActive() != Boolean.FALSE) {
						error.put("isActive", "invalid value is active field");
					}
				}
				
			} 
			
			if(!error.isEmpty()) {
				throw new ValidationException(error);
			}
		}
	}
}
