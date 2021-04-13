package com.jornadadev.casadocodigo.book;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.category.Category;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
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
  @NotNull @Future
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate dateOfPublic;
  @ManyToOne
  private @NotNull @Valid Category category;
  @ManyToOne
  private @NotNull @Valid Author author;


  @Deprecated
  public Book() {}

  public Book(@NotBlank String bookTitle,
      @NotBlank @Size(max = 500) String bookAbstract,
      @NotBlank String bookSummary,
      @NotNull @Min(20) BigDecimal bookPrice,
      @NotNull @Min(100) Integer bookNumberOfPages,
      @NotBlank String bookIsbn, @NotNull LocalDate dateOfPublic,
      @NotNull @Valid Category category, @NotNull @Valid Author author) {
    this.bookTitle = bookTitle;
    this.bookAbstract = bookAbstract;
    this.bookSummary = bookSummary;
    this.bookPrice = bookPrice;
    this.bookNumberOfPages = bookNumberOfPages;
    this.bookIsbn = bookIsbn;
    this.dateOfPublic = dateOfPublic;
    this.category = category;
    this.author = author;
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
        ", category=" + category +
        ", author=" + author +
        '}';
  }
}
