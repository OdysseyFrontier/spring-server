package com.enjoyTrip.OdysseyFrontiers.board.model.service;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.mapper.BoardMapper;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public void writeBoard(BoardDto boardDto) throws Exception {
        boardMapper.writeBoard(boardDto);
    }

    @Override
    public List<BoardDto> listBoard(Map<String, String> map) throws Exception {
        return boardMapper.listBoard(map);
    }

    @Override
    public BoardDto getBoard(int BoardNo) throws Exception {
        return boardMapper.getBoard(BoardNo);
    }

//	@Override
//	public void updateHit(int BoardNo) throws Exception {
//		boardMapper.updateHit(BoardNo);
//	}

    @Override
    public void modifyBoard(BoardDto boardDto) throws Exception {
        boardMapper.modifyBoard(boardDto);
    }

    @Override
    public void deleteBoard(int BoardNo) throws Exception {
        boardMapper.deleteBoard(BoardNo);
    }

    @Override
    @Transactional
    public void createOrUpdateHit(BoardHitDto boardHitDto) throws Exception {
        int boardNo = boardHitDto.getBoardNo();
        String memberId = boardHitDto.getMemberId();
        Optional<Integer> recentMemberHitId =
                boardMapper.getRecentMemberHit(boardNo, memberId);

        if (recentMemberHitId.isEmpty()) {
            boardMapper.createHit(boardNo, memberId);
        } else {
            boardMapper.updateHit(recentMemberHitId.get());
        }
    }

//	@Override
//	public int countHit(int boardNo) {
//		return boardMapper.countHit(boardNo);
//	}

}
