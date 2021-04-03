package com.jornadadev.casadocodigo.controllers;

import com.jornadadev.casadocodigo.models.Author;
import com.jornadadev.casadocodigo.repositories.AuthorRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public void newAuthor(@Valid @RequestBody Author author) {
    manager.persist(author);
  }

}
