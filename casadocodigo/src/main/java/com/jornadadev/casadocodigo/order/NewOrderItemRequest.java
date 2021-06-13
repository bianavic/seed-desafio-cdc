package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.book.Book;
import com.jornadadev.casadocodigo.validation.ExistsId;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// classe que representa os dados da requisicao, DTO
public class NewOrderItemRequest {

  @NotNull
  @ExistsId(domainClass = Book.class, fieldName = "id")
  private Long bookId;
  @Positive
  private int quantity;

  public Long getBookId() {
    return bookId;
  }

  public NewOrderItemRequest(@NotNull Long bookId, @Positive int quantity) {
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

  public OrderItem toModel(EntityManager manager) {
    @NotNull Book book = manager.find(Book.class, bookId);
    return new OrderItem(book, quantity);
  }
}