package com.mbc.hr.hrSys.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = OptionsConstraintValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface Options {

	public String[] allowedOptions();
	
	public String message() default "Invalid option";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}











