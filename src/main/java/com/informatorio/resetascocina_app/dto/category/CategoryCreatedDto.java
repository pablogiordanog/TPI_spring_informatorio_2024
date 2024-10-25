package com.informatorio.resetascocina_app.dto.category;

import com.informatorio.resetascocina_app.domain.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CategoryCreatedDto(UUID id,
                                 String name) {
}
