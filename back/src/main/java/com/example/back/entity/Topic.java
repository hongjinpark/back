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
    @Column(name = "select_product_id")
    private long id;
    @Column(name = "status", nullable = false, columnDefinition = "N")
    private String status; // N : 사라진 주제, Y : 있는 주제
    @Column(name = "topic_name", nullable = false)
    private String topicName; // 주제 이름
}
