package com.informatorio.resetascocina_app.dto.ingredient;

import com.informatorio.resetascocina_app.domain.Step;

import java.util.UUID;

public record IngredientCreateDto(Long id,
                                  String name,
                                  String description,
                                  UUID uuidStep) {
}
