package com.jornadadev.casadocodigo.coupon;

import com.jornadadev.casadocodigo.CustomMockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.time.api.Dates;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class CouponControllerTest {

  private Set<String> unique = new HashSet<>();
  @Autowired
  private CustomMockMvc mvc;

  @Property(tries = 10)
  @Label("new coupon registration flow")
  void test(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String code,
      @ForAll @BigRange(min = "1", max = "90") BigDecimal discountPercentage,
      @ForAll("futureDates")LocalDate expiresAt) throws Exception {

    Assumptions.assumeTrue(unique.add(code));

    String validityFormated = expiresAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    mvc.post("/coupons", Map.of("code", code, "discountPercentage", discountPercentage.toString(), "expiresAt", validityFormated ))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    mvc.post("/coupons", Map.of("code", code, "discountPercentage", discountPercentage.toString(), "expiresAt", validityFormated ))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Provide
  Arbitrary<LocalDate> futureDates() {
    return Dates.dates().atTheEarliest(LocalDate.now().plusDays(1));
  }
}