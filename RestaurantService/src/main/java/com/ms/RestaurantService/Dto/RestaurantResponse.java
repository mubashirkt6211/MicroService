package com.ms.RestaurantService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private String location;
    private String phoneNumber;
    private String email;
    private String instagramUrl;
    private String facebookUrl;
    private String imageUrl;
    private String logoUrl;
    private boolean open;
    private boolean featured;
    private boolean verified;
    private double deliveryFee;
    private int estimatedDeliveryTime;
    private List<String> tags;
    private List<CategoryResponse> categories;
}
