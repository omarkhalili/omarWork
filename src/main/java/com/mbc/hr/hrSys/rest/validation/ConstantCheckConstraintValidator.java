package com.mbc.hr.hrSys.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstantCheckConstraintValidator implements ConstraintValidator<ConstantCheck, String> {

	private String constantValue;

	@Override
	public void initialize(ConstantCheck constantCheck) {
		this.constantValue = constantCheck.value();
	}

	@Override
	public boolean isValid(String inputValue, ConstraintValidatorContext theConstraintValidatorContext) {
		return constantValue.equals(inputValue);
	}
}