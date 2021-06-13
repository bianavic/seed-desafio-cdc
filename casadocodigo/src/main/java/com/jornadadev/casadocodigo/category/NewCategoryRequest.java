package com.jornadadev.casadocodigo.category;

import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.validation.constraints.NotBlank;

// Form Value Object, conversa com DDD e conversa com lado Web
// classe que representa os dados da requisicao, DTO
public class NewCategoryRequest {

  @NotBlank
  @UniqueValue(domainClass = Category.class, fieldName = "categoryName")
  private String categoryName;

  public Category toModel() {
    return new Category(categoryName);
  }

  public String getCategoryName() {
    return categoryName;
  }
}
