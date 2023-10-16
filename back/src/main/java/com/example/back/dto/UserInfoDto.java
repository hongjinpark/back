package com.example.back.dto;

import com.example.back.constant.Role;
import com.example.back.entity.User;
import com.example.back.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {

    private Long id;

    private String phoneNum; //전화번호

    private String usrNickName; //닉네임

    private String imgUrl; //프로필 이미지

    public UserInfoDto(String phoneNum, String usrNickName, String imgUrl) {
        this.phoneNum = phoneNum;
        this.usrNickName = usrNickName;
        this.imgUrl = imgUrl;
    }

    public UserInfo toEntity() {
        return UserInfo.builder()
                .phoneNum(phoneNum)
                .usrNickName(usrNickName)
                .status("Y")
                .build();
    }
}
