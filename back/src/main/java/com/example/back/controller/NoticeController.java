package com.example.back.controller;

import com.example.back.dto.NoticeDto;
import com.example.back.entity.Notice;
import com.example.back.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ////@PathVariable 활용 문의 필요
    @GetMapping("/{id}")
    public void detailNotice(@PathVariable Long id) {
        noticeMapper.selectNoticeDetail(id);
    }

    //공지사항 수정
    @PostMapping("/update")
    public void updateNotice(@RequestBody NoticeDto noticeDto) {
        noticeMapper.updateNotice(noticeDto);
    }

    //공지사항 삭제
    ////@PathVariable 활용 문의 필요
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeMapper.deleteNotice(id);
    }
}
