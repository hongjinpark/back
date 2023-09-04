package com.example.back.mapper;

import com.example.back.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserInfoMapper {

    void createUserInfo(String nickName, String status); //회원가입시 userInfo TB nickName, status 저장

    void updateUserInfo(UserInfoDto userInfoDto); //로그인한 사용자

    List<Map<String, Object>> selectUserInfo();
}
