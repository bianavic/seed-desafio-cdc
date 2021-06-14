package com.jornadadev.casadocodigo.countrystate;

import com.jornadadev.casadocodigo.CustomMockMvc;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

  @Autowired
  private CustomMockMvc mvc;
  private static Set<String> uniques = new HashSet<>();

  @Property(tries = 10)
  @Label("new country registration flow")
  void test(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String countryName) throws Exception {

    Assumptions.assumeTrue(uniques.add(countryName));

    mvc.post("/countries", Map.of("countryName", countryName)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    mvc.post("/countries", Map.of("countryName", countryName)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

}