package com.example.back.controller;

import com.example.back.dto.PurchaseHistoryDto;
import com.example.back.mapper.PurchaseHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchaseHistories")
@RequiredArgsConstructor
public class PurchaseHistoryController {

    private final PurchaseHistoryMapper purchaseHistoryMapper;

    // 미완성 구매 이력 추가
    @PostMapping("/add")
    public ResponseEntity<Long> addPurchaseHistory() {
        PurchaseHistoryDto purchaseHistoryDto = new PurchaseHistoryDto();
        purchaseHistoryDto.setUser(new User(1));
    }

    // 구매 이력 목록 조회
    @GetMapping
    public List<Map<String,Object>> purchaseHistories(){
        return purchaseHistoryMapper.purchaseHistories();
    }

    // 구매 이력 상세 조회
    @GetMapping("/{purchaseId}")
    public ResponseEntity<Map<String,Object>> purchaseHistory(@PathVariable Long purchaseId){
        Map<String, Object> result = purchaseHistoryMapper.selectPurchaseHistoryDetail(purchaseId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 미완성 구매 이력 수정
    @PutMapping("/{purchaseId}/edit")
    public ResponseEntity<?> editPurchaseHistory(@PathVariable Long purchaseId, @RequestBody PurchaseHistoryDto purchaseHistoryDto){
        purchaseHistoryDto.setId(purchaseId);
        purchaseHistoryMapper.updatePurchaseHistory(purchaseHistoryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 구매 이력 삭제
    @DeleteMapping("/{purchaseId}")
    public void deletePurchaseHistory(@PathVariable Long purchaseId){
        purchaseHistoryMapper.deletePurchaseHistory(purchaseId);
    }
}
