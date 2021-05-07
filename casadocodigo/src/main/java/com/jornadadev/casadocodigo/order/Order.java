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
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.util.Assert;

@Entity
@Table(name = "tb_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne @NotNull @Valid
  private Purchase purchase;

  @ElementCollection @Size(min = 1)
  private Set<OrderItem> items = new HashSet<>();

  public Order(@NotNull @Valid Purchase purchase, @Size(min = 1) Set<OrderItem> items) {
    Assert.isTrue(items.iterator().hasNext(), "every order must have at least one item");
    this.purchase = purchase;
    this.items.addAll(items);
  }

  public boolean totalEqual(@Positive @NotNull BigDecimal price) {
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
