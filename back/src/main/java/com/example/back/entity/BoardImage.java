package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board_image")
@Getter
@NoArgsConstructor
public class BoardImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bdimage_id")
    private long id;

    private String bdImgUrl; // 게시판 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
