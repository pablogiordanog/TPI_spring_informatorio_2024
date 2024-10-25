package com.informatorio.resetascocina_app.dto.step;

import com.informatorio.resetascocina_app.dto.ingredient.IngredientUpdateDto;

import java.util.List;
import java.util.UUID;

public record StepUpdateDto(UUID id,
                            String description,
                            int estimatedTime,
                            boolean isOptional,
                            List<IngredientUpdateDto> ingredientUpdateDtos) {
}
