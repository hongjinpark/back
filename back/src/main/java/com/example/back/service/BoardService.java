package com.example.back.service;

import com.example.back.dto.BoardDto;
import com.example.back.dto.BoardListDto;
import com.example.back.entity.Board;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import com.example.back.repository.BoardRepository;
import com.example.back.repository.RegionRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public BoardDto createBoard(BoardDto boardDto) {

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + boardDto.getUserId()));

        Region region = regionRepository.findById(boardDto.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found with ID: " + boardDto.getRegionId()));

        Board board= boardRepository.findByUserAndRegion(user, region)
                .orElse(Board.builder()
                        .bdSubject(boardDto.getBdSubject())
                        .bdContents(boardDto.getBdContents())
                        .status(boardDto.getStatus())
                        .user(user)
                        .region(region)
                        .build());
        boardRepository.save(board);

        return new BoardDto(board, user, region);
    }

    @Transactional
    public List<BoardListDto> getBoardById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not exist with ID : " + id));

        List<Board> boardList = boardRepository.findAllByUser(user).orElse(Collections.emptyList());


        return boardList.stream()
                .map(board -> new BoardListDto(board, user))
                .collect(Collectors.toList());


    }


    @Transactional
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, Board boardDetails) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not exist with ID :" + boardId));

        board.setBdSubject(boardDetails.getBdSubject());
        board.setBdContents(boardDetails.getBdContents());

        Board updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }

    @Transactional
    public void deleteBoard(Long boardId) {

        boardRepository.deleteById(boardId);
    }
}