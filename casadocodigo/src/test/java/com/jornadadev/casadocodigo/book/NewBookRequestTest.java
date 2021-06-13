package com.jornadadev.casadocodigo.book;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.author.NewAuthorRequest;
import com.jornadadev.casadocodigo.category.Category;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.EntityManager;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NewBookRequestTest {

  private NewBookRequest request = new NewBookRequest("bookTitle", "bookAbstract", "bookSummary", BigDecimal.TEN, 100, "bookIsbn",
      new Date(2021, 10, 10), 1l, 1l);

  @Test
  @DisplayName("should create a book when category and author exists")
  void test1() {

    NewBookRequest newBookRequest = new NewBookRequest("bookTitle", "", "", BigDecimal.TEN, 120, "bookIsbn",
        new Date(2021, 12, 10), 1l, 1l);

    Assertions.assertNotNull(newBookRequest, "a instance of this book should be created");
  }

  @Test
  @DisplayName("should not create a book when the author doesnt exists")
  void test2() throws Exception {

    EntityManager manager = Mockito.mock(EntityManager.class);

    Mockito.when(manager.find(Category.class, 1l)).thenReturn(new Category(""));
    Mockito.when(manager.find(Author.class, 1l)).thenReturn(null);

    assertThrows(IllegalStateException.class, () -> request.toModel(manager));
  }

  @Test
  @DisplayName("should not create a book when the category doenst exists")
  void test3() {

    EntityManager manager = Mockito.mock(EntityManager.class);

    Mockito.when(manager.find(Category.class, 1l)).thenReturn(null);
    Mockito.when(manager.find(Author.class, 1l)).thenReturn(new Author("name", "email@email.com", "description"));

    assertThrows(IllegalStateException.class, () -> request.toModel(manager));
  }

}