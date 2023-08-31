package com.example.back.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdimage_id")
    private long id;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
