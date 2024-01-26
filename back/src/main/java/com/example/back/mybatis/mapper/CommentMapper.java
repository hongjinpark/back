package com.example.back.mybatis.mapper;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.BoardDto;
import com.example.back.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
@Repository
public interface CommentMapper {

    List<CommentDto> selectComment(Long id); //댓글 조회

    List<CommentDto> selectReplyComment(String commentGroup, Long id); //대댓글 조회

    void createComment(@Param("Com")CommentDto commentDto, @Param("Pri")PrincipalDetail principalDetail); //댓글 추가
    
    void createReplyComment(@Param("Com")CommentDto commentDto, @Param("Pri")PrincipalDetail principalDetail); //대댓글 추가

    void updateComment(CommentDto commentDto); //댓글 수정

    void deleteComment(Long id); //댓글 삭제

}
