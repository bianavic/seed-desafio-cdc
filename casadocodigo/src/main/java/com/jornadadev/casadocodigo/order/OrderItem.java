package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.book.Book;
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class OrderItem {

  @ManyToOne @NotNull
  private Book book;
  @Positive
  private int quantity;
  @Positive
  private BigDecimal currentPrice;

  @Deprecated
  OrderItem() {}

  OrderItem(@NotNull Book book, @Positive int quantity) {
    this.book = book;
    this.quantity = quantity;
    this.currentPrice = book.getBookPrice();
  }

  public BigDecimal total() {
    return currentPrice.multiply(BigDecimal.valueOf(quantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null)
      return false;
    if (getClass() != o.getClass())
      return false;

    OrderItem other = (OrderItem) o;
    if (book == null) {
      if (other.book != null)
        return false;
    } else if (!book.equals(other.book))
      return false;

    return true;
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
