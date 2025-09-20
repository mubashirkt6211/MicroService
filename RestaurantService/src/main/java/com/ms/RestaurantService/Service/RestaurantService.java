package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.RestaurantRequest;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.RestaurantException;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(RestaurantRequest request);
    Restaurant updateRestaurant(Long id, RestaurantRequest request) throws RestaurantException;
    void deleteRestaurant(Long id) throws RestaurantException;
    Restaurant getRestaurantById(Long id) throws RestaurantException;
    List<Restaurant> getAllRestaurants();

}
