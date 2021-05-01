package com.jornadadev.casadocodigo.purchase;

import javax.validation.Valid;

import com.jornadadev.casadocodigo.validation.StateBelongsToCountryValidator;
import com.jornadadev.casadocodigo.validation.VerifyCpfCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class PurchaseController {

  @Autowired
  private StateBelongsToCountryValidator stateBelongsToCountryValidator;

  // validar documentos
  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators( new VerifyCpfCnpjValidator(), stateBelongsToCountryValidator);
  }

  @PostMapping(value = "/purchases")
  public String createPurchase(@RequestBody @Valid NewPurchaseRequest request) {
    return request.toString();
  }

}
