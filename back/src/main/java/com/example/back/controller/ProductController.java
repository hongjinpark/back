package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.ProductDto;
import com.example.back.dto.ProductListDto;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/lists") //user에 따른 상품 조회
    public ResponseEntity<List<ProductListDto>> getProductById(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        List<ProductListDto> pdDtoList = productService.getProductById(principalDetail.getId());

        return ResponseEntity.ok(pdDtoList);
    }


    @PutMapping("/lists/{pdId}") //상품 수정
    public ResponseEntity<Product> updateProduct(@PathVariable Long pdId, @RequestBody Product productDetails) {

        return productService.updateProduct(pdId, productDetails);
    }

    @DeleteMapping("/lists/{pdId}") //상품 삭제
    public ResponseEntity<Object> deleteProduct(@PathVariable Long pdId) {

        productService.deleteProduct(pdId);

        return ResponseEntity.ok().build();
    }
}
