package com.example.back.entity;

import javax.persistence.*;

@Entity
@Table(name = "attention")
public class Attention extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attention_id")
    private long id;
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne()
    @JoinColumn(name = "product_id")
    private Product product;
}
