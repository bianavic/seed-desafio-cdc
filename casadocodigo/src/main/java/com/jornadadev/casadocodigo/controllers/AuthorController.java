package com.jornadadev.casadocodigo.controllers;

import com.jornadadev.casadocodigo.models.Author;
import com.jornadadev.casadocodigo.repositories.AuthorRepository;
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

  @Autowired
  private AuthorRepository authorRepository;

  @PostMapping("/authors")
  ResponseEntity<String> newAuthor(@Valid @RequestBody Author author) {
    return ResponseEntity.ok("OK");
  }

}
