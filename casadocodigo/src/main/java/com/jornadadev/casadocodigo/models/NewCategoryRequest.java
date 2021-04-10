package com.jornadadev.casadocodigo.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @NotBlank private String categoryName;

  public NewCategoryRequest(Long id, String categoryName) {
    this.id = id;
    this.categoryName = categoryName;
  }

  public @NotBlank String getCategoryName() {
    return categoryName;
  }

  @Override
  public String toString() {
    return "NewCategoryRequest{" +
        "id=" + id +
        ", categoryName='" + categoryName + '\'' +
        '}';
  }
}
