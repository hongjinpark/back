package com.example.back.jpa.service;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.*;
import com.example.back.entity.Product;
import com.example.back.entity.ProductImage;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import com.example.back.entity.*;
import com.example.back.mybatis.mapper.PurchaseHistoryMapper;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.RegionRepository;
import com.example.back.repository.SelectProductRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    private final SelectProductRepository selectProductRepository;
    private final PurchaseHistoryMapper purchaseHistoryMapper;

    @Transactional
    public ProductDto createProduct(ProductDto productDto, PrincipalDetail principalDetail) {
        Region region = regionRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found with ID : " + productDto.getRegionId()));

        User user = userRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with ID : " + productDto.getUserId()));

        Product product = Product.builder()
                .user(user)
                .region(region)
                .status(productDto.getStatus())
                .pdTitle(productDto.getPdTitle())
                .pdContents(productDto.getPdContents())
                .pdCategory(productDto.getPdCategory())
                .price(productDto.getPrice())
                .hideStatus(productDto.getHideStatus())
                .build();

        productRepository.save(product);

        return new ProductDto(product, region, user);
    }

    @Transactional
    public List<ProductListDto> getProductList() {

        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(ProductListDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<ProductListDto> getProductListById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not exist with ID : " + id));

        List<Product> productList = productRepository.findAllByUser(user).orElse(Collections.emptyList());

        return productList.stream()
                .map(product -> new ProductListDto(product, user))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductListDto updateProduct(@PathVariable Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));

        product.setPdTitle(productDetails.getPdTitle());
        product.setPdContents(productDetails.getPdContents());
        product.setPrice(productDetails.getPrice());
        product.setHideStatus(productDetails.getHideStatus());



        Product updatedProduct = productRepository.save(product);
        return new ProductListDto(updatedProduct, product.getUser());
    }

    @Transactional
    public void deleteProduct(Long pdId) {

        productRepository.deleteById(pdId);
    }


    public void saveTestProduct(RequestProduct requestProduct) {
        User user = userRepository.findById(requestProduct.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID : " + requestProduct.getUserId()));

        Region region = regionRepository.findByUserAndAndRegionName(user, requestProduct.getRegion())
                .orElseThrow(() -> new IllegalArgumentException("Region not found with RegionName : " + requestProduct.getRegion()));


        Product product = Product.builder()
                .pdTitle(requestProduct.getPdTitle())
                .pdContents(requestProduct.getPdContents())
                .pdCategory(requestProduct.getPdCategory())
                .price(requestProduct.getPrice())
                .status(requestProduct.getStatus())
                .hideStatus(requestProduct.getHideStatus())
                .user(user)
                .region(region)
                .build();
        for (RequestProductImg productImg : requestProduct.getImages()) {
            ProductImage productImage = new ProductImage(productImg.getUrl(), product);
            product.getProductImages().add(productImage);
        }
        productRepository.save(product);
    }

    @Transactional
    public ProductListDto updateStatus(Product productDetails, PrincipalDetail principalDetail) {
        Product product = productRepository.findById(productDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + productDetails.getId()));

        User user = userRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with ID : " + principalDetail.getId()));

        product.setStatus(productDetails.getStatus());

        //createPurchaseHistory 이거 불러오기 추가(status : C일 떄 생성하는 쿼리)

        PurchaseHistoryDto purchaseHistoryDto = PurchaseHistoryDto.builder()
                .productId(product.getId())
                .userId(user.getId())
                .build();

        if(productDetails.getStatus().equals("C")) {
            purchaseHistoryMapper.createPurchaseHistory(purchaseHistoryDto, user.getId());
        }


        Product updateStatus = productRepository.save(product);
        return new ProductListDto(updateStatus, product.getUser());

    }

    @Transactional
    public Slice<ResponseProduct> getProductListByRegionName(String regionName, Pageable pageable){
        // ProductSlice 조회
        Slice<Product> productList = productRepository.findByRegion_RegionNameContainsOrderByRegTimeDesc(regionName, pageable);
        // ResponeProduct로 변환
        Slice<ResponseProduct> responseProductSlice = productList.map(ResponseProduct::new);
        // ProductList 별 조회수 조회
        List<Object[]> objects = productRepository.findProductsAndCount(productList.toList());
        for(int i=0; i<objects.size(); i++){
            ResponseProduct responseProduct = responseProductSlice.toList().get(i);
            responseProduct.setSelectedCount((Long) objects.get(i)[1]);
        }
        return responseProductSlice;
    }

    @Transactional
    public ResponseProduct  getProductById(Long id, PrincipalDetail principalDetail) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not exist with id :" + id));
        // 로그인 되어 있을 때 SelectProduct 추가
        if(principalDetail != null){
            User user = userRepository.findById(principalDetail.getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID : " + principalDetail.getId()));

            SelectProduct selectProduct = selectProductRepository
                    .findByUser_IdAndProduct_Id(principalDetail.getId(), product.getId())
                    .orElse(new SelectProduct("Y",  user, product));
            selectProductRepository.save(selectProduct);
        }

        return new ResponseProduct(product);
    }
}
