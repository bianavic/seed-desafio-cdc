package com.jornadadev.casadocodigo.models;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Entity
@Table(name = "table_author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long author_id;

  @NotBlank(message = "Author name is required")
  private String author_name;

  @NotBlank(message = "Email is required")
  @Email
  private String author_email;

  @NotBlank(message = "You must provide a description")
  @Size(max = 400)
  private String author_description;

  private LocalDateTime createdAt = LocalDateTime.now();

  public Author(Long author_id, String author_name, @Email String author_email,
      @Size(max = 400) String author_description, LocalDateTime author_registeredAt) {
    this.author_id = author_id;
    this.author_name = author_name;
    this.author_email = author_email;
    this.author_description = author_description;
  }
}
