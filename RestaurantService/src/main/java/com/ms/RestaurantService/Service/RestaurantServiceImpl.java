package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.RestaurantRequest;
import com.ms.RestaurantService.Dto.CategoryRequest;
import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = mapToRestaurant(request);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long id, RestaurantRequest request) throws RestaurantException {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found"));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setCuisineType(request.getCuisineType());
        existing.setLocation(request.getLocation());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setEmail(request.getEmail());
        existing.setImageUrl(request.getImageUrl());
        existing.setLogoUrl(request.getLogoUrl());
        existing.setInstagram_url(request.getInstagramUrl());
        existing.setFacebook_url(request.getFacebookUrl());
        existing.setOpen(request.isOpen());
        existing.setFeatured(request.isFeatured());
        existing.setVerified(request.isVerified());
        existing.setDeliveryFee(request.getDeliveryFee());
        existing.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        existing.setTags(request.getTags());

        List<Category> categories = new ArrayList<>();
        if (request.getCategories() != null) {
            for (CategoryRequest categoryReq : request.getCategories()) {
                Category category = new Category();
                category.setName(categoryReq.getName());
                category.setImageUrl(categoryReq.getImageUrl());
                category.setRestaurant(existing);

                List<Dish> dishes = new ArrayList<>();
                if (categoryReq.getDishes() != null) {
                    for (DishRequest dishReq : categoryReq.getDishes()) {
                        Dish dish = new Dish();
                        dish.setName(dishReq.getName());
                        dish.setDescription(dishReq.getDescription());
                        dish.setPrice(dishReq.getPrice());
                        dish.setAvailable(dishReq.isAvailable());
                        dish.setVeg(dishReq.isVeg());
                        dish.setSpicy(dishReq.isSpicy());
                        dish.setImageUrl(dishReq.getImageUrl());
                        dish.setIngredients(dishReq.getIngredients());
                        dish.setTags(dishReq.getTags());
                        dish.setPreparationTime(dishReq.getPreparationTime());
                        dish.setCategory(category);
                        dishes.add(dish);
                    }
                }
                category.setDishes(dishes);
                categories.add(category);
            }
        }
        existing.setCategories(categories);

        return restaurantRepository.save(existing);
    }

    @Override
    public void deleteRestaurant(Long id) throws RestaurantException {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getRestaurantById(Long id) throws RestaurantException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found"));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    private Restaurant mapToRestaurant(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setLocation(request.getLocation());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setEmail(request.getEmail());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setInstagram_url(request.getInstagramUrl());
        restaurant.setFacebook_url(request.getFacebookUrl());
        restaurant.setOpen(request.isOpen());
        restaurant.setFeatured(request.isFeatured());
        restaurant.setVerified(request.isVerified());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        restaurant.setTags(request.getTags());

        List<Category> categories = new ArrayList<>();
        if (request.getCategories() != null) {
            for (CategoryRequest categoryReq : request.getCategories()) {
                Category category = new Category();
                category.setName(categoryReq.getName());
                category.setImageUrl(categoryReq.getImageUrl());
                category.setRestaurant(restaurant);

                List<Dish> dishes = new ArrayList<>();
                if (categoryReq.getDishes() != null) {
                    for (DishRequest dishReq : categoryReq.getDishes()) {
                        Dish dish = new Dish();
                        dish.setName(dishReq.getName());
                        dish.setDescription(dishReq.getDescription());
                        dish.setPrice(dishReq.getPrice());
                        dish.setAvailable(dishReq.isAvailable());
                        dish.setVeg(dishReq.isVeg());
                        dish.setSpicy(dishReq.isSpicy());
                        dish.setImageUrl(dishReq.getImageUrl());
                        dish.setIngredients(dishReq.getIngredients());
                        dish.setTags(dishReq.getTags());
                        dish.setPreparationTime(dishReq.getPreparationTime());
                        dish.setCategory(category);
                        dishes.add(dish);
                    }
                }
                category.setDishes(dishes);
                categories.add(category);
            }
        }
        restaurant.setCategories(categories);
        return restaurant;
    }
}
