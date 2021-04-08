package com.jornadadev.casadocodigo.controllers;

import com.jornadadev.casadocodigo.models.Author;
import com.jornadadev.casadocodigo.models.NewAuthorRequest;
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
public class AuthorController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping("/authors")
  @Transactional
  public String create(@Valid @RequestBody NewAuthorRequest request) {
    Author author = request.toModel();
    manager.persist(author);
    return author.toString();
  }

}
