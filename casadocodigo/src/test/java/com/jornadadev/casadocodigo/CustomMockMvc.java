package com.jornadadev.casadocodigo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class CustomMockMvc {

  @Autowired
  MockMvc mvc;

  public ResultActions post(String url, Map<String, Object> params) {
    try {
      String payload = new ObjectMapper()
          .writeValueAsString(params);

      MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(payload);

      return mvc.perform(content);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
