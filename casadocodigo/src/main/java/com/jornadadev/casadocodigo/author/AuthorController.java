package com.jornadadev.casadocodigo.author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.jornadadev.casadocodigo.validation.EmailAlreadyRegisteredValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// total da classe = 3
public class AuthorController {

  @PersistenceContext
  private EntityManager manager;
  @Autowired
  private EmailAlreadyRegisteredValidator emailAlreadyRegisteredValidator;

  public void init(WebDataBinder binder) {
    // 1
    binder.addValidators(emailAlreadyRegisteredValidator);
  }

  @PostMapping("/authors")
  @Transactional
  // 1
  // total do metodo = 2
  public String create(@Valid @RequestBody NewAuthorRequest request) {
    // 1
    Author author = request.toModel();
    manager.persist(author);
    return author.toString();
  }

}
