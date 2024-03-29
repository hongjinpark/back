package com.example.back.repository;


import com.example.back.dto.MainBoardDto;
import com.example.back.dto.ProductSearchDto;
import com.example.back.dto.QMainBoardDto;
import com.example.back.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainBoardDto> findAllBoardAndImgUrl(ProductSearchDto productSearchDto) {

        QBoard board = QBoard.board;
        QBoardImage boardImage = QBoardImage.boardImage;

        return queryFactory.select(
                new QMainBoardDto(
                        board.id,
                        board.region.regionName,
                        board.bdSubject,
                        board.bdContents,
                        boardImage.imgUrl,
                        board.status)
                )
                        .from(boardImage)
                        .join(boardImage.board, board)
                        .where(boardImage.repImgYn.eq("Y"))
                        .where(bdSubjectLike(productSearchDto.getSearchQuery()))
                        .fetch();
    }

    @Override
    public List<MainBoardDto> findUserBoardAndImgUrl(Long id) {

        QBoard board = QBoard.board;
        QBoardImage boardImage = QBoardImage.boardImage;
        QUser user = QUser.user;

        return queryFactory.select(
                        new QMainBoardDto(
                                board.id,
                                board.region.regionName,
                                board.bdSubject,
                                board.bdContents,
                                boardImage.imgUrl,
                                board.status)
                )
                .from(board)
                .join(boardImage).on(board.id.eq(boardImage.board.id))
                .join(board).on(board.id.eq(board.user.id))
                .where(boardImage.repImgYn.eq("Y"))
                .where(board.user.id.eq(id))
                .fetch();
    }

    private BooleanExpression bdSubjectLike(String searchQuery) {

        return StringUtils.hasText(searchQuery) ? QBoard.board.bdSubject.like("%" + searchQuery + "%") : null;
    }

}
