package com.informatorio.resetascocina_app.dto.ingredient;

import com.informatorio.resetascocina_app.domain.Step;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record IngredientDto(Long id,
                            @NotNull(message = "El nombre del ingrediente no puede ser nulo")
                            @NotBlank(message = "El nombre del ingrediente no puede ser vacio")
                            @Size(max = 60, message = "El nombre de ingrediente no puede ser mayor a 60 caracteres")
                            String name,
                            @Size(max = 60, message = "La descripción del ingrediente no puede ser mayor a 500 caracteres")
                            String description,
                            UUID uuidStep) {
}
