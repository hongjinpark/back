package com.example.back.controller;

import com.example.back.dto.UserInfoDto;
import com.example.back.entity.UserInfoEntity;
import com.example.back.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    UserInfoController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    //signup
    @PostMapping
    public ResponseEntity<UserInfoDto> post(@RequestBody UserInfoDto userInfoDto){
        UserInfoDto postedUserInfo = userInfoService.post(userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedUserInfo);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserInfoDto> get(@PathVariable String email){
        UserInfoDto userInfoDto = userInfoService.getUserByEmail(email);
        return ResponseEntity.ok(userInfoDto);
    }
}
