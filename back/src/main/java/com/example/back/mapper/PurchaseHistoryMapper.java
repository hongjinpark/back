package com.example.back.mapper;

import com.example.back.dto.PurchaseHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PurchaseHistoryMapper {

    void createPurchaseHistory(PurchaseHistoryDto purchaseHistoryDto, Long userId); // 구매 이력 추가

    List<Map<String,Object>> purchaseHistories(); // 구매 이력 목록 조회

    Map<String, Object> selectPurchaseHistoryDetail(Long purchaseId); // 구매 이력 상세 조회

//    void updatePurchaseHistory(PurchaseHistoryDto purchaseHistoryDto); // 구매 이력 수정

    void deletePurchaseHistory(Long purchaseHistoryId); // 구매 이력 삭제
}
