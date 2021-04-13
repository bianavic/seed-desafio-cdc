package com.jornadadev.casadocodigo.book;

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
public class BookController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping(value = "books")
  @Transactional
  // 1
  public String create(@Valid @RequestBody NewBookRequest request) {
    // 1
    Book newBook = request.toModel(manager);
    manager.persist(newBook);
    return newBook.toString();
  }

}
