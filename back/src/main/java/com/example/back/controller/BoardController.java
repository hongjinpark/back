package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.*;
import com.example.back.entity.Board;
import com.example.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {

        boardService.deleteBoard(boardId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/boardImg")
    public ResponseEntity<String> boardImg_upload(@ModelAttribute("status") String status,
                                                  @ModelAttribute("bdSubject") String bdSubject,
                                                  @ModelAttribute("bdContents") String bdContents,
                                                  @ModelAttribute("images") List<MultipartFile> images,
                                                  @ModelAttribute("region") String region,
                                                  @AuthenticationPrincipal PrincipalDetail principalDetail) {

        RequestBoard requestBoard = RequestBoard.builder()
                .bdSubject(bdSubject)
                .bdContents(bdContents)
                .status(status)
                .userId(principalDetail.getId())
                .region(region)
                .images(new ArrayList<>())
                .build();

        try {
            for(MultipartFile multipartFile : images) {
                byte[] bytes = multipartFile.getBytes();

                String staticPath = Paths.get("src/main/resources").toString();
                String filePath = staticPath + File.separator + "images" + File.separator + multipartFile.getOriginalFilename();
                System.out.println(filePath);

                Path path = Paths.get(filePath);
                Files.createDirectories(path.getParent());

                File newFile = new File(filePath);
                newFile.createNewFile();
                FileUtils.writeByteArrayToFile(newFile, bytes);
                requestBoard.getImages().add(new RequestBoardImg(filePath));

            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }

        boardService.boardImg_upload(requestBoard);
        return ResponseEntity.ok("S");
    }

}