package com.ms.RestaurantService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {

    private String name;
    private String description;
    private double price;
    private boolean available;
    private boolean isVeg;
    private boolean isSpicy;
    private String imageUrl;
    private List<String> ingredients;
    private List<String> tags;
    private int preparationTime;
//    private Long categoryId;
}
