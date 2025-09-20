package com.ms.RestaurantService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String cuisineType; // e.g., Italian, Chinese, Indian
    private String location; // full address or city

    private Date openTime; // restaurant opening time
    private Date closingTime; // restaurant closing time

    private String phoneNumber; // contact
    private String email; // optional email
    private String instagram_url; // social media
    private String facebook_url; // social media

    private String imageUrl; // restaurant main image
    private String logoUrl; // logo for branding

    private boolean isOpen; // current open/close status
    private boolean isFeatured; // featured restaurant
    private boolean isVerified; // verified restaurant by admin

//    private double averageRating; // average customer rating
//    private int totalRatings; // total number of ratings received

    private double deliveryFee; // delivery charge
    private int estimatedDeliveryTime; // in minutes

    @ElementCollection
    private List<String> tags; // e.g., ["Fast Food", "Vegetarian", "Desserts"]

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes; // menu items

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
