package com.example.back.dto;

import com.example.back.entity.Product;
import com.example.back.entity.ProductImage;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseProduct {

    @NotEmpty(message = "상품 제목은 필수 입력 값 입니다.")
    private String pdTitle; //상품 제목

    @NotEmpty(message = "상품 내용은 필수 입력 값 입니다.")
    private String pdContents; //상품 내용

    //보류
    private String pdCategory; //카테고리 별 상품 분류

    @NotEmpty(message = "상품 가격은 필수 입력 값 입니다.")
    private String price; //상품 가격

    private String status;

    private List<ResponseProductImg> images;
    //기본값 : N
    private String hideStatus; //판매 완료된 상품 중 N: 숨기지 않음 Y: 숨김

    private UserDto user;

    private RegionDto region;

    public ResponseProduct(Product product){
        this.pdTitle = product.getPdTitle();
        this.pdContents = product.getPdContents();
        this.pdCategory = product.getPdCategory();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.images = product.getProductImages().stream()
                .map(productImage -> new ResponseProductImg(productImage.getImgUrl())).collect(Collectors.toList());
        this.hideStatus = product.getHideStatus();
        this.user = new UserDto(product.getUser());
        this.region = new RegionDto(product.getRegion());
    }
}
