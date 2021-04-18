package com.jornadadev.casadocodigo.book;

public class BookResponse {

  private Long id;
  private  String bookTitle;

  public BookResponse(Long id, String bookTitle) {
    this.id = id;
    this.bookTitle = bookTitle;
  }

  public Long getId() {
    return id;
  }

  public String getBookTitle() {
    return bookTitle;
  }
}
