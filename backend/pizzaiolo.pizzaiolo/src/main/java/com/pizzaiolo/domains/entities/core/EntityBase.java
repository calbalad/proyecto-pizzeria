package com.example.domains.entities.core;

import java.util.Set;

import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class EntityBase<E> {
	@Transient
	public Set<ConstraintViolation<E>> getErrors() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate((E) this);
	}

	@Transient
	public String getErrorsMessage() {
		if (isValid())
			return "";
		StringBuilder sb = new StringBuilder("ERRORES; ");
		getErrors().forEach(item -> sb.append(item.getPropertyPath() + ": " + item.getMessage()));
		return sb.toString().trim();

	}

	public boolean isValid() {
		return getErrors().size() == 0;
	}

	public boolean isInvalid() {
		return !isValid();
	}

}
