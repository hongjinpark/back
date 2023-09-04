package com.example.back.dto;

import com.example.back.constant.Role;
import com.example.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    private String status;

    //일반 사용자 User
    private String provider;

    private Role role;

    private String token;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.status = user.getStatus();
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .nickname(nickname)
                .status("Y") //Y: 존재하는 유저
                .password(password)
                .provider("user")
                .role(Role.USER)
                .build();
    }

    public static UserDto toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();

        //google 일때
        if(attributes.get("email") != null) {
            return UserDto.builder()
                    .email((String)attributes.get("email"))
                    .nickname((String)attributes.get("nickname"))
                    .build();
        }
        //naver 일때
        else {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            return UserDto.builder()
                    .email((String)response.get("email"))
                    .nickname((String)response.get("nickname"))
                    .build();
        }
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
