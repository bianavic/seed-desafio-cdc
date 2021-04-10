package com.jornadadev.casadocodigo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCategoryResponse {

  @JsonProperty
  private Long id;

  @JsonProperty
  private String categoryName;

  public NewCategoryResponse(Category newCategory) {
    this.id = newCategory.getId();
    this.categoryName = newCategory.getCategoryName();
  }
}
