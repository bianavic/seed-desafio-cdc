package com.jornadadev.casadocodigo.coupon;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

class CouponTest {

  @ParameterizedTest
  @CsvSource({
      "0, true",
      "1, true"
  })
  @DisplayName("create a coupon with a valid date")
  void test1(long value, boolean result) throws Exception {

    Date mock = mock(Date.class);

    Coupon coupon = new Coupon("code", BigDecimal.TEN, LocalDate.now().plusDays(value));
    Assertions.assertEquals(result, coupon.valid());
  }

  @Test
  @DisplayName("coupon with date in the past isn't valid anymore")
  void test2() throws Exception {
    Coupon coupon = new Coupon("code", BigDecimal.TEN, LocalDate.now().plusDays(1));
    // muda valor de 1 campo em um objeto ja criado
    ReflectionTestUtils.setField(coupon, "expiresAt", LocalDate.now().minusDays(1));
    Assertions.assertFalse(coupon.valid());
  }

  @Test
  @DisplayName("cannot create a coupon with date in the past")
  void test3() throws Exception {

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Coupon("code", BigDecimal.TEN, LocalDate.now().minusDays(1));
    });
  }

}