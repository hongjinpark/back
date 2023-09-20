package com.example.back.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class RequestProduct {
    @NotEmpty(message = "상품 제목은 필수 입력 값 입니다.")
    private String pdTitle; //상품 제목

    @NotEmpty(message = "상품 내용은 필수 입력 값 입니다.")
    private String pdContents; //상품 내용

    //보류
    private String pdCategory; //카테고리 별 상품 분류

    @NotEmpty(message = "상품 가격은 필수 입력 값 입니다.")
    private String price; //상품 가격

    private String status;

    private List<RequestProductImg> images;
    //기본값 : N
    private String hideStatus; //판매 완료된 상품 중 N: 숨기지 않음 Y: 숨김

    private Long userId;

    private String region;
}
