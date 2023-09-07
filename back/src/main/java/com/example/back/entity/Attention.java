package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "attention")
@Getter
@NoArgsConstructor
public class Attention extends BaseEntity{ // 상품 관심

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attention_id")
    private long id;

    @Column(name = "status", nullable = false)
    private String status; // N : 관심 비활성화, Y : 관심 활성화

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 관심 누른 사용자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 관심 상품
}
