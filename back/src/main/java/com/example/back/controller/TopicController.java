package com.example.back.controller;

import com.example.back.dto.TopicDto;
import com.example.back.mapper.TopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicMapper topicMapper;

    // 관심 주제 추가
    @PostMapping("/add")
    public void addTopic(@RequestBody TopicDto topicDto){
        topicMapper.createTopic(topicDto);
    }

    // 관심 주제 목록 조회
    @GetMapping
    public List<Map<String, Object>> topics(){
        return topicMapper.selectTopicList();
    }

    // 관심 주제 상세 조회
    @GetMapping("/{selectProductId}")
    public ResponseEntity<?> topic(@PathVariable Long selectProductId){
        Map<String, Object> result = topicMapper.selectTopicDetail(selectProductId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 관심 주제 수정
    @PostMapping("/{selectProductId}/edit")
    public ResponseEntity<?> editTopic(@PathVariable Long selectProductId, @RequestBody TopicDto topicDto){
        topicDto.setId(selectProductId);
        topicMapper.updateTopic(topicDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 관심 주제 삭제
    @DeleteMapping("/{selectProductId}")
    public void deleteTopic(@PathVariable Long selectProductId){
        topicMapper.deleteTopic(selectProductId);
    }
}
