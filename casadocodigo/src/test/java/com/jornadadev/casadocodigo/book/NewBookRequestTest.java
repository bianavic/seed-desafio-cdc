package com.jornadadev.casadocodigo.book;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.author.AuthorRepository;
import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NewBookRequestTest {

  private NewBookRequest request = new NewBookRequest("A Montanha Magica", "", "", BigDecimal.TEN, 120, "111XXX",
      new Date(2021, 12, 10), 1l, 1l);

  @Test
  @DisplayName("create book when category and author are registered")
  void test1() throws Exception {

    EntityManager manager = Mockito.mock(EntityManager.class);

    when(manager.find(Category.class, 1l))
        .thenReturn(new Category(""));

    when(manager.find(Author.class, 1l))
        .thenReturn(new Author("", "", ""));

    // assert fraco pq nao checa o estado do livro
    // efeito colateral do teste em cima de condicao em pre condicao
    Assertions.assertNotNull(request.toModel(manager));
  }

  @Test
  @DisplayName("do not create a book if author doesnt exist at database")
  void test2a() throws Exception {

    EntityManager manager = Mockito.mock(EntityManager.class);

    when(manager.find(Category.class, 1l))
        .thenReturn(new Category(""));

    when(manager.find(Author.class, 1l))
        .thenReturn(null);

    assertThrows(IllegalStateException.class, () -> {
      request.toModel(manager);
    });
  }

  @Test
  @DisplayName("do not create a book if category doenst exists")
  void test3() throws Exception {

    EntityManager manager = Mockito.mock(EntityManager.class);

    when(manager.find(Category.class, 1l))
        .thenReturn(null);
    when(manager.find(Author.class, 1l))
        .thenReturn(new Author("", "", ""));

    assertThrows(IllegalStateException.class, () -> {
      request.toModel(manager);
    });
  }

}