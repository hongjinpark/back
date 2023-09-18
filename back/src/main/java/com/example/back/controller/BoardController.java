package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.BoardDto;
import com.example.back.dto.BoardListDto;
import com.example.back.entity.Board;
import com.example.back.repository.BoardRepository;
import com.example.back.repository.RegionRepository;
import com.example.back.repository.UserRepository;
import com.example.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final BoardRepository boardRepository;

    @PostMapping("/new") //board 생성
    public ResponseEntity createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        BoardDto boardDto_new = boardService.createBoard(boardDto);

        return ResponseEntity.ok().build();
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

}