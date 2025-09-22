package com.ms.RestaurantService.Repository;

import com.ms.RestaurantService.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> getCategoryById(Long id);

    List<Category> findByNameContainingIgnoreCase(String keyword);

    List<Category> findByRestaurantId(Long id);
}
