package com.example.back.mapper;

import com.example.back.dto.BoardDto;
import com.example.back.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<CommentDto> selectComment(CommentDto commentDto); //댓글 조회

    void createComment(CommentDto commentDto); //댓글 추가

    void updateComment(CommentDto commentDto); //댓글 수정

    void deleteComment(CommentDto commentDto); //댓글 삭제


}
