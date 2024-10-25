package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.dto.category.CategoryCreatedDto;
import com.informatorio.resetascocina_app.dto.step.StepCreatedDto;
import com.informatorio.resetascocina_app.utils.DifficultyEnum;

import java.util.List;
import java.util.UUID;

public record RecipeDto(UUID id,
                        String name,
                        String description,
                        DifficultyEnum difficultyEnum,
                        CategoryCreatedDto categoryCreatedDto,
                        List<StepCreatedDto> steps) {
}
