package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "select_product")
@Getter
@NoArgsConstructor
public class SelectProduct extends BaseEntity{ // 상품 조회

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "select_product_id")
    private long id;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 조회한 유저

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 조회한 상품

}
