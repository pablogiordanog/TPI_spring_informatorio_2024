package com.informatorio.resetascocina_app.service.ingredient;

import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreateDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreatedDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientDto;

import java.util.List;
import java.util.Optional;

public interface ServiceIngredient {

    IngredientCreateDto newIngredient(IngredientCreateDto ingredientCreateDto);

    IngredientCreatedDto findIngredient(Long id);

    Optional<IngredientCreatedDto> findIngredientOptional(Long id);

    boolean deleteIngredient(Long id);

    boolean updateIngredient(IngredientCreateDto ingredientCreateDto);

    List<IngredientCreatedDto> getIngredients();

}
