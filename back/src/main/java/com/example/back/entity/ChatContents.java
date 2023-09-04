package com.example.back.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 채팅내역 테이블
 */

@Entity
@Table(name = "chat_contents")
@NoArgsConstructor
@Getter
public class ChatContents extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_contents_id")
    private Long id;

    @Column(nullable = false)
    private String contents; //내용

    @Column(nullable = false)
    private String contentCheckStatus; //N : 읽지 않음 Y : 읽음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
