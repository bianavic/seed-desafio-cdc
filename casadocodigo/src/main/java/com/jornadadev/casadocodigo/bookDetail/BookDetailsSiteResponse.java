package com.jornadadev.casadocodigo.bookDetail;

import com.jornadadev.casadocodigo.book.Book;
import java.math.BigDecimal;
import java.util.Date;

// super border classes
public class BookDetailsSiteResponse {

  private BookDetailsSiteAuthorResponse author;
  private String bookTitle;
  private BigDecimal bookPrice;
  private String bookAbstract;
  private String bookSummary;
  private String bookIsbn;
  private int bookNumberOfPages;
  private Date publicationDate;

  public BookDetailsSiteResponse(Book book) {
    bookTitle = book.getBookTitle();
    author = new BookDetailsSiteAuthorResponse(book.getAuthor());
    bookPrice = book.getBookPrice();
    bookAbstract = book.getBookAbstract();
    bookSummary = book.getBookSummary();
    bookIsbn = book.getBookIsbn();
    bookNumberOfPages = book.getBookNumberOfPages();
    publicationDate = book.getPublicationDate();
  }

  public BookDetailsSiteAuthorResponse getAuthor() {
    return author;
  }

  public String getBookTitle() {
    return bookTitle;
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

  public int getBookNumberOfPages() {
    return bookNumberOfPages;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }
}
