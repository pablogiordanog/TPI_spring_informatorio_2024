package com.informatorio.resetascocina_app.repository;

import com.informatorio.resetascocina_app.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
