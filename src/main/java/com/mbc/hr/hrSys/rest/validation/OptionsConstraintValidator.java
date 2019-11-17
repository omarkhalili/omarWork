package com.mbc.hr.hrSys.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionsConstraintValidator implements ConstraintValidator<Options, String> {

	private String[] allowedOptions;

	@Override
	public void initialize(Options options) {
		this.allowedOptions = options.allowedOptions();
	}

	@Override
	public boolean isValid(String selectedOption, ConstraintValidatorContext theConstraintValidatorContext) {

		if (selectedOption != null) {

			for (String option : allowedOptions) {
				if (option.equals(selectedOption)) {
					return true;
				}
			}
		} else {
			return true;
		}

		return false;
	}
}
