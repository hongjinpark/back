package com.example.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "topic")
@Getter
@NoArgsConstructor
public class Topic extends BaseEntity{ // 관심 주제

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;

    @Column(name = "status", nullable = false)
    private String status; // N : 사라진 주제, Y : 있는 주제

    @Column(name = "topic_name", nullable = false)
    private String topicName; // 주제 이름
}
