package com.jornadadev.casadocodigo.category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByCategoryName(String categoryName);
  Optional<Category> findById(Long id);

}
