package com.example.back.entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 사용자 정보 테이블
 */

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N: 탈퇴한 유저 Y: 존재하는 유저

    @Column(nullable = false)
    private String phoneNum; //전화번호

    @Column(nullable = false)
    private String usrNickName; //닉네임

    @Column(nullable = false)
    private String imgUrl; //프로필 이미지
}