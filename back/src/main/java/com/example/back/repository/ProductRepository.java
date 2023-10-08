package com.example.back.repository;

import com.example.back.entity.Product;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByRegionAndUser(Region region, User user);
    Optional<List<Product>> findAllByUser(User user);

    @Query("SELECT p FROM Product p" +
            " JOIN FETCH p.region r" +
            " JOIN FETCH p.user u" +
            " LEFT JOIN FETCH p.productImages pi" +
            " WHERE r.regionName LIKE %:regionName%")
    Slice<Product> findByRegion_RegionNameContainsOrderByRegTimeDesc(String regionName, Pageable pageable);
    Optional<List<Product>> findByRegion_RegionNameContains(String regionName);

}
