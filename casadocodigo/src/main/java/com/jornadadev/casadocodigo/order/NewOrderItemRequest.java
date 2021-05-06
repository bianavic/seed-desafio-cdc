package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.book.Book;
import com.jornadadev.casadocodigo.validation.ExistsId;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewOrderItemRequest {

  @NotNull
  @ExistsId(domainClass = Book.class, fieldName = "id")
  private Long bookId;
  @Positive
  private int quantity;

  public Long getBookId() {
    return bookId;
  }

  public NewOrderItemRequest(Long bookId, int quantity) {
    super();
    this.bookId = bookId;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "NewOrderItemRequest{" +
        "bookId=" + bookId +
        ", quantity=" + quantity +
        '}';
  }

}
