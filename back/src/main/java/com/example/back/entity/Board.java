package com.example.back.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 게시판 테이블
 */

@Entity
@Table(name = "board")
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region; //게시판 등록 지역

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Builder
    public Board(Long id, String status, String bdSubject, String bdContents, User user, Integer viewCnt) {
        this.id = id;
        this.status = status;
        this.bdSubject = bdSubject;
        this.bdContents = bdContents;
        this.user = user;
        this.viewCnt = viewCnt;
    }
}
