package com.informatorio.resetascocina_app.dto.recipe;

import com.informatorio.resetascocina_app.domain.Category;
import com.informatorio.resetascocina_app.domain.Step;
import com.informatorio.resetascocina_app.dto.step.StepCreateDto;
import com.informatorio.resetascocina_app.utils.DifficultyEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record RecipeCreateDto(UUID id,
                              @NotNull(message = "El nombre de la receta no puede ser nulo")
                           @NotBlank(message = "El nombre de la receta no puede ser vacio")
                           @Size(max = 500, message = "El nombre de la receta no puede ser mayor a 500 caracteres")
                           String name,

                              List<StepCreateDto> steps,
                              DifficultyEnum difficultyEnum,

                              @NotNull(message = "La descripción de la receta no puede ser nulo")
                           @NotBlank(message = "La descripción de la receta no puede ser vacio")
                           @Size(max = 5000, message = "La descripción de la receta no puede ser mayor a 5000 caracteres")
                           String description,
                              Category category) {
}
