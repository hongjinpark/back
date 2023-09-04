package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board_sympathy")
@Getter
@NoArgsConstructor
public class BoardSympathy extends BaseEntity{ // 게시판 공감
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private long id;
    @Column(name = "status", nullable = false, columnDefinition = "N")
    private String status; // N : 취소, A ~ F : 공감

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 공감 누른 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // 게시판 게시글
}
