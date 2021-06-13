package com.jornadadev.casadocodigo.order;

import com.jornadadev.casadocodigo.purchase.Purchase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.util.Assert;

// classe que representa os dados da requisicao, DTO
public class NewOrderRequest {

  @Positive
  @NotNull
  private BigDecimal total;
  @Size(min = 1)
  @Valid
  private List<NewOrderItemRequest> items = new ArrayList<>();

  public List<NewOrderItemRequest> getItems() {
    return items;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public NewOrderRequest(BigDecimal total,
      List<NewOrderItemRequest> items) {
    super();
    this.total = total;
    this.items = items;
  }

  @Override
  public String toString() {
    return "NewItemRequest{" +
        "total=" + total +
        ", items=" + items +
        '}';
  }

  public Function<Purchase, Order> toModel(EntityManager manager) {

    Set<OrderItem> totalItemsPrice = items.stream().map(items -> items.toModel(manager)).collect(Collectors.toSet());

    return purchase -> {
      Order order = new Order(purchase, totalItemsPrice);
      Assert.isTrue(order.totalEqual(total), "Hey, this total("+total+") sent does not correspond to real total("+order.total()+"). Items = "+totalItemsPrice);
      return order;
    };
  }

}
