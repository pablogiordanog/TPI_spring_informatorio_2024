package com.informatorio.resetascocina_app.dto.step;

import com.informatorio.resetascocina_app.dto.ingredient.IngredientViewDto;

import java.util.List;
import java.util.UUID;

public record StepViewDto(UUID id,
                          int estimatedTime,
                          List<IngredientViewDto> ingredients) {
}
