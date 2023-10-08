package com.example.back.dto;

import com.example.back.config.auth.PrincipalDetail;
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

//    private Long productId; // product_id / 재우

    //private Product product; // 구매 상품 / 이건 없어도됨

    private Long principalDetail; // 홍진

    public Long getUserId(PrincipalDetail principalDetail) { // 홍진
        return this.principalDetail = principalDetail.getId();
    }

}
