package com.example.back.service;


import com.example.back.entity.UserInfo;
import com.example.back.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class UserInfoService {

    @Value("${profileImgLocation}")
    private String profileImgLocation;

    private final UserInfoRepository userInfoRepository;
    private final FileService fileService;

    //유저 프로필 이미지 저장
    public void saveProfileImg(UserInfo userInfo, MultipartFile profileImgFile) throws Exception {

        String oriImgName = profileImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)) {

            imgName = fileService.uploadFile(profileImgLocation, oriImgName, profileImgFile.getBytes());
            imgUrl = "/images/user/" + imgName;
        }

        //상품 이미지 정보 저장
        userInfo.updateProfileImg(oriImgName, imgName, imgUrl);
        userInfoRepository.save(userInfo);
    }

    //유저 프로필 이미지 수정
    public void updateProfileImg(Long userInfoId, MultipartFile itemImgFile) throws Exception {

        if(!itemImgFile.isEmpty()) {

            UserInfo savedProfileImg = userInfoRepository.findById(userInfoId)
                    .orElseThrow(EntityNotFoundException::new);
            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedProfileImg.getImgName())) {

                fileService.deleteFile(profileImgLocation + "/" + savedProfileImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(profileImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/user/" + imgName;
            savedProfileImg.updateProfileImg(oriImgName, imgName, imgUrl);
        }
    }
}
