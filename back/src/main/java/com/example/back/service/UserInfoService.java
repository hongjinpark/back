package com.example.back.service;

import com.example.back.dto.UserInfoDto;
import com.example.back.entity.UserInfoEntity;
import com.example.back.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }
    //signup
    public UserInfoDto post(UserInfoDto userInfoDto){
        UserInfoEntity userInfoEntity = new UserInfoEntity(userInfoDto);
        userInfoRepository.save(userInfoEntity);
        return userInfoDto;
    }

    public UserInfoDto getUserByEmail(String email){
        UserInfoEntity userInfoEntity = userInfoRepository.findByEmail(email);
        UserInfoDto userInfoDto = new UserInfoDto(userInfoEntity);
        return userInfoDto;
    }
}
