package com.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.entity.Access;

@Repository("accessValidator")
public class AccessValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Access.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "userName", "用户姓名不能为空");
	}
}
