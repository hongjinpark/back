package com.example.back.jpa.service;

import com.example.back.dto.RegionDto;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import com.example.back.repository.RegionRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    //Region 삽입, 수정 메소드
    @Transactional
    public RegionDto saveAndUpdateRegion(RegionDto regionDto){
        //userId로 UserEntity 조회, 없으면 예외 처리
        User user = userRepository.findById(regionDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + regionDto.getUserId()));
        //userEntity, regionName 으로 AttentionEntity 조회, 없으면 생성
        Region region = regionRepository.findByUserAndAndRegionName(user, regionDto.getRegionName())
                .orElse(Region.builder()
                        .regionName(regionDto.getRegionName())
                        .noticeStatus(regionDto.getNoticeStatus())
                        .leadStatus(regionDto.getLeadStatus())
                        .user(user)
                        .build());
        //regionEntity status 수정
        region.setStatus(regionDto.getStatus());
        //regionEntity 저장
        regionRepository.save(region);

        return new RegionDto(region, user);
    }

    //User의 RegionList 조회
    @Transactional
    public List<RegionDto> selectRegionListByUserId(long userId){
        //userId로 UserEntity 조회, 없으면 예외 처리
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        //retionList 없을 때 빈 리스트 반환
        List<Region> regionList = regionRepository.findAllByUser(user).orElse(Collections.emptyList());

        //regionList를 regionDtoList로 변환
        return regionList.stream()
                .map(region -> new RegionDto(region, user))
                .collect(Collectors.toList());
    }
}
