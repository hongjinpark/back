package com.example.back.service;

import com.example.back.dto.UserInfoDto;
import com.example.back.entity.UserInfo;
import com.example.back.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }
    //signup
    public UserInfoDto post(UserInfoDto userInfoDto){
        UserInfo userInfoEntity = new UserInfo(userInfoDto);
        userInfoRepository.save(userInfoEntity);
        return userInfoDto;
    }

    public UserInfoDto getUserByEmail(String email){
        UserInfo userInfoEntity = userInfoRepository.findByEmail(email);
        UserInfoDto userInfoDto = new UserInfoDto(userInfoEntity);
        return userInfoDto;
    }
}
