package com.example.back.entity;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "select_product_id")
    private long id;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "topic_name", nullable = false)
    private String topicName;
}
