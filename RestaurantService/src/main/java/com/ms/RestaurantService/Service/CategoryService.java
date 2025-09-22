package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.CategoryRequest;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.RestaurantException;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryRequest categoryRequest) throws RestaurantException;

    void deleteCategory(Long id) throws CategoryNotFoundException;

    Category updateCategory(Long id,CategoryRequest categoryRequest) throws CategoryNotFoundException;

    Category getCategoryById(Long id) throws CategoryNotFoundException;

    List<Category> getAllCategories();

    List<Category> searchCategory(String keyword) throws CategoryNotFoundException;

}
