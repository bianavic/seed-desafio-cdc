package com.jornadadev.casadocodigo.validation;

import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.category.NewCategoryRequest;
import com.jornadadev.casadocodigo.category.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryNameAlreadyRegisteredValidator implements Validator {

  @Autowired
  public CategoryRepository categoryRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return NewCategoryRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    NewCategoryRequest request = (NewCategoryRequest) target;
    Optional<Category> possibleCategory = categoryRepository.findByCategoryName((String) request.getCategoryName());

    if (possibleCategory.isPresent()) {
      errors.rejectValue("categoryName", String.valueOf(HttpStatus.BAD_REQUEST), "This category is already registered " + request.getCategoryName());
    }

  }
}
