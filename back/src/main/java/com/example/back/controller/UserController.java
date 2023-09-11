package com.example.back.controller;


import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.UserDto;
import com.example.back.dto.UserInfoDto;
import com.example.back.entity.User;
import com.example.back.mapper.UserInfoMapper;
import com.example.back.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserInfoMapper userInfoMapper;

    //계정 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto, HttpServletResponse response) {
        UserDto result = userService.loginUser(userDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication = " + authentication);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
        //token header에 추가
        response.setHeader(HttpHeaders.AUTHORIZATION, result.getToken());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //신규 계정 생성 (회원가입)
    @PostMapping("/new")
    public User postUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    //계정 이름, 비밀번호 수정
    @PutMapping("/modify")
    public Long updateUser(@RequestBody UserDto userDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        return userService.updateUser(userDto, principalDetail);
    }

    //계정 삭제
    @DeleteMapping("/delete")
    public void deleteUser(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        userService.deleteUser(principalDetail.getEmail());
    }

    //계정 정보 조회
    @GetMapping("/info")
    public ResponseEntity<?> selectUserInfo() {
        List<Map<String, Object>> result =  userInfoMapper.selectUserInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //계정 정보 수정
    @PostMapping("/info")
    public void updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        userInfoMapper.updateUserInfo(userInfoDto);
    }
}
