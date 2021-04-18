package com.jornadadev.casadocodigo.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.validation.ExistsId;
import com.jornadadev.casadocodigo.validation.UniqueValue;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date publicationDate;
  @NotNull
  @ExistsId(domainClass = Category.class, fieldName = "id")
  private Long categoryId;
  @NotNull
  @ExistsId(domainClass = Author.class, fieldName = "id")
  private Long authorId;

  public NewBookRequest(String bookTitle, String bookAbstract, String bookSummary,
      BigDecimal bookPrice, int bookNumberOfPages, String bookIsbn,
      Date publicationDate, Long categoryId, Long authorId) {
    this.bookTitle = bookTitle;
    this.bookAbstract = bookAbstract;
    this.bookSummary = bookSummary;
    this.bookPrice = bookPrice;
    this.bookNumberOfPages = bookNumberOfPages;
    this.bookIsbn = bookIsbn;
    this.publicationDate = publicationDate;
    this.categoryId = categoryId;
    this.authorId = authorId;
  }

  public Book toModel(EntityManager manager) {
    @NotNull Category category = manager.find(Category.class, categoryId);
    @NotNull Author author = manager.find(Author.class, authorId);

    return new Book(bookTitle, bookAbstract, bookSummary, bookPrice,
        bookNumberOfPages, bookIsbn, publicationDate, category, author);
  }

}
