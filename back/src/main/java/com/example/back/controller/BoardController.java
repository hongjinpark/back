package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.BoardDto;
import com.example.back.dto.BoardListDto;
import com.example.back.entity.Board;
import com.example.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/new") //board 생성
    public ResponseEntity createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        BoardDto boardDto_new = boardService.createBoard(boardDto, principalDetail);

        return ResponseEntity.ok(boardDto_new);
    }

    @GetMapping("/lists") //user에 따른 조회
    public ResponseEntity<List<BoardListDto>> getBoardById(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        List<BoardListDto> boardDtoList = boardService.getBoardById(principalDetail.getId());

        return ResponseEntity.ok(boardDtoList);
    }

    @PutMapping("/lists/{boardId}") //boardId에 따른 board 수정
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, @RequestBody Board boardDetails) {

        return boardService.updateBoard(boardId, boardDetails);
    }

    @DeleteMapping("/lists/{boardId}") //board 삭제
    public ResponseEntity<Object> deleteBoard(@PathVariable Long boardId) {

        boardService.deleteBoard(boardId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestPart(value="file",required = false) MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        File destination = new File("upload/dir" + originalFileName);
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(originalFileName);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(originalFileName);
    }

}