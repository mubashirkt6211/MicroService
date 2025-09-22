package com.ms.RestaurantService.Service;

import com.ms.RestaurantService.Dto.RestaurantRequest;
import com.ms.RestaurantService.Dto.RestaurantResponse;
import com.ms.RestaurantService.Entity.Restaurant;
import com.ms.RestaurantService.Exception.RestaurantException;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse createRestaurant(RestaurantRequest request);

    RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) throws RestaurantException;

    void deleteRestaurant(Long id) throws RestaurantException;
    RestaurantResponse getRestaurantById(Long id) throws RestaurantException;

    List<RestaurantResponse> getAllRestaurants();

}
