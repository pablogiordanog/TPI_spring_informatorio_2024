package com.informatorio.resetascocina_app.controller.ingredient;

import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreateDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreatedDto;
import com.informatorio.resetascocina_app.dto.message.MessageAction;
import com.informatorio.resetascocina_app.service.ingredient.ServiceIngredient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
public class ControllerIngredient {

    private ServiceIngredient serviceIngredient;

    @GetMapping("/test")
    public ResponseEntity<?> testIngredient(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ok Test");
    }

    @PostMapping()
    public ResponseEntity<?> newIngredient(@Valid @RequestBody IngredientCreateDto ingredientCreateDto){
        IngredientCreateDto newIngredient = serviceIngredient.newIngredient(ingredientCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newIngredient);
    }

    @PutMapping()
    public ResponseEntity<?> updateIngredient(@Valid @RequestBody IngredientCreateDto ingredientCreateDto){
        boolean isUpdateIngredient = serviceIngredient.updateIngredient(ingredientCreateDto);
        if(isUpdateIngredient){
            IngredientCreatedDto ingredientDtoUpdate = serviceIngredient.findIngredient(ingredientCreateDto.id());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ingredientDtoUpdate);
        }else{
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }

    @GetMapping()
    public List<IngredientCreatedDto> getIngredients(){
        return serviceIngredient.getIngredients();
    }

    @GetMapping("/{idIngrediet}")
    public ResponseEntity<?> findIngredient(@PathVariable Long idIngrediet){
        Optional<IngredientCreatedDto> findIngredient = serviceIngredient.findIngredientOptional(idIngrediet);
        if(findIngredient.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(findIngredient.get());
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Ingrediente no encontrada."));
        }
    }

    @DeleteMapping("/{idIngredient}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long idIngredient){
        boolean isDeletedIngredient = serviceIngredient.deleteIngredient(idIngredient);
        if (isDeletedIngredient){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageAction("Ingrediente eliminado con éxito."));
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Ingrediente no encontrado - No se pudo eliminar el ingrediente."));
        }
    }
}
