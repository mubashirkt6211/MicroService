package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.CategoryRequest;
import com.ms.RestaurantService.Dto.DishRequest;
import com.ms.RestaurantService.Dto.RestaurantRequest;
import com.ms.RestaurantService.Entity.Category;
import com.ms.RestaurantService.Entity.Dish;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Repository.CategoryRepository;
import com.ms.RestaurantService.Repository.DishRepository;
import com.ms.RestaurantService.Repository.RestaurantRepository;
import com.ms.RestaurantService.Service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public Restaurant createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = mapToRestaurant(request);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        if (request.getCategories() != null) {
            for (CategoryRequest catReq : request.getCategories()) {
                Category category = new Category();
                category.setName(catReq.getName());
                category.setImageUrl(catReq.getImageUrl());
                category.setRestaurant(savedRestaurant);
                Category savedCategory = categoryRepository.save(category);

                if (catReq.getDishes() != null) {
                    for (DishRequest dishReq : catReq.getDishes()) {
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
                        dish.setCategory(savedCategory);
                        dishRepository.save(dish);
                    }
                }
            }
        }

        return savedRestaurant;
    }
    @Override
    @Transactional
    public Restaurant updateRestaurant(Long id, RestaurantRequest request) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));

        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setLocation(request.getLocation());
        restaurant.setOpenTime(request.getOpenTime());
        restaurant.setClosingTime(request.getClosingTime());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setEmail(request.getEmail());
        restaurant.setInstagram_url(request.getInstagram_url());
        restaurant.setFacebook_url(request.getFacebook_url());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setOpen(request.isOpen());
        restaurant.setFeatured(request.isFeatured());
        restaurant.setVerified(request.isVerified());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        restaurant.setTags(request.getTags());

        List<Category> updatedCategories = new ArrayList<>();
        if (request.getCategories() != null) {
            for (CategoryRequest catReq : request.getCategories()) {
                Category category;

                category = restaurant.getCategories().stream()
                        .filter(c -> c.getName().equalsIgnoreCase(catReq.getName()))
                        .findFirst()
                        .orElse(new Category());

                category.setName(catReq.getName());
                category.setImageUrl(catReq.getImageUrl());
                category.setRestaurant(restaurant);
                Category savedCategory = categoryRepository.save(category);

                List<Dish> updatedDishes = new ArrayList<>();
                if (catReq.getDishes() != null) {
                    for (DishRequest dishReq : catReq.getDishes()) {
                        Dish dish;

                        dish = savedCategory.getDishes().stream()
                                .filter(d -> d.getName().equalsIgnoreCase(dishReq.getName()))
                                .findFirst()
                                .orElse(new Dish());

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
                        dish.setCategory(savedCategory);

                        updatedDishes.add(dishRepository.save(dish));
                    }
                }
                savedCategory.setDishes(updatedDishes);
                updatedCategories.add(savedCategory);
            }
        }
        restaurant.setCategories(updatedCategories);
        return restaurantRepository.save(restaurant);
    }


    @Override
    public void deleteRestaurant(Long id) throws RestaurantException {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getRestaurantById(Long id) throws RestaurantException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));
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
        restaurant.setOpenTime(request.getOpenTime());
        restaurant.setClosingTime(request.getClosingTime());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setEmail(request.getEmail());
        restaurant.setInstagram_url(request.getInstagram_url());
        restaurant.setFacebook_url(request.getFacebook_url());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setOpen(request.isOpen());
        restaurant.setFeatured(request.isFeatured());
        restaurant.setVerified(request.isVerified());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        restaurant.setTags(request.getTags());
        return restaurant;
    }
}
