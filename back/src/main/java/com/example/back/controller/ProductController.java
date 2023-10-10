package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.*;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.internal.compiler.batch.ClasspathDirectory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/new")
    public ResponseEntity createProduct(@RequestBody ProductDto productDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        ProductDto pdDto = productService.createProduct(productDto, principalDetail);

        return ResponseEntity.ok(pdDto);
    }

    @GetMapping("/lists") //user에 따른 상품 조회
    public ResponseEntity<List<ProductListDto>> getProductListById(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        List<ProductListDto> pdDtoList = productService.getProductListById(principalDetail.getId());

        return ResponseEntity.ok(pdDtoList);
    }


    @PutMapping("/lists/{pdId}") //상품 수정
    public ResponseEntity<ProductListDto> updateProduct(@PathVariable Long pdId, @RequestBody Product productDetails) {

        ProductListDto productListDto = productService.updateProduct(pdId, productDetails);

        return ResponseEntity.ok(productListDto);
    }

    @DeleteMapping("/lists/{pdId}") //상품 삭제
    public ResponseEntity<Object> deleteProduct(@PathVariable Long pdId) {

        productService.deleteProduct(pdId);

        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/testnew") // Product 저장
    public ResponseEntity<String> PostTest(@RequestPart List<MultipartFile> images
            ,@RequestPart(name = "product") RequestProduct product
            ,@AuthenticationPrincipal PrincipalDetail principalDetail) {
        product.setUserId(principalDetail.getId());
        product.setHideStatus("N");
        product.setPdCategory("test");
        try {
            for (MultipartFile multipartFile : images) {
                // multipartFile: 이미지, byte[]로 저장
                byte[] bytes = multipartFile.getBytes();
                // 이미지 저장 주소
                ClassPathResource classPathResource = new ClassPathResource("back\\src\\main\\resources\\images");
                // 이미지 저장 주소 + 파일 이름
                String filePath =  classPathResource.getPath() + File.separator + multipartFile.getOriginalFilename();
                // 최종 주소
                Path path = Paths.get(filePath);
                Files.createDirectories(path.getParent());

                // 파일 저장
                File newFile = new File(filePath);
                newFile.createNewFile();
                FileUtils.writeByteArrayToFile(newFile, bytes);
                // requestProduct에 ProductImg 주소만 저장
                product.getImages().add(new RequestProductImg(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
        productService.saveTestProduct(product);
        return ResponseEntity.ok("S");
    }

    @PutMapping("/lists/pdStatus") //Status만 수정 (N: 상품 없음 C: 거래 완료 R: 예약중 Y: 상품 있음)
    public ResponseEntity<ProductListDto> updateStatus(@RequestBody Product productDetails) {
        ProductListDto productListDto = productService.updateStatus(productDetails);

        return ResponseEntity.ok(productListDto);

    }

    @GetMapping("/lists/{regionName}") //regionNamge을 포함하는 ProductList 조회
    public ResponseEntity<Slice<ResponseProduct>> getProductList(@PathVariable(value = "regionName") String regionName, @RequestParam(defaultValue = "0") int page) throws IOException {
        //Sort 정의 regTime, desc
        Sort sort = Sort.by(Sort.Direction.DESC, "regTime");
        // Pageable 정의 PageRequest.of(index, size, sort)
        Pageable pageable = PageRequest.of(page, 2, sort);
        Slice<ResponseProduct> responseProductList = productService.getProductListByRegionName(regionName, pageable);

        // ResponseProductList의 Image를 byte[]로 추가
        for(ResponseProduct responseProduct: responseProductList) {
            for(ResponseProductImg responseProductImg: responseProduct.getImages()) {
                ClassPathResource classPathResource = new ClassPathResource(responseProductImg.getUrl()
                        .replace("back/src/main/resources/", ""));
                // url을 통해 image파일 byte[]로 저장
                byte[] imageBytes = classPathResource.getInputStream().readAllBytes();
                responseProductImg.setData(imageBytes);
            }
        }
        return ResponseEntity.ok(responseProductList);
    }

    @GetMapping("/{pdId}") // pdId통해 Product 조회
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable Long pdId
            ,@AuthenticationPrincipal PrincipalDetail principalDetail) throws IOException {
        ResponseProduct responseProduct = productService.getProductById(pdId, principalDetail);
        // ResponseProduct의 Image를 byte[]로 추가
        for(ResponseProductImg responseProductImg: responseProduct.getImages()) {
            ClassPathResource classPathResource = new ClassPathResource(responseProductImg.getUrl()
                    .replace("back/src/main/resources/", ""));
            // url을 통해 image파일 byte[]로 저장
            byte[] imageBytes = classPathResource.getInputStream().readAllBytes();
            responseProductImg.setData(imageBytes);
        }
        return ResponseEntity.ok(responseProduct);
    }
}
