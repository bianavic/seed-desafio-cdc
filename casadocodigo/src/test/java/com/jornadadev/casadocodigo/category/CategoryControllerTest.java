package com.jornadadev.casadocodigo.category;

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
class CategoryControllerTest {

  @Autowired
  CustomMockMvc mvc;
  private Set<String> nameGenerator = new HashSet<>();

  @Property(tries = 10)
  @Label("new category registration flow")
  void test(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String categoryName)  throws Exception {

    Assumptions.assumeTrue(nameGenerator.add(categoryName));

    mvc.post("/categories", Map.of("categoryName", categoryName))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    mvc.post("/categories", Map.of("categoryName", categoryName))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}