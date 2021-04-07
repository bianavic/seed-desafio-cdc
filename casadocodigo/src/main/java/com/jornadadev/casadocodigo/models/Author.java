package com.jornadadev.casadocodigo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Author {

  private @NotBlank String authorName;
  public @NotBlank @Email String email;
  public  @NotBlank @Size(max = 400) String description;

  public Author(@NotBlank String authorName, @NotBlank @Email String email,
      @NotBlank @Size(max = 400) String description) {
    this.authorName = authorName;
    this.email = email;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Author{" +
        "authorName='" + authorName + '\'' +
        ", email='" + email + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
