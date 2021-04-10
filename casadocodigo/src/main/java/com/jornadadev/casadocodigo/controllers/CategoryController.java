package com.jornadadev.casadocodigo.controllers;

import com.jornadadev.casadocodigo.models.NewCategoryRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

  @PostMapping("/categories")
  public String create(@Valid @RequestBody NewCategoryRequest request) {
    return "Category created";
  }
}
