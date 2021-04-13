package com.jornadadev.casadocodigo.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @NotBlank
  private String bookTitle;
  @NotBlank @Size(max = 500)
  private String bookAbstract;
  private String bookSummary;
  @NotNull @Min(20)
  private BigDecimal bookPrice;
  @NotNull @Min(100)
  private Integer bookNumberOfPages;
  @NotBlank
  private String bookIsbn;
  @Future
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate dateOfPublic;
  @NotNull
  private Long categoryId;
  @NotBlank
  private Long authorId;

  @Deprecated
  public Book() {}

  protected Book(@NotBlank String bookTitle,
      @NotBlank @Size(max = 500) String bookAbstract,
      @NotNull @Min(20) BigDecimal bookPrice,
      @NotNull @Min(100) Integer bookNumberOfPages,
      @NotBlank String bookIsbn,
      @Future LocalDate dateOfPublic,
      @NotNull Long categoryId, @NotBlank Long authorId) {
    this.bookTitle = bookTitle;
    this.bookPrice = bookPrice;
    this.bookNumberOfPages = bookNumberOfPages;
    this.bookIsbn = bookIsbn;
    this.dateOfPublic = dateOfPublic;
    this.categoryId = categoryId;
    this.authorId = authorId;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", bookTitle='" + bookTitle + '\'' +
        ", bookAbstract='" + bookAbstract + '\'' +
        ", bookSummary='" + bookSummary + '\'' +
        ", bookPrice=" + bookPrice +
        ", bookNumberOfPages=" + bookNumberOfPages +
        ", bookIsbn='" + bookIsbn + '\'' +
        ", dateOfPublic=" + dateOfPublic +
        ", categoryId=" + categoryId +
        ", authorId=" + authorId +
        '}';
  }
}
