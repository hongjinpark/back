package com.example.back.mapper;

import com.example.back.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserInfoMapper {

    void createUserInfo(@Param("nickName")String nickName, @Param("status") String status); //회원가입시 userInfo TB nickName, status 저장

    void updateUserInfo(UserInfoDto userInfoDto); //로그인한 사용자 정보 수정

    List<Map<String, Object>> selectUserInfo(); //사용자 정보 조회
}
