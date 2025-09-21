package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.DishNotFoundException;
import com.ms.RestaurantService.Repository.CategoryRepository;
import com.ms.RestaurantService.Repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService{

    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Dish createDish(DishRequest dishRequest, Long categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

        Dish dish = new Dish();
        dish.setName(dishRequest.getName());
        dish.setDescription(dishRequest.getDescription());
        dish.setPrice(dishRequest.getPrice());
        dish.setImageUrl(dishRequest.getImageUrl());
        dish.setAvailable(dishRequest.isAvailable());
        dish.setVeg(dishRequest.isVeg());
        dish.setSpicy(dishRequest.isSpicy());
        dish.setIngredients(dishRequest.getIngredients());
        dish.setTags(dishRequest.getTags());
        dish.setPreparationTime(dishRequest.getPreparationTime());

        dish.setCategory(category);
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Long dishId, DishRequest dishRequest) throws DishNotFoundException {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("Dish not found with id: " + dishId));

        dish.setName(dishRequest.getName());
        dish.setDescription(dishRequest.getDescription());
        dish.setPrice(dishRequest.getPrice());
        dish.setImageUrl(dishRequest.getImageUrl());
        dish.setAvailable(dishRequest.isAvailable());
        dish.setVeg(dishRequest.isVeg());
        dish.setSpicy(dishRequest.isSpicy());
        dish.setIngredients(dishRequest.getIngredients());
        dish.setTags(dishRequest.getTags());
        dish.setPreparationTime(dishRequest.getPreparationTime());

        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long dishId) throws DishNotFoundException {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("Dish not found with id: " + dishId));
        dishRepository.delete(dish);
    }

    @Override
    public Dish getDishById(Long dishId) throws DishNotFoundException {
        return dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("Dish not found with id: " + dishId));
    }

    @Override
    public Page<Dish> getAllDishes(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return dishRepository.findAll(pageable);
    }

    @Override
    public Page<Dish> getDishesByCategory(Long categoryId, int page, int size, String sortBy, String sortDir) throws CategoryNotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException("Category not found with id: " + categoryId);
        }
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return dishRepository.findByCategoryId(categoryId, pageable);
    }


    @Override
    public Page<Dish> searchDishes(String keyword, int page, int size, String sortBy, String sortDir) throws DishNotFoundException {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Dish> dishes = dishRepository.findByNameContainingIgnoreCase(keyword, pageable);
        if (dishes.isEmpty()) {
            throw new DishNotFoundException("No dishes found matching: " + keyword);
        }
        return dishes;
    }

    @Override
    public Dish toggleAvailability(Long dishId) throws DishNotFoundException {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("Dish not found with id: " + dishId));
        dish.setAvailable(!dish.isAvailable());
        return dishRepository.save(dish);
    }
}
