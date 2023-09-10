package com.example.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {

    private Long id;

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String noticeTitle; //제목

    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String noticeContents; //내용

}
