package com.informatorio.resetascocina_app.service.recipe;

import com.informatorio.resetascocina_app.domain.Recipe;
import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.recipe.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRecipe {
    RecipeCreatedDto newRecipe(RecipeCreateDto recipeCreateDto);
    boolean deleteRecipe(UUID idRecipe);
    boolean updateRecipe(RecipeUpdateDto recipeUpdateDto);
    RecipeDto findRecipe(UUID idRecipe);
    Optional<RecipeViewDto> findRecipeOptional(UUID id);
    List<RecipeViewDto> getRecipes();
    List<RecipeViewCategoryRbo> getRecipePorCategoryesId(UUID uuidCategory);
    List<RecipeViewCategoryRbo> getRecipePorCategoryesNames(String categoyName);
    boolean updateStepRecipe(RecipeUpdateDto recipeUpdateDto);
}
