package com.informatorio.resetascocina_app.mappers.ingredient;

import com.informatorio.resetascocina_app.domain.Ingredient;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreateDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreatedDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientDto;
import org.mapstruct.Mapper;

@Mapper
public interface IngredientMapper {
    IngredientCreateDto ingredientCreateDtoToIngredient(Ingredient ingredient);
    Ingredient ingredientCreateDtoToIngredient(IngredientCreateDto ingredientCreateDto);
    IngredientCreatedDto ingredientToIngredienteCreatedDto(Ingredient ingredient);

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
    Ingredient ingredientToIngredientCreatedDto(IngredientCreatedDto ingredientCreatedDto);
}
