package com.jornadadev.casadocodigo.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.book.Book;
import com.jornadadev.casadocodigo.category.Category;
import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;
import com.jornadadev.casadocodigo.coupon.Coupon;
import com.jornadadev.casadocodigo.coupon.CouponRepository;
import com.jornadadev.casadocodigo.order.NewOrderItemRequest;
import com.jornadadev.casadocodigo.order.NewOrderRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

class NewPurchaseRequestTest {

  private EntityManager manager = Mockito.mock(EntityManager.class);

  private CouponRepository couponRepository = Mockito.mock(CouponRepository.class);

  private Country country = new Country("country");
  private State state = new State("State", country);
  private Coupon coupon = new Coupon("111XXX", new BigDecimal(50), LocalDate.now().plusDays(30));
  private Author author = new Author("name", "name@email.com", "description");
  private Category category = new Category("romance");
  private Book book = new Book("bookTitle", "bookAbstract", "bookSummary", BigDecimal.TEN,
      120, "111XXX", new Date(), category, author);

  private List<NewOrderItemRequest> items = List.of(new NewOrderItemRequest(1l, 5));
  private NewOrderRequest order = new NewOrderRequest(new BigDecimal("50"), items);

  private NewPurchaseRequest request = new NewPurchaseRequest("email@email", "name", "surname", "111111", "address", "111", "city",
      1l, 1l,  "111111111-11", "1111111", new BigDecimal("100"), "111XXX",  order);

  {
    when(manager.find(Country.class, 1l)).thenReturn(country);
    when(manager.find(Book.class, 1l)).thenReturn(book);
    when(manager.find(State.class, 1l)).thenReturn(new State("state", country));
    when(couponRepository.getByCode("code")).thenReturn(new Coupon("code", BigDecimal.TEN, LocalDate.now().plusDays(1l)));
  }

  @Test
  @DisplayName("a purchase with state and coupon")
  void test1() throws Exception {

    request.setCouponCode("code");
    request.setStateId(1l);
    Purchase newPurchase = request.toModel(manager, couponRepository);

    assertNotNull(newPurchase);
    Mockito.verify(manager).find(State.class, 1l);
    Mockito.verify(couponRepository).getByCode("code");
  }

  @Test
  @DisplayName("a purchase without a state but with coupon")
  void test2() throws Exception {

    request.setCouponCode("code");

    Purchase newPurchase = request.toModel(manager, couponRepository);

    assertNotNull(newPurchase);
    // abre o find para garantir que tal find nao é para ser chamado nunca
    Mockito.verify(manager, Mockito.never()).find(Mockito.eq(State.class), Mockito.anyLong());
    Mockito.verify(couponRepository).getByCode("code");
  }

  @Test
  @DisplayName("purchase without state and without a coupon")
  void test3() throws Exception {
    Purchase newPurchase = request.toModel(manager, couponRepository);

    assertNotNull(newPurchase);

    // usa o matcher para facilitar achar o bug, ja que as buscas
    Mockito.verify(manager, Mockito.never()).find(Mockito.eq(State.class), Mockito.anyLong());
    Mockito.verify(couponRepository, Mockito.never()).getByCode(Mockito.anyString());
  }

  // cpf true cnpj false
  // cpf false cnpj true
  // cpf false cnpj false
  @ParameterizedTest
  @DisplayName("verify if documents are valids")
  @CsvSource({
      "111111111-11, true", "111111111-22, false"
  })
  void test4(String document, boolean expectedResult) throws Exception {
    NewPurchaseRequest request = new NewPurchaseRequest("email@email", "name", "surname", document, "address", "111", "city",
        1l, 1l,  "state", "1111111", new BigDecimal("100"), null, order);

    assertEquals(expectedResult, request.validDocument());
  }

}