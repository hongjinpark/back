package com.example.back.mapper;

import com.example.back.dto.TopicDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TopicMapper {

    void createTopic(TopicDto topicDto); // 관심 주제 추가

    List<Map<String, Object>> selectTopicList(); // 관심 주제 목록 조회

    Map<String, Object> selectTopicDetail(Long topicId); // 관심 주제 상세보기 조회

    void updateTopic(TopicDto topicDto); // 관심 주제 수정

    void deleteTopic(Long topicId); // 관심 주제 삭제
}
