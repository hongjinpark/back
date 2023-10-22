package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.NoticeDto;
import com.example.back.mybatis.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeMapper noticeMapper;

    //공지사항 추가
    @PostMapping("/new")
    public void postNotice(@Valid @RequestBody NoticeDto noticeDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        noticeMapper.createNotice(noticeDto, principalDetail);
    }

    //공지사항 목록 조회
    @GetMapping("/list")
    public List<NoticeDto> listNotice() {
        return noticeMapper.selectNoticeList();
    }

    //공지사항 상세보기 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detailNotice(@PathVariable Long id) {
        Map<String, Object> result = noticeMapper.selectNoticeDetail(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //공지사항 수정
    @PostMapping("/update")
    public void updateNotice(@Valid @RequestBody NoticeDto noticeDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        noticeMapper.updateNotice(noticeDto, principalDetail);

    }

    //공지사항 삭제
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        noticeMapper.deleteNotice(id, principalDetail);
    }
}
