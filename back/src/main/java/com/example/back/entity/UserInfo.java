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
@Builder
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long id;

    @Column
    private String status; //N: 탈퇴한 유저 Y: 존재하는 유저

    @Column
    private String phoneNum; //전화번호

    @Column(nullable = false)
    private String usrNickName; //닉네임

    private String imgUrl; //프로필 이미지

    private String imgName; //프로필 이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    @Builder
    public UserInfo(String status, String phoneNum, String usrNickName, String imgUrl) {
        this.status = status;
        this.phoneNum = phoneNum;
        this.usrNickName = usrNickName;
        this.imgUrl = imgUrl;
    }

    public void updateUserInfo(String phoneNum, String usrNickName) {
        this.phoneNum = phoneNum;
        this.usrNickName = usrNickName;
    }

    public void updateProfileImg(String oriImgName, String imgName, String imgUrl) {

        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}