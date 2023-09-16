package com.example.back.service;

import com.example.back.dto.ProductDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Region region = regionRepository.findById(productDto.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found with ID : " + productDto.getRegionId()));

        User user = userRepository.findById(productDto.getUserId())
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

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));

        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(@PathVariable Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));

        product.setPdTitle(productDetails.getPdTitle());
        product.setPdContents(productDetails.getPdContents());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));

        productRepository.delete(product);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
