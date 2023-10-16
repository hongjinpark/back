package com.example.back.mapper;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NoticeMapper {

    void createNotice(@Param("Not")NoticeDto noticeDto, @Param("Pri")PrincipalDetail principalDetail); //공지사항 추가

    List<NoticeDto> selectNoticeList(); //공지사항 목록 조회

    Map<String, Object> selectNoticeDetail(Long id); //공지사항 상세보기 조회

    void updateNotice(@Param("Not")NoticeDto noticeDto, @Param("Pri")PrincipalDetail principalDetail); //공지사항 수정

    void deleteNotice(Long id, @Param("Pri")PrincipalDetail principalDetail); //공지사항 삭제

}
