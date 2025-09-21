package com.ms.RestaurantService.Controller;

import com.ms.RestaurantService.Dto.RestaurantRequest;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody RestaurantRequest request) {
        Restaurant created = restaurantService.createRestaurant(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequest request) throws RestaurantException {
        Restaurant updated = restaurantService.updateRestaurant(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) throws RestaurantException {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) throws RestaurantException {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
}
