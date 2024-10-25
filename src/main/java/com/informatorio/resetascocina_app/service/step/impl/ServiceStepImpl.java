package com.informatorio.resetascocina_app.service.step.impl;


import com.informatorio.resetascocina_app.domain.Recipe;
import com.informatorio.resetascocina_app.domain.Step;
import com.informatorio.resetascocina_app.dto.step.StepCreateDto;
import com.informatorio.resetascocina_app.mappers.recipe.RecipeMapper;
import com.informatorio.resetascocina_app.mappers.step.StepMapper;
import com.informatorio.resetascocina_app.repository.RecipeRepository;
import com.informatorio.resetascocina_app.repository.StepRepository;
import com.informatorio.resetascocina_app.service.recipe.ServiceRecipe;
import com.informatorio.resetascocina_app.service.step.ServiceStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceStepImpl implements ServiceStep {
    private StepRepository stepRepository;
    private StepMapper stepMapper;
    private ServiceRecipe serviceRecipe;
    private RecipeMapper recipeMapper;

    @Override
    public StepCreateDto newStep(StepCreateDto stepCreateDto) {
        Step step = stepMapper.stepDtpStep(stepCreateDto);
        step.setId(UUID.randomUUID());
        Recipe recipe = recipeMapper.recipeDtoToRecipe(serviceRecipe.findRecipe(stepCreateDto.recipe_id()));
        step.setRecipe(recipe);

        return stepMapper.stepToStepDto(stepRepository.save(step));
    }

    @Override
    public StepCreateDto findStep(UUID id) {
        Optional<Step> stepFind = stepRepository.findById(id);
        if(stepFind.isPresent()){
            return stepMapper.stepToStepDto(stepFind.get());
        }
        return null;
    }

    @Override
    public boolean updateStep(Step step) {
        Optional<Step> stepOptional = stepRepository.findById(step.getId());
        if(stepOptional.isPresent()){
            stepRepository.save(stepOptional.get());
            return true;
        }
        return false;
    }
}
