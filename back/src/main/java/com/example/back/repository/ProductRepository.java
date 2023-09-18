package com.example.back.repository;

import com.example.back.entity.Product;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByRegionAndUser(Region region, User user);

    Optional<List<Product>> findAllByUser(User user);
}
