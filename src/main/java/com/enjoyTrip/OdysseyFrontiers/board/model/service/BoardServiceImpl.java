package com.enjoyTrip.OdysseyFrontiers.board.model.service;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardListDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.mapper.BoardMapper;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public int writeBoard(BoardDto boardDto) throws Exception {
        return boardMapper.writeBoard(boardDto);
    }

//    @Override
//    public List<BoardDto> listBoard(Map<String, String> map) throws Exception {
//        return boardMapper.listBoard(map);
//    }
    @Override
    public BoardListDto listBoard(Map<String, String> map) throws Exception {
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "10" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		if ("member_id".equals(key))
			param.put("key", key == null ? "" : "b.member_id");
		List<BoardDto> list = boardMapper.listBoard(param);

		if ("member_id".equals(key))
			param.put("key", key == null ? "" : "member_id");
		int totalArticleCount = boardMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);
    	
    	
    	
    	return boardListDto;
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
    public int modifyBoard(BoardDto boardDto) throws Exception {
        return boardMapper.modifyBoard(boardDto);
    }

    @Override
    public int deleteBoard(int BoardNo) throws Exception {
        return boardMapper.deleteBoard(BoardNo);
    }

    @Override
    @Transactional
    public int createOrUpdateHit(BoardHitDto boardHitDto) throws Exception {
        int boardNo = boardHitDto.getBoardNo();
        Long memberId = boardHitDto.getMemberId();
        Optional<Integer> recentMemberHitId =
                boardMapper.getRecentMemberHit(boardNo, memberId);

        if (recentMemberHitId.isEmpty()) {
            return boardMapper.createHit(boardNo, memberId);
        } else {
            return boardMapper.updateHit(recentMemberHitId.get());
        }
    }

//	@Override
//	public int countHit(int boardNo) {
//		return boardMapper.countHit(boardNo);
//	}

}
