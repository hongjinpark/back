package com.example.back.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MainProductDto {

    private Long product_id;
    private String pdTitle; //상품 제목
    private String pdContents; //상품 내용
    private String pdCategory; //상품 카테고리
    private String imgUrl; //이미지 경로
    private String price; //가격

    @QueryProjection
    public MainProductDto(Long product_id, String pdTitle, String pdContents, String pdCategory, String imgUrl, String price) {
        this.product_id = product_id;
        this.pdTitle = pdTitle;
        this.pdContents = pdContents;
        this.pdCategory = pdCategory;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
