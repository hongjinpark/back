package com.example.back.entity;

import javax.persistence.*;

@Entity
@Table(name = "board_image")
public class BoardImage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bdimage_id")
    private long id;

    private String bdImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
