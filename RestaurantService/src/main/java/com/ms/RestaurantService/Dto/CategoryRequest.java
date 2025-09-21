package com.ms.RestaurantService.Dto;

import com.ms.RestaurantService.Entity.Restaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;
    private String imageUrl;
    private Long restaurantId;
    private List<DishRequest> dishes;
}
