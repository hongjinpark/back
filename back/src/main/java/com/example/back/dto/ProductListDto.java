package com.example.back.dto;

import com.example.back.entity.Product;
import com.example.back.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListDto {

    private Long userId;

    private String pdTitle;

    private String pdContents;

    private String pdCategory;

    private String price;

    private String status;

    private String hideStatus;

    public ProductListDto(Product product, User user) {
        this.userId = user.getId();
        this.pdTitle = product.getPdTitle();
        this.pdContents = product.getPdContents();
        this.pdCategory = product.getPdCategory();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.hideStatus = product.getHideStatus();
    }
}
