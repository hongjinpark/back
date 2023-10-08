package com.example.back.dto;

import com.example.back.config.auth.PrincipalDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseHistoryDto {

    private Long id;

    private Long productId; // product_id

    private Long userId; // user_id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(PrincipalDetail principalDetail) {
        this.userId = principalDetail.getId();
    }

}
