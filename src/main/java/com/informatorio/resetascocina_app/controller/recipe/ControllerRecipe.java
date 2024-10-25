package com.informatorio.resetascocina_app.controller.recipe;

import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.message.MessageAction;
import com.informatorio.resetascocina_app.dto.recipe.*;
import com.informatorio.resetascocina_app.service.recipe.ServiceRecipe;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipe")
@AllArgsConstructor
public class ControllerRecipe {

    private ServiceRecipe serviceRecipe;

    @GetMapping("/test")
    public ResponseEntity<?> testIngredient(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ok Test");
    }

    @PostMapping()
    public ResponseEntity<?> newRecipe(@Valid @RequestBody
                                           RecipeCreateDto recipeCreateDto){
        RecipeCreatedDto newRecipeDto = serviceRecipe.newRecipe(recipeCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newRecipeDto);
    }

    @GetMapping("/recipeId/{idRecipe}")
    public ResponseEntity<?> findRecipe(@PathVariable("idRecipe") UUID idRecipe){
        Optional<RecipeViewDto> findRecipe = serviceRecipe.findRecipeOptional(idRecipe);
        if(findRecipe.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(findRecipe.get());
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Reseta no encontrada."));
        }
    }

    @DeleteMapping("/recipeId/{idRecipe}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("idRecipe") UUID idRecipe){
        boolean isDeleteRecipe = serviceRecipe.deleteRecipe(idRecipe);
        if(isDeleteRecipe){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageAction("Reseta eliminada con éxito."));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Reseta no encontrada."));
        }
    }


    @GetMapping("/categoryId/{idCategory}")
    public List<RecipeViewCategoryRbo> getRecipesDoCategoryId(@PathVariable("idCategory") UUID idCategory){
        return serviceRecipe.getRecipePorCategoryesId(idCategory);
    }

    @GetMapping("/categoryName/{categoryName}")
    public List<RecipeViewCategoryRbo> getRecipesDoCategoryName(@PathVariable("categoryName") String categoryName){
        return serviceRecipe.getRecipePorCategoryesNames(categoryName);
    }

    @PutMapping()
    public ResponseEntity<?> updateStepRecipe(@Valid @RequestBody RecipeUpdateDto recipeUpdateDto) {
        boolean isUpdate = serviceRecipe.updateStepRecipe(recipeUpdateDto);
        if(isUpdate){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageAction("Paso de la receta Actualizado con éxito."));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Paso de la receta No actualizado."));
        }
    }

    @GetMapping()
    public List<RecipeViewDto> getRecipes(){
        return serviceRecipe.getRecipes();
    }

}
