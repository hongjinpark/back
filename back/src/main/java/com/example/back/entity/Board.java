package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 게시판 테이블
 */

@Entity
@Table(name = "board")
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String status; //기본값 : N

    @Column(nullable = false)
    private String bdSubject; //게시글 주제

    @Column(nullable = false)
    private String bdContents; //게시글 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region; //게시판 등록 지역
}
