package com.jornadadev.casadocodigo.controllers;

import com.jornadadev.casadocodigo.models.Category;
import com.jornadadev.casadocodigo.models.NewCategoryRequest;
import com.jornadadev.casadocodigo.models.NewCategoryResponse;
import com.jornadadev.casadocodigo.validations.CategoryNameAlreadyRegisteredValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

  @PersistenceContext
  private EntityManager manager;
  @Autowired
  private CategoryNameAlreadyRegisteredValidator categoryNameAlreadyRegisteredValidator;

  public void init(WebDataBinder binder) {
    // 1
    binder.addValidators(categoryNameAlreadyRegisteredValidator);
  }

  @PostMapping("/categories")
  @Transactional
  // 1
  public ResponseEntity<NewCategoryResponse> create(@Valid @RequestBody NewCategoryRequest request) {
    // total do metodo = 2
    Category newCategory= request.toModel();
    this.manager.persist(newCategory);
    return ResponseEntity.ok(new NewCategoryResponse(newCategory));
  }
}
