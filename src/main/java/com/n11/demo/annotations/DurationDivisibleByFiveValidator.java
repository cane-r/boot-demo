package com.n11.demo.annotations;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DurationDivisibleByFiveValidator implements ConstraintValidator<DivisibleByFive, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value.intValue()%5==0;
			
		
	}

	

	
}
