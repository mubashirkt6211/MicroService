package com.ms.RestaurantService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {

    private String name;
    private String description;
    private String cuisineType;
    private String location;
    private Date openTime;
    private Date closingTime;
    private String phoneNumber;
    private String email;
    private String instagramUrl;
    private String facebookUrl;
    private String imageUrl;
    private String logoUrl;
    private boolean isOpen;
    private boolean isFeatured;
    private boolean isVerified;
    private double deliveryFee;
    private int estimatedDeliveryTime;

    private List<String> tags;
    private List<DishRequest> dishes;
    private List<CategoryRequest> categories;

}
