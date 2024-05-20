package com.enjoyTrip.OdysseyFrontiers.board.model.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardListDto;

public interface BoardService {

	int writeBoard(BoardDto boardDto) throws Exception;
//	List<BoardDto> listBoard(Map<String, String> map) throws Exception;
	BoardListDto listBoard(Map<String, String> map) throws Exception;
	BoardDto getBoard(int BoardNo) throws Exception;
	int modifyBoard(BoardDto boardDto) throws Exception;
	int deleteBoard(int BoardNo) throws Exception;

	int createOrUpdateHit(BoardHitDto boardHitDto) throws Exception;
}
