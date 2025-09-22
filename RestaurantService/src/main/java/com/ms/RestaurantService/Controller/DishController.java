package com.ms.RestaurantService.Controller;

import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Exception.DishNotFoundException;
import com.ms.RestaurantService.Service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;


    @PostMapping("/create/{categoryId}")
    public ResponseEntity<Dish> createDish(@PathVariable Long categoryId,
                                           @RequestBody @Valid DishRequest dishRequest) throws CategoryNotFoundException {
        Dish createdDish = dishService.createDish(dishRequest, categoryId);
        return ResponseEntity.ok(createdDish);
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Dish> updateDish(
            @PathVariable Long dishId,
            @RequestBody DishRequest dishRequest) throws DishNotFoundException {
        return ResponseEntity.ok(dishService.updateDish(dishId, dishRequest));
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<String> deleteDish(@PathVariable Long dishId) throws DishNotFoundException {
        dishService.deleteDish(dishId);
        return ResponseEntity.ok("Dish deleted successfully with id: " + dishId);
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long dishId) throws DishNotFoundException {
        return ResponseEntity.ok(dishService.getDishById(dishId));
    }

    @GetMapping
    public ResponseEntity<Page<Dish>> getAllDishes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(dishService.getAllDishes(page, size, sortBy, sortDir));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Dish>> getDishesByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) throws CategoryNotFoundException {
        return ResponseEntity.ok(dishService.getDishesByCategory(categoryId, page, size, sortBy, sortDir));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Dish>> searchDishes(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) throws DishNotFoundException {
        return ResponseEntity.ok(dishService.searchDishes(keyword, page, size, sortBy, sortDir));
    }

    @PatchMapping("/{dishId}/toggle")
    public ResponseEntity<Dish> toggleAvailability(@PathVariable Long dishId) throws DishNotFoundException {
        return ResponseEntity.ok(dishService.toggleAvailability(dishId));
    }
}
