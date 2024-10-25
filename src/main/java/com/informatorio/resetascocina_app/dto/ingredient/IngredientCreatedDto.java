package com.informatorio.resetascocina_app.dto.ingredient;

import java.util.UUID;

public record IngredientCreatedDto(Long id,
                                   String name,
                                   String description,
                                   UUID uuidStep) {
}
