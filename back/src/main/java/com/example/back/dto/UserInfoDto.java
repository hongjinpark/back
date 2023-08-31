package com.example.back.dto;

import com.example.back.entity.UserInfoEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String password;
    private String nickname;
    private String status;

    public UserInfoDto(UserInfoEntity userInfoEntity) {
        this.email = userInfoEntity.getEmail();
        this.password = userInfoEntity.getPassword();
        this.nickname = userInfoEntity.getNickname();
        this.status = userInfoEntity.getStatus();
    }
}
