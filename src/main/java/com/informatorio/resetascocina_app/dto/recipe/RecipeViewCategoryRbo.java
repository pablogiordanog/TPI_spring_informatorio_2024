package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.utils.DifficultyEnum;

import java.util.UUID;

public record RecipeViewCategoryRbo(UUID id,
                                    String name,
                                    DifficultyEnum difficultyEnum,
                                    String description,
                                    int totalPreparation) {
}
