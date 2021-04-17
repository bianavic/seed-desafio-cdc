package com.jornadadev.casadocodigo.book;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllById(Iterable<Long> longs);
}