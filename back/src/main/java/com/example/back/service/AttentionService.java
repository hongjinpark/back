package com.example.back.service;

import com.example.back.dto.AttentionDto;
import com.example.back.entity.Attention;
import com.example.back.entity.Product;
import com.example.back.entity.User;
import com.example.back.repository.AttentionRepository;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttentionService {

    private final AttentionRepository attentionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //Attention 삽입, 수정 메소드
    @Transactional
    public AttentionDto saveAndUpdateAttention(AttentionDto attentionDto) {
        //userId로 UserEntity 조회, 없으면 예외처리
        User user = userRepository.findById(attentionDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + attentionDto.getUserId()));
        //productId로 ProductEntity 조회, 없으면 예외처리
        Product product = productRepository.findById(attentionDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + attentionDto.getProductId()));
        //userEntity, productEntity로 AttentionEntity 조회, 없으면 생성
        Attention attention = attentionRepository.findByUserAndProduct(user, product)
                .orElse(Attention.builder()
                        .user(user)
                        .product(product)
                        .build());
        //AttentionEntity의 status 수정
        attention.setStatus(attention.getStatus());
        //AttentionEntity 저장
        attentionRepository.save(attention);

        return AttentionDto.builder()
                .status(attention.getStatus())
                .userId(user.getId())
                .productId(product.getId()).build();
    }
}
