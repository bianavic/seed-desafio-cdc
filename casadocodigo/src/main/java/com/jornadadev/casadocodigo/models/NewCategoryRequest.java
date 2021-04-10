package com.jornadadev.casadocodigo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

  @JsonProperty
  @NotBlank
  private String categoryName;

  public Category toModel() {
    return new Category(categoryName);
  }

  public String getCategoryName() {
    return categoryName;
  }
}
