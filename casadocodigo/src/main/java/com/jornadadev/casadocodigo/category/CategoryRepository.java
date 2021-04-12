package com.jornadadev.casadocodigo.category;

import com.jornadadev.casadocodigo.category.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Optional<Category> findByCategoryName(String categoryName);
}
