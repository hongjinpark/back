package com.example.back.dto;

import com.example.back.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id; // comment_id

    private String status; //N : 댓글 없음 Y : 댓글 있음

    private String contents; // 댓글 내용

    private String cmlayer; //0 : 댓글 1 : 대댓글(답글)

    private String commentGroup; //댓글 그룹. 0 : 1번째 댓글 1 : 2번째 댓글

    private Long board; // board_id

}
