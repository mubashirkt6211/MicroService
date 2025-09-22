package com.ms.RestaurantService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "cuisine_type")
    private String cuisineType;

    private String location;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "delivery_fee", nullable = false)
    private double deliveryFee;

    @Column(name = "estimated_delivery_time", nullable = false)
    private int estimatedDeliveryTime;

    @Column(name = "is_open", nullable = false)
    @JsonProperty("isOpen")
    private boolean isOpen;

    @Column(name = "is_featured", nullable = false)
    @JsonProperty("isFeatured")
    private boolean isFeatured;

    @Column(name = "is_verified", nullable = false)
    @JsonProperty("isVerified")
    private boolean isVerified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_time")
    private String openTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closing_time")
    private String closingTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @ElementCollection
    private List<String> tags;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Dish> dishes;
}
