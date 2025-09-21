package com.ms.RestaurantService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private boolean available; // is the dish currently available
    private boolean isVeg; // vegetarian or non-veg
    private boolean isSpicy; // spicy indicator
    private String imageUrl; // dish image

    @ElementCollection
    private List<String> ingredients; // list of ingredients

    @ElementCollection
    private List<String> tags; // e.g., ["Gluten-Free", "Low Carb"]

    private int preparationTime; // estimated preparation time in minutes

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt = new java.util.Date();
}
