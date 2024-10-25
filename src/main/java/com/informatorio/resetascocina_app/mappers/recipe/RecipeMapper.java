package com.informatorio.resetascocina_app.mappers.recipe;

import com.informatorio.resetascocina_app.domain.Recipe;
import com.informatorio.resetascocina_app.dto.recipe.RecipeCreateDto;
import com.informatorio.resetascocina_app.dto.recipe.RecipeDto;
import com.informatorio.resetascocina_app.dto.recipe.RecipeViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeMapper {
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);
    Recipe recipeCreateDtoToRecipe(RecipeCreateDto recipeNewDto);
    RecipeCreateDto recipeToRecipeCreateDto(Recipe recipe);
    RecipeDto recipeToRecipeDto(Recipe recipe);
    RecipeViewDto recipeToRecipeViewDto(Recipe recipe);
    Recipe recipeViewDtoToRecipe(RecipeViewDto recipeViewDto);
}
