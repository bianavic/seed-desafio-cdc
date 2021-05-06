package com.jornadadev.casadocodigo.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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

  public NewOrderRequest(BigDecimal total,
      List<NewOrderItemRequest> items) {
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

}
