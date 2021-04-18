package com.jornadadev.casadocodigo.author;

import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAuthorRequest {

  @NotBlank
  private String authorName;
  @NotBlank
  @Email
  @UniqueValue(domainClass = Author.class, fieldName = "email")
  public String email;
  @NotBlank
  @Size(max = 400)
  public String description;

  public NewAuthorRequest(String authorName, String email, String description) {
    this.authorName = authorName;
    this.email = email;
    this.description = description;
  }

  public Author toModel() {
    return new Author(this.authorName, this.email, this.description);
  }

  public Object getEmail() {
    return this.email;
  }
}
