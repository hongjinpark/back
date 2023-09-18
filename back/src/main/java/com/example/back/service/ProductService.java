package com.example.back.service;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.ProductDto;
import com.example.back.dto.ProductListDto;
import com.example.back.entity.Product;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.RegionRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProductDto createProduct(ProductDto productDto, PrincipalDetail principalDetail) {
        Region region = regionRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found with ID : " + productDto.getRegionId()));

        User user = userRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with ID : " + productDto.getUserId()));

        Product product = productRepository.findByRegionAndUser(region, user)
                .orElse(Product.builder()
                        .user(user)
                        .region(region)
                        .pdTitle(productDto.getPdTitle())
                        .pdContents(productDto.getPdContents())
                        .pdCategory(productDto.getPdCategory())
                        .price(productDto.getPrice())
                        .status(productDto.getStatus())
                        .hideStatus(productDto.getHideStatus())
                        .build());

        product.setStatus(productDto.getStatus());

        productRepository.save(product);

        return new ProductDto(product, region, user);
    }

    @Transactional
    public List<ProductListDto> getProductById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not exist with ID : " + id));

        List<Product> productList = productRepository.findAllByUser(user).orElse(Collections.emptyList());

        return productList.stream()
                .map(product -> new ProductListDto(product, user))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));

        product.setPdTitle(productDetails.getPdTitle());
        product.setPdContents(productDetails.getPdContents());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long pdId) {

        productRepository.deleteById(pdId);
    }
}
