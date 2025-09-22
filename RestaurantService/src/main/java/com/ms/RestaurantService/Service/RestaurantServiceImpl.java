package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.*;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.RestaurantException;
import com.ms.RestaurantService.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = mapToRestaurant(request);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return mapToResponse(savedRestaurant, new ArrayList<>());
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));
        return mapToResponse(restaurant, new ArrayList<>());
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        List<RestaurantResponse> responses = new ArrayList<>();
        for (Restaurant r : restaurantRepository.findAll()) {
            responses.add(mapToResponse(r, new ArrayList<>()));
        }
        return responses;
    }

    @Override
    @Transactional
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));
        updateRestaurantEntity(restaurant, request);
        restaurantRepository.save(restaurant);
        return mapToResponse(restaurant, new ArrayList<>());
    }

    @Override
    public void deleteRestaurant(Long id) throws RestaurantException {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
    }

    private Restaurant mapToRestaurant(RestaurantRequest request) {
        Restaurant r = new Restaurant();
        r.setName(request.getName());
        r.setDescription(request.getDescription());
        r.setCuisineType(request.getCuisineType());
        r.setLocation(request.getLocation());
        r.setOpen(request.isOpen());
        r.setFeatured(request.isFeatured());
        r.setVerified(request.isVerified());
        r.setDeliveryFee(request.getDeliveryFee());
        r.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        r.setImageUrl(request.getImageUrl());
        r.setLogoUrl(request.getLogoUrl());
        r.setPhoneNumber(request.getPhoneNumber());
        r.setEmail(request.getEmail());
        r.setInstagramUrl(request.getInstagramUrl());
        r.setFacebookUrl(request.getFacebookUrl());
        r.setOpenTime(request.getOpenTime());
        r.setClosingTime(request.getClosingTime());
        r.setTags(request.getTags());
        r.setCategories(new ArrayList<>()); // categories added later
        return r;
    }

    private void updateRestaurantEntity(Restaurant r, RestaurantRequest request) {
        r.setName(request.getName());
        r.setDescription(request.getDescription());
        r.setCuisineType(request.getCuisineType());
        r.setLocation(request.getLocation());
        r.setOpen(request.isOpen());
        r.setFeatured(request.isFeatured());
        r.setVerified(request.isVerified());
        r.setDeliveryFee(request.getDeliveryFee());
        r.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        r.setImageUrl(request.getImageUrl());
        r.setLogoUrl(request.getLogoUrl());
        r.setPhoneNumber(request.getPhoneNumber());
        r.setEmail(request.getEmail());
        r.setInstagramUrl(request.getInstagramUrl());
        r.setFacebookUrl(request.getFacebookUrl());
        r.setOpenTime(request.getOpenTime());
        r.setClosingTime(request.getClosingTime());
        r.setTags(request.getTags());
    }

    public RestaurantResponse mapToResponse(Restaurant restaurant, List<CategoryResponse> categoryResponses) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setDescription(restaurant.getDescription());
        response.setCuisineType(restaurant.getCuisineType());
        response.setLocation(restaurant.getLocation());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setEmail(restaurant.getEmail());
        response.setInstagramUrl(restaurant.getInstagramUrl());
        response.setFacebookUrl(restaurant.getFacebookUrl());
        response.setImageUrl(restaurant.getImageUrl());
        response.setLogoUrl(restaurant.getLogoUrl());
        response.setOpen(restaurant.isOpen());
        response.setFeatured(restaurant.isFeatured());
        response.setVerified(restaurant.isVerified());
        response.setDeliveryFee(restaurant.getDeliveryFee());
        response.setEstimatedDeliveryTime(restaurant.getEstimatedDeliveryTime());
        response.setTags(restaurant.getTags());
        response.setCategories(categoryResponses);
        return response;
    }
}
