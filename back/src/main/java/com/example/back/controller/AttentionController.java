package com.example.back.controller;

import com.example.back.config.auth.PrincipalDetail;
//import com.example.back.service.AttentionService;
import com.example.back.dto.AttentionDto;
import com.example.back.service.AttentionService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/attention")
public class AttentionController {

    private final AttentionService attentionService;

    @PostMapping
    public ResponseEntity<AttentionDto> saveAndUpdateAttention(@RequestBody AttentionDto attentionDto){
        AttentionDto attentionResult = attentionService.saveAndUpdateAttention(attentionDto);

        return ResponseEntity.ok(attentionResult);
    }
}