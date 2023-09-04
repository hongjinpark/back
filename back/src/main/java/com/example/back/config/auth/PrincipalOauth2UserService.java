package com.example.back.config.auth;

import com.example.back.config.auth.userinfo.GoogleUserInfo;
import com.example.back.config.auth.userinfo.KakaoUserInfo;
import com.example.back.config.auth.userinfo.NaverUserInfo;
import com.example.back.config.auth.userinfo.OAuth2UserInfo;
import com.example.back.constant.Role;
import com.example.back.entity.User;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Configuration
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

   /* private final PasswordEncoder passwordEncoder;*/

    private final UserRepository userRepository;

    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료 시  @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> 코드 리턴 -> 액세스 토큰 요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아준다.

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;	//추가
        String provider = userRequest.getClientRegistration().getRegistrationId();

        //추가
        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getName();
        String nickname = provider + "_" + oAuth2UserInfo.getName();
        /*String password = passwordEncoder.encode("비밀번호");*/
        String email = oAuth2UserInfo.getEmail();

        User userEntity = userRepository.findByEmail(email);


        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());
        System.out.println("oAuth2UserInfo = " + oAuth2UserInfo);

        System.out.println("oAuth2UserInfo = " + oAuth2UserInfo.getName());
        if (userEntity == null) {
            userEntity = User.builder()
                    .nickname(nickname)
                    /*.password(password)*/
                    .email(email)
                    .role(Role.USER)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetail(userEntity, oAuth2User.getAttributes());
    }
}