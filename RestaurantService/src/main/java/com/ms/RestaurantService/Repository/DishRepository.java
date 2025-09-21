package com.ms.RestaurantService.Repository;

import com.ms.RestaurantService.Entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

    Page<Dish> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Dish> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
