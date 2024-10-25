package com.informatorio.resetascocina_app.mappers.step;

import com.informatorio.resetascocina_app.domain.Step;

import com.informatorio.resetascocina_app.dto.step.StepCreateDto;
import com.informatorio.resetascocina_app.dto.step.StepViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface StepMapper {
    StepCreateDto stepToStepDto(Step step);
    Step stepDtpStep(StepCreateDto step);
    Step stepToViewDto(StepViewDto stepViewDto);
}
