package com.jornadadev.casadocodigo.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.book.Book;
import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.purchase.Purchase;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

  @DisplayName("checks that the order total is equal to the one passed as argument")
  @ParameterizedTest
  @CsvSource({
      "10, true",
      "9.99, false",
      "10.01, false"
  })
  void test1(BigDecimal value, boolean expectedResult) throws Exception {
    Author author = new Author("name", "email@email.com", "description");
    Category category = new Category("category");
    Book book = new Book("bookTitle", "bookAbstract", "bookSummary", BigDecimal.TEN,
        120, "111XXX", new Date(2000, 10, 10), category, author);
    Set<OrderItem> items = Set.of(new OrderItem(book, 1));

    Country country = new Country("Brazil");
    Function<Purchase, Order> createOrderFunction = purchase -> {
      return new Order(purchase, items);
    };

    Purchase purchase = new Purchase("email@email", "name", "surname", "111111", "address", "111", country, "999999999", "011111111", createOrderFunction);
    Order order = createOrderFunction.apply(purchase);
    assertEquals(expectedResult, order.totalEqual(value));
  }

}