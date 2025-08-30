package com.david.florczak.westmarches.validators;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueByPropertyValidator implements ConstraintValidator<UniqueByProperty, List<?>> {

	String fieldName;
	
	@Override
	public void initialize(UniqueByProperty constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
	}
	
	@Override
	public boolean isValid(List<?> value, ConstraintValidatorContext context) {
		boolean ret = true;
		HashSet<Object> seenProperties = new HashSet<>();
		
		for(Object o: value){
			BeanWrapperImpl wrapper = new BeanWrapperImpl(o);
      Object fieldValue = wrapper.getPropertyValue(fieldName);
      if(seenProperties.contains(fieldValue)) {
        ret = false;
      } else {
        seenProperties.add(fieldValue);
      }
		}
		return ret;
	}

}
