package com.example.back.dto;

import com.example.back.entity.Region;
import com.example.back.entity.User;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class RegionDto {
    private String status; //N : 삭제한 동네 Y : 등록된 동네
    private String regionName; //지역 명칭
    private String leadStatus; //N : 대표지역 (X) Y : 대표지역(O)
    private String noticeStatus; //기본값 Y
    private long userId;

    public RegionDto(Region region, User user) {
        this.status = region.getStatus();
        this.regionName = region.getRegionName();
        this.leadStatus = region.getLeadStatus();
        this.noticeStatus = region.getNoticeStatus();
        this.userId = user.getId();
    }
}
