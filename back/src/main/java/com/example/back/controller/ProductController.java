package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.ProductDto;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/new")
    public ResponseEntity createProduct(@RequestBody ProductDto productDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        ProductDto pdDto = productService.createProduct(productDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/lists") //Product 조회
    public List<Product> listAllProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{id}") //id에 따른 상품 조회
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}") //id에 따른 board 수정
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}") //board 삭제
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
