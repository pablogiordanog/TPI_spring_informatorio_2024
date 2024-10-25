package com.informatorio.resetascocina_app.service.category;

import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.category.CategoryCreateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    CategoryCreateDto newCategory(CategoryCreateDto categoria);

    CategoryDto findCategory(UUID id);

    CategoryCreateDto findCategoryNew(UUID id);

    boolean isFindCategory(UUID id);

    Optional<CategoryDto> findCategoryOptional(UUID id);

    boolean deleteCategory(UUID id);

    boolean updateCategory(CategoryCreateDto categoryDto);

    List<CategoryDto> getCategories();

}
