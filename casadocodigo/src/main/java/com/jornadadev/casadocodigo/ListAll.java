package com.jornadadev.casadocodigo;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListAll {

  @PersistenceContext
  private EntityManager manager;

  public HashMap<String, Object> list() {
    List authors = manager.createQuery("select a from Author a").getResultList();

    HashMap<String, Object> result = new HashMap<>();
    result.put("authors", authors.toString());

    List categories = manager.createQuery("select c from Category c").getResultList();
    result.put("categories", categories.toString());

    return result;
  }

}
