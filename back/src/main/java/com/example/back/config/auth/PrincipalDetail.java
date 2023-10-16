package com.example.back.config.auth;

import com.example.back.config.auth.userinfo.OAuth2UserInfo;
import com.example.back.constant.Role;
import com.example.back.entity.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetail implements UserDetails, OAuth2User {

    private OAuth2UserInfo oAuth2UserInfo;
    private User user;
    private Map<String, Object> attributes;

    //일반 로그인 생성자
    public PrincipalDetail(User user) {
        this.user = user;
    }

    //OAuth 로그인 생성자
    public PrincipalDetail(User user, Map<String, Object> attributes ) {
        this.user = user;
        this.attributes = attributes;
    }

    public PrincipalDetail(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    /**
     * OAuth2User 인터페이스 메소드
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * UserDetails 인터페이스 메소드
     */
    // 해당 User의 권한을 리턴하는 곳!(role)
    // SecurityFilterChain에서 권한을 체크할 때 사용됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());

        return collection;
    }
    //사용자 이메일
    public String getEmail() {
        return user.getEmail();
    }

    //사용자 pk
    public Long getId() {
        return user.getId();
    }

    //사용자 패스워드
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //사용자 이름
    @Override
    public String getUsername() {
        return user.getNickname(); //name -> nickname
    }

    //계정이 만료되었는지 (true: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 (true: 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드가 만료되지 않았는지 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화되어 있는지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUser(User userEntity) {
        this.user = user;
    }

    @Override
    public String getName() {
        return null;
    }

    //사용자 닉네임
    public String getNickName() {
        return user.getNickname();
    }

    public Role getRole() {
        return user.getRole();
    }
}