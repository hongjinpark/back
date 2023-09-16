package com.example.back.dto;

import com.example.back.entity.Product;
import com.example.back.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseHistoryDto {

    private Long id;

    private User user; // 구매 유저

    private Product product; // 구매 상품

}
