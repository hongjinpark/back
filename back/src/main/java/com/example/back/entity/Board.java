package com.example.back.entity;

import com.example.back.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImage> boardImages = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Board(String status, String bdSubject, String bdContents, User user, Region region) {
        this.status = status;
        this.bdSubject = bdSubject;
        this.bdContents = bdContents;
        this.user = user;
        this.region = region;
    }

    public void updateBoard(BoardDto boardDto) {
        this.status = boardDto.getStatus();
        this.bdSubject = boardDto.getBdSubject();
        this.bdContents = boardDto.getBdContents();
    }
}
