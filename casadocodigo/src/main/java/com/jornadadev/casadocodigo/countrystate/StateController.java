package com.jornadadev.casadocodigo.countrystate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

  @Autowired
  private EntityManager manager;

  @PostMapping(value = "/states")
  @Transactional
  public String newState(@RequestBody @Valid NewStateRequest request) {
    State newState = request.toModel(manager);
    manager.persist(newState);

    return newState.toString();
  }

}
