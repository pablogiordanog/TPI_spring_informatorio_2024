package com.informatorio.resetascocina_app.dto.category;

import com.informatorio.resetascocina_app.domain.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CategoryDto(UUID id,
                          @NotNull(message = "El nombre de categoria no puede ser nulo")
                          @NotBlank(message = "El nombre de categoria no puede ser vacio")
                          @Size(max = 100, message = "El nombre de categoria no puede ser mayor a 100 caracteres")
                          String name,
                          Recipe recipe) {
}