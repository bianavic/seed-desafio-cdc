package com.jornadadev.casadocodigo.countrystates;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StateController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping(value = "/states")
  @Transactional
  public String newState(@RequestBody @Valid NewStateRequest request) {
    State newState = request.toModel(manager);
    manager.persist(newState);

    return newState.toString();
  }
}
