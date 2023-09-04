package com.example.back.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 공지 사항 테이블
 */

@Entity
@Table(name = "notice")
@NoArgsConstructor
@Getter
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N : 공지 없음 Y : 공지 있음

    @Column(nullable = false)
    private String noticeTitle; //제목

    @Column(nullable = false)
    private String noticeContents; //내용
}
