package com.informatorio.resetascocina_app.service.recipe.impl;

import com.informatorio.resetascocina_app.domain.Category;
import com.informatorio.resetascocina_app.domain.Recipe;
import com.informatorio.resetascocina_app.domain.Step;
import com.informatorio.resetascocina_app.dto.category.CategoryCreateDto;
import com.informatorio.resetascocina_app.dto.category.CategoryViewDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientViewDto;
import com.informatorio.resetascocina_app.dto.recipe.*;
import com.informatorio.resetascocina_app.dto.step.StepCreatedDto;
import com.informatorio.resetascocina_app.dto.step.StepViewDto;
import com.informatorio.resetascocina_app.mappers.category.CategoryMapper;
import com.informatorio.resetascocina_app.mappers.recipe.RecipeMapper;
import com.informatorio.resetascocina_app.mappers.step.StepMapper;
import com.informatorio.resetascocina_app.repository.RecipeRepository;
import com.informatorio.resetascocina_app.repository.StepRepository;
import com.informatorio.resetascocina_app.service.category.CategoryService;
import com.informatorio.resetascocina_app.service.recipe.ServiceRecipe;
import com.informatorio.resetascocina_app.service.step.ServiceStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class ServiceRecipeImpl implements ServiceRecipe {
    private RecipeRepository recipeRepository;
    private RecipeMapper recipeMapper;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private StepRepository stepRepository;
    private StepMapper stepMapper;

    private RecipeViewDto recipeToRecipeViewDto(Recipe recipe){
       return new RecipeViewDto(recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getDifficultyEnum(),
                new CategoryViewDto(recipe.getCategory().getId(),
                        recipe.getCategory().getName()),
                recipe.getSteps().stream()
                        .map(stepDto ->{
                            return new StepViewDto(stepDto.getId(),
                                    stepDto.getEstimatedTime(),
                                    stepDto.getIngredients().stream()
                                            .map(ingredient -> {
                                                return new IngredientViewDto(ingredient.getId(),
                                                        ingredient.getName());
                                            }).toList());
                        }).toList());
    }

    private RecipeViewCategoryRbo recipeToRecipeViewCategoryDto(Recipe recipe){
        AtomicReference<Integer> totTimePrepar = new AtomicReference<>(0);
        recipe.getSteps().stream()
                .map(step -> {
                    if(!step.isOptional()){
                        totTimePrepar.set(totTimePrepar.get() + step.getEstimatedTime());
                    }
                    return step;
                }).toList();

        return new RecipeViewCategoryRbo(recipe.getId(),
                recipe.getName(),
                recipe.getDifficultyEnum(),
                recipe.getDescription(),
                totTimePrepar.get().intValue());
    }

    @Override
    public RecipeCreatedDto newRecipe(RecipeCreateDto recipeNewDto) {
        Recipe recipe = recipeMapper.recipeCreateDtoToRecipe(recipeNewDto);
        recipe.setId(UUID.randomUUID());

        recipe.getSteps().stream()
                .map(step -> {
                    step.setRecipe(recipe);
                    step.getIngredients()
                            .stream()
                            .map(ingredient -> {
                                ingredient.setStep(step);
                                return ingredient;
                            }).toList();
                    return step;
                }).toList();


        Recipe recipeNew = null;
        if(recipeNewDto.category()==null){
            recipe.setCategory(null);
            recipeNew = recipeRepository.save(recipe); //Guarda la receta sin la categoria
        }else{
            //recipeNewDto.category().setId(UUID.randomUUID());
            Category category = categoryMapper.categoryDtoToCategoryNewDto(categoryService.newCategory(new CategoryCreateDto(recipeNewDto.category().getId(), recipeNewDto.category().getName(), recipe.getId())));
            recipe.setCategory(category);
            recipeNew = recipeRepository.save(recipe); //Guarda la receta con la categoria asociada
        }

        RecipeCreateDto recipeNewDto1 = recipeMapper.recipeToRecipeCreateDto(recipeNew);

        return new RecipeCreatedDto(recipeNewDto1.id(),
                recipeNewDto1.name(),
                recipeNewDto1.steps().stream()
                        .map(stepCreateDto -> {
                            StepCreatedDto stepCreatedDto1 = new StepCreatedDto(stepCreateDto.id(),
                                    stepCreateDto.estimatedTime(),
                                    stepCreateDto.description(),
                                    stepCreateDto.isOptional(),
                                    stepCreateDto.ingredients().stream()
                                            .map(ingredientCreatedDto -> {
                                                IngredientDto ingredientDto = new IngredientDto(ingredientCreatedDto.id(),
                                                        ingredientCreatedDto.name(),
                                                        ingredientCreatedDto.description(),
                                                        stepCreateDto.id());
                                                return ingredientDto;
                                            }).toList(),
                                    recipeNewDto1.id());

                            return stepCreatedDto1;
                        }).toList(),
                recipeNewDto1.difficultyEnum(),
                recipeNewDto1.description(),
                categoryMapper.categoryToCategoryCreatedDto(recipeNew.getCategory()));
    }

    @Override
    public boolean deleteRecipe(UUID idRecipe) {
        Optional<RecipeViewDto> recipeViewDtoOptional = findRecipeOptional(idRecipe);
        if(recipeViewDtoOptional.isPresent()){
            recipeRepository.deleteById(idRecipe);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRecipe(RecipeUpdateDto recipeUpdateDto) {
        return false;
    }

    @Override
    public RecipeDto findRecipe(UUID idRecipe) {
        Optional<Recipe> recipeFind = recipeRepository.findById(idRecipe);
        if(recipeFind.isPresent()){
            return recipeMapper.recipeToRecipeDto(recipeFind.get());
        }
        return null;
    }

    @Override
    public Optional<RecipeViewDto> findRecipeOptional(UUID id) {
        Optional<Recipe> recipeFind = recipeRepository.findById(id);
        if(recipeFind.isPresent()){
            return Optional.of(recipeToRecipeViewDto(recipeFind.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<RecipeViewDto> getRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> {
                    return recipeToRecipeViewDto(recipe);
                }).toList();
    }

    @Override
    public List<RecipeViewCategoryRbo> getRecipePorCategoryesId(UUID uuidCategory) {
        //return null;
        return recipeRepository.findByCategoryId(uuidCategory).stream()
                .map(recipe -> {
                    return recipeToRecipeViewCategoryDto(recipe);
                }).toList();
    }

    @Override
    public List<RecipeViewCategoryRbo> getRecipePorCategoryesNames(String categoyName) {
        return recipeRepository.findByCategoryName(categoyName).stream()
                .map(recipe -> {
                    return recipeToRecipeViewCategoryDto(recipe);
                }).toList();
    }

    @Override
    public boolean updateStepRecipe(RecipeUpdateDto recipeUpdateDto) {
        Optional<RecipeViewDto> recipeViewDtoOptional = findRecipeOptional(recipeUpdateDto.id());
        if(recipeViewDtoOptional.isPresent()){
            Step step = null;
            for(StepViewDto stepDto:recipeViewDtoOptional.get().steps()){
                if(stepDto.id().toString().equals(recipeUpdateDto.stepUpdateDto().id().toString())){
                    step = stepMapper.stepToViewDto(stepDto);
                    break;
                }
            }
            if(step!=null){
                Recipe recipe = recipeMapper.recipeViewDtoToRecipe(recipeViewDtoOptional.get());
                step.setDescription(recipeUpdateDto.stepUpdateDto().description());
                step.setEstimatedTime(recipeUpdateDto.stepUpdateDto().estimatedTime());
                step.setOptional(recipeUpdateDto.stepUpdateDto().isOptional());
                step.setRecipe(recipe);
                stepRepository.save(step);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}
