package com.informatorio.resetascocina_app.repository;

import com.informatorio.resetascocina_app.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    List<Recipe> findByCategoryId(UUID uuid);
    List<Recipe> findByCategoryName(String categoryName);

}
