package com.example.back.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 댓글과 대댓글(답글) 테이블
 */

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N : 댓글 없음 Y : 댓글 있음

    @Column(nullable = false)
    private String contents; //내용

    @Column(nullable = false)
    private String cmlayer; //0 : 댓글 1 : 대댓글(답글)

    @Column(nullable = false)
    private String commentCroup; //댓글 그룹. 0 : 1번째 댓글 1 : 2번째 댓글

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
