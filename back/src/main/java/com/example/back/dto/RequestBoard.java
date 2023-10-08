package com.example.back.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class RequestBoard {

    private Long userId;

    private String region;

    private String status;

    private String bdSubject;

    private String bdContents;

    private List<RequestBoardImg> images;
}
