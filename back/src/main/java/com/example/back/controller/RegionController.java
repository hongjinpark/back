package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.RegionDto;
import com.example.back.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/lists")
    public ResponseEntity<List<RegionDto>> getRegionListByUserId(@AuthenticationPrincipal PrincipalDetail principalDetail){

        List<RegionDto> regionDtoList = regionService.selectRegionListByUserId(principalDetail.getId());

        return ResponseEntity.ok(regionDtoList);
    }

    @PostMapping
    public ResponseEntity<RegionDto> saveAndUpdateRegion(@AuthenticationPrincipal PrincipalDetail principalDetail
            , @RequestBody RegionDto regionDto){
        regionDto.setUserId(principalDetail.getId());
        RegionDto regionResult = regionService.saveAndUpdateRegion(regionDto);
        return ResponseEntity.ok(regionResult);
    }
}
