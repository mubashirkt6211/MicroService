package com.ms.RestaurantService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {

    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(min = 2, max = 255)
    private String name;
    private String description;
    private String cuisineType;
    private String location;
    private String openTime;
    private String closingTime;
    private String phoneNumber;
    private String email;
    private String instagramUrl;
    private String facebookUrl;
    private String imageUrl;
    private String logoUrl;
    private double deliveryFee;
    private int estimatedDeliveryTime;
    private boolean isOpen;
    private boolean isFeatured;
    private boolean isVerified;
    private List<String> tags;
    private List<CategoryRequest> categories;
}
