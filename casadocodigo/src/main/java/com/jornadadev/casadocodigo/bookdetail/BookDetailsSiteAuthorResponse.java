package com.jornadadev.casadocodigo.bookdetail;

import com.jornadadev.casadocodigo.author.Author;

public class BookDetailsSiteAuthorResponse{

  private String authorName;
  private String description;

  public BookDetailsSiteAuthorResponse(Author author) {
    authorName = author.getAuthorName();
    description = author.getDescription();
  }

  public String getAuthorName() {
    return authorName;
  }

  public String getDescription() {
    return description;
  }
}
