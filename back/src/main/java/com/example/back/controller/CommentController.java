package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.dto.CommentDto;
import com.example.back.mapper.BoardMapper;
import com.example.back.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;

    @GetMapping("/list")
    public List<CommentDto> listNotice(CommentDto commentDto) {
        return commentMapper.selectComment(commentDto);
    }

    //댓글 추가
    @PostMapping("/new")
    public void postComment(@RequestBody CommentDto commentDto) {
        commentMapper.createComment(commentDto);
    }

    //댓글 수정
    @PostMapping("/update")
    public void updateComment(@RequestBody CommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    }

    //댓굴 삭제
    @PostMapping("/delete")
    public void deleteComment(@RequestBody CommentDto commentDto) {
        commentMapper.deleteComment(commentDto);
    }
}
