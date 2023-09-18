package com.example.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnore
    private Region region; //게시판 등록 지역

    @Builder
    public Board(String status, String bdSubject, String bdContents, User user, Region region) {
        this.status = status;
        this.bdSubject = bdSubject;
        this.bdContents = bdContents;
        this.user = user;
        this.region = region;
    }
}
