package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.book.Book;
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

@Embeddable
public class OrderItem {

  @ManyToOne
  private Book book;
  @Positive
  private int quantity;
  @Positive
  private BigDecimal currentPrice;

  @Deprecated
  OrderItem() {}

  public OrderItem(Book book, int quantity, BigDecimal currentPrice) {
    this.book = book;
    this.quantity = quantity;
    this.currentPrice = currentPrice;
  }

  public Book getBook() {
    return book;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getCurrentPrice() {
    return currentPrice;
  }

  public BigDecimal total() {
    return currentPrice.multiply(BigDecimal.valueOf(quantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OrderItem orderItem = (OrderItem) o;

    if (quantity != orderItem.quantity) {
      return false;
    }
    if (book != null ? !book.equals(orderItem.book) : orderItem.book != null) {
      return false;
    }
    return currentPrice != null ? currentPrice.equals(orderItem.currentPrice)
        : orderItem.currentPrice == null;
  }

  @Override
  public int hashCode() {
    int result = book != null ? book.hashCode() : 0;
    result = 31 * result + quantity;
    result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
        "book=" + book +
        ", quantity=" + quantity +
        ", currentPrice=" + currentPrice +
        '}';
  }
}
