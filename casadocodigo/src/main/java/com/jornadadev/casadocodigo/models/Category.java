package com.jornadadev.casadocodigo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @NotBlank
  private String categoryName;

  @Deprecated
  Category() {
    super();
  }

  public Category(@NotBlank String categoryName) {
    super();
    this.categoryName = categoryName;
  }

  public Long getId() {
    return Id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  @Override
  public String toString() {
    return "Category{" +
        "Id=" + Id +
        ", categoryName='" + categoryName + '\'' +
        '}';
  }
}
