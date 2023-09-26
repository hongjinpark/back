package com.example.back.dto;

import com.example.back.entity.Product;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ProductDto {

    @NotEmpty(message = "상품 제목은 필수 입력 값 입니다.")
    private String pdTitle; //상품 제목

    @NotEmpty(message = "상품 내용은 필수 입력 값 입니다.")
    private String pdContents; //상품 내용

    //보류
    private String pdCategory; //카테고리 별 상품 분류

    @NotEmpty(message = "상품 가격은 필수 입력 값 입니다.")
    private String price; //상품 가격

    private String status; //기본값 : N

    private String hideStatus; //판매 완료된 상품 중 N: 숨기지 않음 Y: 숨김

    private Long userId;

    private Long regionId;

    public ProductDto(Product product, Region region, User user) {
        this.userId = user.getId();
        this.regionId = region.getId();
        this.pdTitle = product.getPdTitle();
        this.pdContents = product.getPdContents();
        this.pdCategory = product.getPdCategory();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.hideStatus = product.getHideStatus();
    }
}
