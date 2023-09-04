package com.example.back.entity;



import com.example.back.constant.Role;
import com.example.back.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 사용자 테이블
 * Region에서 user_id중 leadStatus가 Y인것을 조회하여 사용자별 대표지역을 알 수 있다
 */

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email; //이메일

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false)
    private String nickname; //닉네임

    @Column(nullable = false)
    private String status; //N: 없는 유저 Y: 존재하는 유저

    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    //일반 계정, 구글, 네이버, 카카오
    private String provider;
    // oauth2를 이용할 경우 아이디값
    private String providerId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String password, String nickname, String status, String provider, String providerId, Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.provider = provider;
        this.providerId = providerId;
        this.role = Role.USER;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }

    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }

    //Oauth2
    public User updateName(String nickname) {
        this.nickname = nickname;

        return this;
    }

    //Oauth2
    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
