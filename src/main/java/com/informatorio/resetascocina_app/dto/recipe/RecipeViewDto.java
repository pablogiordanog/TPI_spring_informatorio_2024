package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.dto.category.CategoryViewDto;
import com.informatorio.resetascocina_app.dto.step.StepViewDto;
import com.informatorio.resetascocina_app.utils.DifficultyEnum;

import java.util.List;
import java.util.UUID;

public record RecipeViewDto(UUID id,
                            String name,
                            String description,
                            DifficultyEnum difficultyEnum,
                            CategoryViewDto categoryViewDto,
                            List<StepViewDto> steps) {
}
