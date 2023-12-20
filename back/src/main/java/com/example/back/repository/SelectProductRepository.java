package com.example.back.repository;

import com.example.back.entity.SelectProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectProductRepository extends JpaRepository<SelectProduct, Long>, SelectProductCustomRepository {
    Optional<SelectProduct> findByUser_IdAndProduct_Id(Long userId, Long productId);

}
