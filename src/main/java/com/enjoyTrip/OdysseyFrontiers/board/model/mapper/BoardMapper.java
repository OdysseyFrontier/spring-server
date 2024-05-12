package com.enjoyTrip.OdysseyFrontiers.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void writeBoard(BoardDto reqBardDto) throws SQLException;
    List<BoardDto> listBoard(Map<String, String> map) throws SQLException;
    BoardDto getBoard(int BoardNo) throws SQLException;

    void modifyBoard(BoardDto boardDto) throws SQLException;
    void deleteBoard(int BoardNO) throws SQLException;

    Optional<Integer> getRecentMemberHit(int boardNo, String memberId) throws SQLException;
    void createHit(int boardNo, String memberId);
    void updateHit(int boardHitId) throws SQLException;
}
