package com.jornadadev.casadocodigo.purchase;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class PurchaseController {

  @PostMapping(value = "/purchases")
  public String createPurchase(@RequestBody @Valid NewPurchaseRequest request) {
    return request.toString();
  }

}
