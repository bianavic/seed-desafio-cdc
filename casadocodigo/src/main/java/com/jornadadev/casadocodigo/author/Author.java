package com.jornadadev.casadocodigo.author;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Author {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private @NotBlank String authorName;
  public @NotBlank @Email String email;
  public  @NotBlank @Size(max = 400) String description;
  private LocalDateTime instantOfCriation = LocalDateTime.now();

  @Deprecated
  public Author(){}

  public Author(@NotBlank String authorName, @NotBlank @Email String email,
      @NotBlank @Size(max = 400) String description) {
    this.authorName = authorName;
    this.email = email;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Author{" +
        "Id=" + Id +
        ", authorName='" + authorName + '\'' +
        ", email='" + email + '\'' +
        ", description='" + description + '\'' +
        ", instantOfCriation=" + instantOfCriation +
        '}';
  }
}
