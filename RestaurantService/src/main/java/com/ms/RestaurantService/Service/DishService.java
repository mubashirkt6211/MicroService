package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.DishNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DishService {

    Dish createDish(DishRequest dishRequest, Long categoryId) throws CategoryNotFoundException;

    Dish updateDish(Long dishId, DishRequest dishRequest) throws DishNotFoundException;

    void deleteDish(Long dishId) throws DishNotFoundException;

    Dish getDishById(Long dishId) throws DishNotFoundException;

    Page<Dish> getAllDishes(int page, int size, String sortBy, String sortDir);

    Page<Dish> getDishesByCategory(Long categoryId, int page, int size, String sortBy, String sortDir) throws CategoryNotFoundException;

    Page<Dish> searchDishes(String keyword, int page, int size, String sortBy, String sortDir) throws DishNotFoundException;

    Dish toggleAvailability(Long dishId) throws DishNotFoundException;
}
