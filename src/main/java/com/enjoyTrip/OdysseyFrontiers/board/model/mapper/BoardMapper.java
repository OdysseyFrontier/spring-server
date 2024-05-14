package com.enjoyTrip.OdysseyFrontiers.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int writeBoard(BoardDto reqBardDto) throws SQLException;
    
    List<BoardDto> listBoard(Map<String, Object> map) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    
    
    BoardDto getBoard(int BoardNo) throws SQLException;

    int modifyBoard(BoardDto boardDto) throws SQLException;
    int deleteBoard(int BoardNO) throws SQLException;

    Optional<Integer> getRecentMemberHit(int boardNo, Long memberId) throws SQLException;
    int createHit(int boardNo, Long memberId);
    int updateHit(int boardHitId) throws SQLException;
}
