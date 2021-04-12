package com.jornadadev.casadocodigo.book;

import com.jornadadev.casadocodigo.validations.UniqueValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Constraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class NewBookRequest {

  @NotBlank
  @UniqueValue(domainClass = Book.class, fieldName = "bookTitle", message = "This title is already registered")
  private String bookTitle;
  @NotBlank @Size(max = 500)
  private String bookAbstract;
  private String bookSummary;
  @NotNull @Min(20)
  private BigDecimal bookPrice;
  @NotNull @Min(100)
  private Integer bookNumberOfPages;
  @NotBlank
  @UniqueValue(domainClass = Book.class, fieldName = "bookIsbn", message = "This isbn is already registered")
  private String bookIsbn;
  @Future
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate dateOfPublic;
  @NotNull
  private Long categoryId;
  @NotBlank
  private Long authorId;

}
