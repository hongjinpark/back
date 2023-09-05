package com.example.back.entity;
import lombok.*;

import javax.persistence.*;


/**
 * 사용자 정보 테이블
 */

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N: 탈퇴한 유저 Y: 존재하는 유저

    @Column
    private String phoneNum; //전화번호

    @Column(nullable = false)
    private String usrNickName; //닉네임

    @Column
    private String imgUrl; //프로필 이미지

    @Builder
    public UserInfo(String status, String phoneNum, String usrNickName, String imgUrl) {
        this.status = status;
        this.phoneNum = phoneNum;
        this.usrNickName = usrNickName;
        this.imgUrl = imgUrl;
    }
}