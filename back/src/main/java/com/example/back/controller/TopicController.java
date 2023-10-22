package com.example.back.controller;

import com.example.back.dto.TopicDto;
import com.example.back.mybatis.mapper.TopicMapper;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/{topicId}")
    public ResponseEntity<?> topic(@PathVariable Long topicId){
            Map<String, Object> result = topicMapper.selectTopicDetail(topicId);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 관심 주제 수정
    @PostMapping("/edit/{topicId}")
    public ResponseEntity<?> editTopic(@PathVariable Long topicId, @RequestBody TopicDto topicDto){
        topicDto.setId(topicId);
        topicMapper.updateTopic(topicDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 관심 주제 삭제
    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable Long topicId){
        topicMapper.deleteTopic(topicId);
    }
}
