package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.dto.category.CategoryCreatedDto;
import com.informatorio.resetascocina_app.dto.step.StepCreatedDto;
import com.informatorio.resetascocina_app.utils.DifficultyEnum;

import java.util.List;
import java.util.UUID;

public record RecipeCreatedDto(UUID id,
                               String name,
                               List<StepCreatedDto> steps,
                               DifficultyEnum difficultyEnum,
                               String description,
                               CategoryCreatedDto categoryCreatedDto) {
}
