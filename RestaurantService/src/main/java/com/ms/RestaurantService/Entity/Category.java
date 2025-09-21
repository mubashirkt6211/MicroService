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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // e.g., "Starters", "Main Course"
    private String imageUrl;  // ✅ Category-specific image

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Dish> dishes;
}