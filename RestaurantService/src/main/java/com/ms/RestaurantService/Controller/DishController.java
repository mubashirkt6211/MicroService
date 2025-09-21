package com.ms.RestaurantService.Controller;

import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Exception.CategoryNotFoundException;
import com.ms.RestaurantService.Service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
