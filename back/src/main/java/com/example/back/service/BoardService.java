package com.example.back.service;

import com.example.back.entity.Board;
import com.example.back.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    public List<Board> listAllBoards() {
        return boardRepository.findAll();
    }

    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not exist with id :" + id));

        int cnt = board.getViewCnt();
        board.setViewCnt(cnt + 1);

        return ResponseEntity.ok(board);
    }

    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board boardDetails) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not exist with id :" + id));

        board.setBdSubject(boardDetails.getBdSubject());
        board.setBdContents(boardDetails.getBdContents());

        Board updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }

    // delete board
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not exist with id :" + id));

        boardRepository.delete(board);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
