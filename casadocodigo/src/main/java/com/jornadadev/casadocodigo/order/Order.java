package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.purchase.Purchase;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.util.Assert;

@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Purchase purchase;

  @ElementCollection
  private Set<OrderItem> items = new HashSet<>();

  public Order(Purchase purchase, Set<OrderItem> items) {
    Assert.isTrue(items.iterator().hasNext(), "every order must have at least one item");
    this.purchase = purchase;
    this.items = items;
  }

  public boolean totalIqual(BigDecimal price) {
    return total().setScale(2).equals(price.setScale(2));
  }

  public BigDecimal total() {
    return items.stream().map(OrderItem::total).reduce(BigDecimal.ZERO,
        (actual, next) -> actual.add(next));
  }

  @Override
  public String toString() {
    return "Order{" +
        "items=" + items +
        '}';
  }
}
