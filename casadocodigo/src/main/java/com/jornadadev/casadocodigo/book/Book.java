package com.jornadadev.casadocodigo.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.category.Category;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
  private Date publicationDate;
  @ManyToOne
  private @NotNull @Valid Category category;
  @ManyToOne
  private @NotNull @Valid Author author;

  @Deprecated
  public Book() {}

  // set this format at body post "publicationDate": "2021-06-06"
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  public Date getPublicationDate() {
    return publicationDate;
  }

  public Book(@NotBlank String bookTitle,
      @NotBlank @Size(max = 500) String bookAbstract,
      @NotBlank String bookSummary,
      @NotNull @Min(20) BigDecimal bookPrice,
      @NotNull @Min(100) Integer bookNumberOfPages,
      @NotBlank String bookIsbn, @NotNull Date publicationDate,
      @NotNull @Valid Category category, @NotNull @Valid Author author) {
    this.bookTitle = bookTitle;
    this.bookAbstract = bookAbstract;
    this.bookSummary = bookSummary;
    this.bookPrice = bookPrice;
    this.bookNumberOfPages = bookNumberOfPages;
    this.bookIsbn = bookIsbn;
    this.publicationDate = publicationDate;
    this.category = category;
    this.author = author;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public Author getAuthor() {
    return author;
  }

  public BigDecimal getBookPrice() {
    return bookPrice;
  }

  public String getBookAbstract() {
    return bookAbstract;
  }

  public String getBookSummary() {
    return bookSummary;
  }

  public String getBookIsbn() {
    return bookIsbn;
  }

  public Integer getBookNumberOfPages() {
    return bookNumberOfPages;
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
        ", publicationDate=" + publicationDate +
        ", category=" + category +
        ", author=" + author +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Book book = (Book) o;

    return bookIsbn.equals(book.bookIsbn);
  }

  @Override
  public int hashCode() {
    return bookIsbn.hashCode();
  }
}
