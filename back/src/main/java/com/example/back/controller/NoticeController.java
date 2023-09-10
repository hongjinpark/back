package com.example.back.controller;

import com.example.back.dto.NoticeDto;
import com.example.back.entity.Notice;
import com.example.back.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeMapper noticeMapper;

    //공지사항 추가
    @PostMapping("/new")
    public void postNotice(@RequestBody NoticeDto noticeDto) {
        noticeMapper.createNotice(noticeDto);
    }

    //공지사항 목록 조회
    @GetMapping("/list")
    public List<NoticeDto> listNotice() {
        return noticeMapper.selectNoticeList();
    }

    //공지사항 상세보기 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<?> detailNotice(@PathVariable Long noticeId) {
        Map<String, Object> result = noticeMapper.selectNoticeDetail(noticeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //공지사항 수정
    @PostMapping("/update")
    public void updateNotice(@RequestBody NoticeDto noticeDto) {
        noticeMapper.updateNotice(noticeDto);

    }

    //공지사항 삭제
    @DeleteMapping("/{noticeId}")
    public void deleteNotice(@PathVariable Long noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }
}
