package com.example.back.mapper;

import com.example.back.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NoticeMapper {

    void createNotice(NoticeDto noticeDto); //공지사항 추가

    List<NoticeDto> selectNoticeList(); //공지사항 목록 조회

    Map<String, Object> selectNoticeDetail(Long noticeId); //공지사항 상세보기 조회

    void updateNotice(NoticeDto noticeDto); //공지사항 수정

    void deleteNotice(Long noticeId); //공지사항 삭제

}
