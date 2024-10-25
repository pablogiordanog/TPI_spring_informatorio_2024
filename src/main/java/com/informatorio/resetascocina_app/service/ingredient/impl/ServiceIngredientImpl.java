package com.informatorio.resetascocina_app.service.ingredient.impl;

import com.informatorio.resetascocina_app.domain.Ingredient;
import com.informatorio.resetascocina_app.domain.Step;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreateDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientCreatedDto;
import com.informatorio.resetascocina_app.dto.ingredient.IngredientDto;
import com.informatorio.resetascocina_app.dto.step.StepCreateDto;
import com.informatorio.resetascocina_app.mappers.ingredient.IngredientMapper;
import com.informatorio.resetascocina_app.mappers.step.StepMapper;
import com.informatorio.resetascocina_app.repository.IngredientRepository;
import com.informatorio.resetascocina_app.service.ingredient.ServiceIngredient;
import com.informatorio.resetascocina_app.service.step.ServiceStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceIngredientImpl implements ServiceIngredient {

    private IngredientRepository ingredientRepository;
    private ServiceStep serviceStep;
    private IngredientMapper ingredientMapper;
    private StepMapper stepMapper;


    @Override
    public IngredientCreateDto newIngredient(IngredientCreateDto ingredientCreateDto) {
        StepCreateDto stepCreateDto = serviceStep.findStep(ingredientCreateDto.uuidStep());
        var ingredient = ingredientMapper.ingredientCreateDtoToIngredient(ingredientCreateDto);
        ingredient.setStep(stepMapper.stepDtpStep(stepCreateDto));

        var ingredientNew = ingredientRepository.save(ingredient);

        return new IngredientCreateDto(ingredientNew.getId(),
                ingredientNew.getName(),
                ingredientNew.getDescription(),
                ingredientNew.getStep().getId());
    }

    @Override
    public IngredientCreatedDto findIngredient(Long id) {
        Optional<Ingredient> ingredientFind = ingredientRepository.findById(id);
        if(ingredientFind.isPresent()){
            return new IngredientCreatedDto(ingredientFind.get().getId(),
                    ingredientFind.get().getName(),
                    ingredientFind.get().getDescription(),
                    ingredientFind.get().getStep().getId());
        }
        return null;
    }

    @Override
    public Optional<IngredientCreatedDto> findIngredientOptional(Long id) {
        Optional<Ingredient> ingredientFind = ingredientRepository.findById(id);
        if(ingredientFind.isPresent()){
            return  Optional.of(new IngredientCreatedDto(ingredientFind.get().getId(),
                    ingredientFind.get().getName(),
                    ingredientFind.get().getDescription(),
                    (ingredientFind.get().getStep()==null?null:ingredientFind.get().getStep().getId())));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteIngredient(Long id) {
        if(ingredientRepository.existsById(id)){
            ingredientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateIngredient(IngredientCreateDto ingredientCreateDto) {
        Optional<IngredientCreatedDto> findIngredient = findIngredientOptional(ingredientCreateDto.id());
        boolean isUpdateIngredient = Boolean.FALSE;
        if(findIngredient.isPresent()){
            Ingredient ingredient = ingredientMapper.ingredientToIngredientCreatedDto(findIngredient.get());
            ingredient.setName(ingredientCreateDto.name());
            ingredient.setDescription(ingredientCreateDto.description());

            Step step = stepMapper.stepDtpStep(serviceStep.findStep(ingredientCreateDto.uuidStep()));
            ingredient.setStep(step);

            ingredientRepository.save(ingredient);
            isUpdateIngredient = Boolean.TRUE;
        }
        return isUpdateIngredient;
    }

    @Override
    public List<IngredientCreatedDto> getIngredients() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredient -> {
                    //ingredientMapper.ingredientToIngredienteCreatedDto(ingredient);
                    UUID uuid = null;
                    try {
                        uuid =ingredient.getStep().getId();
                    }catch(Exception ex){}
                    IngredientCreatedDto dto = new IngredientCreatedDto(ingredient.getId(),
                            ingredient.getName(),
                            ingredient.getDescription(),
                            uuid);
                    return dto;
                }).collect(Collectors.toList());
    }
}
