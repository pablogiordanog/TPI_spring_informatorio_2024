package com.informatorio.resetascocina_app.service.category.impl;

import com.informatorio.resetascocina_app.domain.Category;
import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.category.CategoryCreateDto;
import com.informatorio.resetascocina_app.mappers.category.CategoryMapper;
import com.informatorio.resetascocina_app.repository.CategoryRepository;
import com.informatorio.resetascocina_app.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public CategoryCreateDto newCategory(CategoryCreateDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategoryNewDto(categoryDto);
        if(category.getId()==null || category.getId().toString().isBlank()){
            category.setId(UUID.randomUUID());
        }
        var categoryNew = categoryRepository.save(category);
        return new CategoryCreateDto(categoryNew.getId(),categoryNew.getName(), null);
    }

    @Override
    public CategoryDto findCategory(UUID id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if(categoryFind.isPresent()){
            return categoryMapper.categoryToCategoryDto(categoryFind.get());
        }
        return null;
    }

    @Override
    public CategoryCreateDto findCategoryNew(UUID id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if(categoryFind.isPresent()){
            return categoryMapper.categoryToCategoryNewDto(categoryFind.get());
        }
        return null;
    }

    @Override
    public boolean isFindCategory(UUID id) {
        return (findCategory(id)!=null);
    }

    @Override
    public Optional<CategoryDto> findCategoryOptional(UUID id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if(categoryFind.isPresent()){
            return Optional.of(categoryMapper.categoryToCategoryDto(categoryFind.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCategory(UUID id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCategory(CategoryCreateDto categoryNewDto) {
        Optional<CategoryDto> findCategory = findCategoryOptional(categoryNewDto.id());
        boolean isUpdateCategory = Boolean.FALSE;
        if(findCategory.isPresent()){
            Category category = categoryMapper.categoryDtoToCategory(findCategory.get());
            category.setName(categoryNewDto.name());
            categoryRepository.save(category);
            isUpdateCategory = Boolean.TRUE;
        }
        return isUpdateCategory;
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDto -> categoryMapper.categoryToCategoryDto(categoryDto)).toList();
    }
}
