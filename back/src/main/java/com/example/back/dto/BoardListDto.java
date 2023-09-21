package com.example.back.dto;

import com.example.back.entity.Board;
import com.example.back.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDto {

    private Long userId;

    private String status;

    private String bdSubject; //게시글 주제

    private String bdContents; //게시글 내용

    public BoardListDto(Board board, User user) {
        this.userId = user.getId();
        this.status = board.getStatus();
        this.bdSubject = board.getBdSubject();
        this.bdContents = board.getBdContents();
    }
}
