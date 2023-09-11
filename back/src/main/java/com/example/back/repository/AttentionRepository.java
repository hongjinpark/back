package com.example.back.repository;

import com.example.back.entity.Attention;
import com.example.back.entity.Product;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long> {
    Optional<Attention> findByUserAndProduct(User user, Product product);

    Optional<List<Attention>> findAllByUser(User user);
}
