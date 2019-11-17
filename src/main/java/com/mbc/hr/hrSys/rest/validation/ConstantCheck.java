package com.mbc.hr.hrSys.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ConstantCheckConstraintValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER} )
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstantCheck {

	public String value();
	
	public String message() default "You have no admin rights to perform this operation";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}











