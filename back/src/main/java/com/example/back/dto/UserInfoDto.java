package com.example.back.dto;

import com.example.back.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String password;
    private String nickname;
    private String status;

    public UserInfoDto(UserInfo userInfoEntity) {
        this.email = userInfoEntity.getEmail();
        this.password = userInfoEntity.getPassword();
        this.nickname = userInfoEntity.getNickname();
        this.status = userInfoEntity.getStatus();
    }
}
