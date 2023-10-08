package com.example.back.mapper;

import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.PurchaseHistoryDto;
import com.example.back.entity.PurchaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PurchaseHistoryMapper {

//    void createPurchaseHistory(@Param("Pur")PurchaseHistoryDto purchaseHistoryDto, @Param("Pri")PrincipalDetail principalDetail); 재우
    void createPurchaseHistory(@Param("Pur")PurchaseHistoryDto purchaseHistoryDto, Long userId); // 홍진(이렇게 하면 @Param("Pur")을 안적어도됨.
    // void createPurchaseHistory(PurchaseHistoryDto purchaseHistoryDto); // 구매 이력 추가

    List<Map<String,Object>> purchaseHistories(); // 구매 이력 목록 조회

    Map<String, Object> selectPurchaseHistoryDetail(Long purchaseId); // 구매 이력 상세 조회

    void updatePurchaseHistory(PurchaseHistoryDto purchaseHistoryDto); // 구매 이력 수정

    void deletePurchaseHistory(Long purchaseId); // 구매 이력 삭제
}
