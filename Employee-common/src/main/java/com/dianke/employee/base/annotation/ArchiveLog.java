package com.dianke.employee.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dianke.employee.base.ProcessMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArchiveLog {

	String value();
	
	ProcessMethod method();
	
	int[] parmeters() default {};
}
