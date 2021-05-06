package com.jornadadev.casadocodigo.countrystate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping(value = "/countries")
  @Transactional
  public String newCountry(@RequestBody @Valid NewCountryRequest request) {
    Country newCountry = new Country(request.getCountryName());
    manager.persist(newCountry);
    return newCountry.toString();
  }

}
