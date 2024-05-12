package com.enjoyTrip.OdysseyFrontiers.board.model.service;


import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {

	void writeBoard(BoardDto boardDto) throws Exception;
	List<BoardDto> listBoard(Map<String, String> map) throws Exception;
	BoardDto getBoard(int BoardNo) throws Exception;
	void modifyBoard(BoardDto boardDto) throws Exception;
	void deleteBoard(int BoardNo) throws Exception;

	void createOrUpdateHit(BoardHitDto boardHitDto) throws Exception;
}
