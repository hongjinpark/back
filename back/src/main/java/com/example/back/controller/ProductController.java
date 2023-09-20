package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.ProductDto;
import com.example.back.dto.RequestProduct;
import com.example.back.dto.RequestProductImg;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @PostMapping("/testnew")
    public ResponseEntity<String> PostTest(@ModelAttribute("pdTitle") String pdTitle
            ,@ModelAttribute("pdContents") String pdContents
            ,@ModelAttribute("price") String price
            ,@ModelAttribute("images") List<MultipartFile> images
            ,@ModelAttribute("status") String status
            ,@ModelAttribute("pdCategory") String pdCategory
            ,@ModelAttribute("pdHideStatus") String pdHideStatus
            ,@ModelAttribute("region") String region
            ,@AuthenticationPrincipal PrincipalDetail principalDetail){

        RequestProduct requestProduct = RequestProduct.builder()
                .pdTitle(pdTitle)
                .pdContents(pdContents)
                .status(status)
                .hideStatus(pdHideStatus)
                .pdCategory(pdCategory)
                .userId(principalDetail.getId())
                .region(region)
                .images(new ArrayList<>())
                .price(price)
                .build();
        try {
            for(MultipartFile multipartFile: images){
                // Get the file bytes
                byte[] bytes = multipartFile.getBytes();

                // Set the file path
                String staticPath = Paths.get("src/main/resources").toString();
                String filePath = staticPath + File.separator + "images" + File.separator + multipartFile.getOriginalFilename();
                System.out.println(filePath);
                // Ensure the directory structure exists
                Path path = Paths.get(filePath);
                Files.createDirectories(path.getParent());

                // Save the file
                File newFile = new File(filePath);
                newFile.createNewFile();
                FileUtils.writeByteArrayToFile(newFile, bytes);
                requestProduct.getImages().add(new RequestProductImg(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
      productService.saveTestProduct(requestProduct);
        return ResponseEntity.ok("S");
    }
}
