package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.dto.step.StepUpdateDto;
import com.informatorio.resetascocina_app.dto.step.StepViewDto;

import java.util.UUID;

public record RecipeUpdateDto(UUID id,
                              StepUpdateDto stepUpdateDto) {
}
