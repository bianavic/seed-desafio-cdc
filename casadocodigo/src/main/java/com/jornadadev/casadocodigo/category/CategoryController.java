package com.jornadadev.casadocodigo.category;

import com.jornadadev.casadocodigo.validations.CategoryNameAlreadyRegisteredValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
// total da classe = 3
public class CategoryController {

  @PersistenceContext
  private EntityManager manager;

  @Autowired
  private CategoryNameAlreadyRegisteredValidator categoryNameAlreadyRegisteredValidator;

  public void init(WebDataBinder binder) {
    // 1
    binder.addValidators(categoryNameAlreadyRegisteredValidator);
  }

  // *whatever name you want, toModel() can be map()
  @PostMapping("/categories")
  @Transactional
  // 1
  public String create(@Valid @RequestBody NewCategoryRequest request) {
    // total do metodo = 2
    Category newCategory= request.toModel();
    this.manager.persist(newCategory);
    return newCategory.toString();
  }
}
