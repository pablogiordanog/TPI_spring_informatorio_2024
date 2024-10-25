package com.informatorio.resetascocina_app.dto.step;

import com.informatorio.resetascocina_app.dto.ingredient.IngredientDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record StepCreatedDto(UUID id,
                             int estimatedTime,
                             @NotNull(message = "El nombre de paso no puede ser nulo")
                             @NotBlank(message = "El nombre de paso no puede ser vacio")
                             String description,
                             boolean isOptional,
                             List<IngredientDto> ingredients,
                             UUID recipe_id) {
}
