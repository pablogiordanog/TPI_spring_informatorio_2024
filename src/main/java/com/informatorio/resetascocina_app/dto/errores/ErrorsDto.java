package com.informatorio.resetascocina_app.dto.errores;

import java.util.List;
import java.util.Map;

public record ErrorsDto(String mensaje, List<Map<String,String>> errors) {
}
