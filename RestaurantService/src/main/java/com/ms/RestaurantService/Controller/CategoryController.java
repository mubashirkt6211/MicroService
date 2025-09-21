package com.ms.RestaurantService.Controller;

import com.ms.RestaurantService.Dto.CategoryRequest;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest request) throws RestaurantException {
        Category saved = categoryService.addCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @Valid @RequestBody CategoryRequest request) throws CategoryNotFoundException {
        Category updated = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategory(@RequestParam String keyword) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.searchCategory(keyword));
    }

}
