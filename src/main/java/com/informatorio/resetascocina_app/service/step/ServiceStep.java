package com.informatorio.resetascocina_app.service.step;

import com.informatorio.resetascocina_app.domain.Step;
import com.informatorio.resetascocina_app.dto.step.StepCreateDto;

import java.util.UUID;

public interface ServiceStep {
    StepCreateDto newStep(StepCreateDto stepCreateDto);
    StepCreateDto findStep(UUID id);
    boolean updateStep(Step step);
}
