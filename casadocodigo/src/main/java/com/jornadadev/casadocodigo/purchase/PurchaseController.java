package com.jornadadev.casadocodigo.purchase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PurchaseController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping(value = "/purchases")
  @Transactional
  public String createPurchase(@RequestBody @Valid NewPurchaseRequest request) {

    return "newPurchase";
  }

}
