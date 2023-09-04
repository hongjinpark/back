package com.example.back.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 사용자별 동네설정 테이블
 */

@Entity
@Table(name = "region")
@NoArgsConstructor
@Getter
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(nullable = false)
    private String status; //N : 삭제한 동네 Y : 등록된 동네

    @Column(nullable = false)
    private String regionName; //지역 명칭

    @Column(nullable = false)
    private String leadStatus; //N : 대표지역 (X) Y : 대표지역(O)

    @Column(nullable = false)
    private String noticeStatus; //기본값 Y

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
