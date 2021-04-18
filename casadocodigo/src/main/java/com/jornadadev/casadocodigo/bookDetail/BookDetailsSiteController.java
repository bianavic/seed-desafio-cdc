package com.jornadadev.casadocodigo.bookDetail;

import com.jornadadev.casadocodigo.book.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 3
@RestController
public class BookDetailsSiteController {

  // para recuperar 1 livro
  @PersistenceContext
  private EntityManager manager;

  @GetMapping(value = "/products/{id}")
  public ResponseEntity<?> detail(@PathVariable("id") Long id) {

    // 1
    // find da JPA retorna nulo caso nao os encontremos
    Book fetchedBook = manager.find(Book.class, id);
    // 1
    // o find retorna nulo
    if (fetchedBook == null) {
      return ResponseEntity.notFound().build();
    }

    // 1
    // criar variavel
    BookDetailsSiteResponse bookDetailsSiteResponse = new BookDetailsSiteResponse(fetchedBook);
    return ResponseEntity.ok(bookDetailsSiteResponse);
  }

}
