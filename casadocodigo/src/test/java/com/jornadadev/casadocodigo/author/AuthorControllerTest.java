package com.jornadadev.casadocodigo.author;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

  @Autowired
  private MockMvc mvc;

  private final Set<String> emailGenerator = new HashSet<>();

  @Property(tries = 10)
  @Label("new author registration flow")
  void test(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String authorName,
      @ForAll @AlphaChars @StringLength(min = 1, max = 50) String email,
      @ForAll @AlphaChars @StringLength(min = 1, max = 255) String description) throws Exception {

    assumeTrue(emailGenerator.add(email));

    String payload = new ObjectMapper()
        .writeValueAsString(
            Map.of("authorName", authorName,
                "email", email+"@gmail.com",
                "description", description));

    MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload);

    mvc.perform(content).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    mvc.perform(content).andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

}