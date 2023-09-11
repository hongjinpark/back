package com.example.back.controller;

import com.example.back.entity.Board;
import com.example.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/test")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping("/test")
    public List<Board> listAllBoards() {
        return boardService.listAllBoards();
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/test/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board boardDetails) {
        return boardService.updateBoard(id, boardDetails);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

}
