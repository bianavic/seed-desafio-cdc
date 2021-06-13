package com.jornadadev.casadocodigo.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.jornadadev.casadocodigo.coupon.Coupon;
import com.jornadadev.casadocodigo.coupon.CouponRepository;
import com.jornadadev.casadocodigo.order.NewOrderItemRequest;
import com.jornadadev.casadocodigo.order.NewOrderRequest;
import com.jornadadev.casadocodigo.purchase.NewPurchaseRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

class CouponValidatorTest {

  private CouponRepository couponRepository = Mockito.mock(CouponRepository.class);
  private List<NewOrderItemRequest> items = List.of(new NewOrderItemRequest(1l, 10));
  private NewOrderRequest order = new NewOrderRequest(BigDecimal.TEN, items);
  private NewPurchaseRequest request = new NewPurchaseRequest("email@email", "name", "surname", "111111", "address", "111", "city",
      1l, "999999999",  "111111111-11", order);

  @Test
  @DisplayName("should reject if coupon is invalid")
  void test1() throws Exception {
    // o cupom precisa que sua validade esteja para tras do dia de hoje
    Coupon coupon = new Coupon("code", BigDecimal.TEN, LocalDate.now());
    // importante usar field name com a variavel correta (expiresAt)
    setField(coupon, "expiresAt", LocalDate.now().minusDays(1));

    request.setCouponCode("code");

    Errors errors = new BeanPropertyBindingResult(request, "target");
    CouponValidator validator = new CouponValidator(couponRepository);

    Mockito.when(couponRepository.getByCode("code")).thenReturn(coupon);

    validator.validate(request, errors);

    assertTrue(errors.hasErrors());
    assertNotNull("this coupon is no longer valid", errors.getFieldError("couponCode").getDefaultMessage());
  }

  @Test
  @DisplayName("should pass if coupon is valid")
  void test2() throws Exception {
    Coupon coupon = new Coupon("code", BigDecimal.TEN, LocalDate.now().plusDays(1));

    request.setCouponCode("code");
    Mockito.when(couponRepository.getByCode("code")).thenReturn(coupon);

    Errors errors = new BeanPropertyBindingResult(request, "target");
    CouponValidator validator = new CouponValidator(couponRepository);
    validator.validate(request, errors);

    assertFalse(errors.hasErrors());
  }

  @Test
  @DisplayName("should pass if you don't have coupon code")
  void test3() throws Exception {
    Errors errors = new BeanPropertyBindingResult(request, "target");

    CouponValidator validator = new CouponValidator(couponRepository);
    validator.validate(request, errors);

    assertFalse(errors.hasErrors());
  }

  @Test
  @DisplayName("should stop if you already have an error")
  void test4() throws Exception {
    Errors errors = new BeanPropertyBindingResult(request, "target");
    errors.reject("code");

    CouponValidator validator = new CouponValidator(couponRepository);
    validator.validate(request, errors);

    assertTrue(errors.getAllErrors().size() == 1);
    assertEquals("code", errors.getGlobalErrors().get(0).getCode());
  }

}