package com.jornadadev.casadocodigo.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

// Form Value Object, conversa com DDD e conversa com lado Web
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
