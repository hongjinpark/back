package com.example.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductImg {
    private String url;
    private byte[] data;

    public ResponseProductImg(String url) {
        this.url = url;
    }
}
