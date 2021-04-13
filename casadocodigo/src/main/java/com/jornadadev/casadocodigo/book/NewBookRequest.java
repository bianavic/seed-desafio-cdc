package com.jornadadev.casadocodigo.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.validations.UniqueValue;
import java.math.BigDecimal;
import java.time.LocalDate;
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
  @NotBlank
  private String bookSummary;
  @NotNull @Min(20)
  private BigDecimal bookPrice;
  @NotNull @Min(100)
  private int bookNumberOfPages;
  @NotBlank
  @UniqueValue(domainClass = Book.class, fieldName = "bookIsbn", message = "This isbn is already registered")
  private String bookIsbn;
  @Future
  @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
  private LocalDate dateOfPublic;
  @NotNull
  @UniqueValue(domainClass = Category.class, fieldName = "id")
  private Long categoryId;
  @NotNull
  @UniqueValue(domainClass = Author.class, fieldName = "id")
  private Long authorId;

  /**
   *
   * @param dateOfPublic
   * criei este setter porque o json nao estava sendo capaz de
   * deserializar o json com a data no parametro pelo construtor.
   * Talvez exista um jeito melhor
   */

  public void setDateOfPublic(LocalDate dateOfPublic) {
    this.dateOfPublic = dateOfPublic;
  }

  public NewBookRequest(String bookTitle, String bookAbstract, String bookSummary,
      BigDecimal bookPrice, Integer bookNumberOfPages, String bookIsbn,
      Long categoryId, Long authorId) {
    super();
    this.bookTitle = bookTitle;
    this.bookAbstract = bookAbstract;
    this.bookSummary = bookSummary;
    this.bookPrice = bookPrice;
    this.bookNumberOfPages = bookNumberOfPages;
    this.bookIsbn = bookIsbn;
    this.categoryId = categoryId;
    this.authorId = authorId;
  }

  /**
   *
   * @return

  public Book toModel() {
    return new Book(bookTitle, bookAbstract, bookPrice, bookNumberOfPages, bookIsbn, dateOfPublic, categoryId, authorId);
  }
   */
}
