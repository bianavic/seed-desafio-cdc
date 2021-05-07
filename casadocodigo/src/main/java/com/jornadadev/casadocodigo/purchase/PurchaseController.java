package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.validation.StateBelongsToCountryValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

  @Autowired
  private StateBelongsToCountryValidator stateBelongsToCountryValidator;
  @PersistenceContext
  private EntityManager manager;

  @InitBinder
  void init(WebDataBinder binder) {
    binder.addValidators(stateBelongsToCountryValidator);
  }

  @PostMapping(value = "/purchases")
  @Transactional
  public String createPurchase(@RequestBody @Valid NewPurchaseRequest request) {

    Purchase newPurchase = request.toModel(manager);
    manager.persist(newPurchase);

    return newPurchase.toString();
  }

}
