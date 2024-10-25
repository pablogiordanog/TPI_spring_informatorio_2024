package com.informatorio.resetascocina_app.controller.caterory;

import com.informatorio.resetascocina_app.dto.category.CategoryDto;
import com.informatorio.resetascocina_app.dto.category.CategoryCreateDto;
import com.informatorio.resetascocina_app.dto.message.MessageAction;
import com.informatorio.resetascocina_app.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/test")
    public ResponseEntity<?> testCatetory(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ok Test");
    }

    @PostMapping()
    public ResponseEntity<?> newCategory(@Valid @RequestBody CategoryCreateDto categoryNewDto){
        CategoryCreateDto categoryNew = categoryService.newCategory(categoryNewDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryNew);
    }

    @PutMapping()
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryCreateDto categoryNewDto){
        boolean isUpdateCategory = categoryService.updateCategory(categoryNewDto);
        if(isUpdateCategory){
            CategoryDto categoryDtoUpdate = categoryService.findCategory(categoryNewDto.id());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(categoryDtoUpdate);
        }else{
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }

    @GetMapping()
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<?> findCategory(@PathVariable UUID idCategory){
        Optional<CategoryDto> findCategory = categoryService.findCategoryOptional(idCategory);
        if(findCategory.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(findCategory.get());
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Categoria no encontrada."));
        }
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID idCategory){
        boolean isDeletedCategory = categoryService.deleteCategory(idCategory);
        if (isDeletedCategory){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageAction("Categoria eliminada con éxito."));
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageAction("Categoria no encontrada - No se pudo eliminar la categoria."));
        }
    }
}
