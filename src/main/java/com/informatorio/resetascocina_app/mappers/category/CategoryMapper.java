package com.informatorio.resetascocina_app.mappers.category;

import com.informatorio.resetascocina_app.domain.Category;
import com.informatorio.resetascocina_app.dto.category.CategoryCreatedDto;
import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.category.CategoryCreateDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

    CategoryCreateDto categoryToCategoryNewDto(Category category);
    Category categoryDtoToCategoryNewDto(CategoryCreateDto categoryNewDto);
    CategoryCreatedDto categoryToCategoryCreatedDto(Category category);
}
