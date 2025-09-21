package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.CategoryRequest;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Repository.CategoryRepository;
import com.ms.RestaurantService.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Category addCategory(CategoryRequest categoryRequest) throws RestaurantException {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setImageUrl(categoryRequest.getImageUrl());

        Restaurant restaurant = restaurantRepository.findById(categoryRequest.getRestaurantId())
                .orElseThrow(() -> new RestaurantException(
                        "Restaurant not found with ID: " + categoryRequest.getRestaurantId()
                ));
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryRequest categoryRequest) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));

        category.setName(categoryRequest.getName());
        category.setImageUrl(categoryRequest.getImageUrl());

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategory(String keyword) throws CategoryNotFoundException {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(keyword);
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found for keyword: " + keyword);
        }
        return categories;
    }
}
