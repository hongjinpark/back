package com.example.back.entity;

import com.example.back.dto.UserInfoDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "UserInfo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfo_id")
    private long id;
    @Email
    private String email;
    private String password;
    private String nickname;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Builder
    public UserInfoEntity(String email, String password, String nickname, String status){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    public UserInfoEntity(UserInfoDto userInfoDto){
        this.email = userInfoDto.getEmail();
        this.password = userInfoDto.getPassword();
        this.nickname = userInfoDto.getNickname();
        this.status = userInfoDto.getStatus();
        this.createAt = new Date();
        this.updateAt = new Date();
    }
}
