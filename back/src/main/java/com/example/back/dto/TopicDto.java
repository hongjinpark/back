package com.example.back.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {

    private Long id;

    private String topicName; // 관심 주제 이름
}
