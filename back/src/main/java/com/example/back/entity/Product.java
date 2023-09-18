package com.example.back.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 상품 테이블
 */

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N: 상품 없음 C: 거래 완료 R: 예약중 Y: 상품 있음

    @Column(nullable = false)
    private String pdTitle; //상품 제목

    @Column(nullable = false)
    private String pdContents; //상품 내용

    @Column(nullable = false)
    private String pdCategory; //카테고리 별 상품 분류

    @Column(nullable = false)
    private String price; //상품 가격

    @Column(nullable = false)
    private String hideStatus; //판매 완료된 상품 중 N: 숨기지 않음 Y: 숨김

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonIgnore
    private Region region;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Builder
    public Product(String status, String pdTitle, String pdContents, String pdCategory, String price, String hideStatus, Region region, User user) {
        this.status = status;
        this.pdTitle = pdTitle;
        this.pdContents = pdContents;
        this.pdCategory = pdCategory;
        this.price = price;
        this.hideStatus = hideStatus;
        this.region = region;
        this.user = user;
    }
}
