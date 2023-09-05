package com.example.back.dto;

import com.example.back.entity.Attention;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AttentionDto {
    private String status; // N : 관심 비활성화, Y : 관심 활성화
    private long userId; // 관심 누른 사용자
    private long productId; // 관심 상품

    @Builder
    public AttentionDto(String status, long userId, long productId) {
        this.status = status;
        this.userId = userId;
        this.productId = productId;
    }
}
