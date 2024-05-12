package com.enjoyTrip.OdysseyFrontiers.board.controller;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.BoardService;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.CommentService;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;

@RestController
@RequestMapping("/board")
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@RequestParam Map<String, String> map) throws Exception {
        // 게시판의 리스트를 json으로 보내주는 코드
        return new ResponseEntity<>(boardService.listBoard(map), HttpStatus.OK);
    }


    @GetMapping("/view/{BoardNo}")
    public ResponseEntity<?> view(@PathVariable int BoardNo, @RequestParam Map<String, String> map,
                               Model model, HttpSession session)
            throws Exception {

        // 현재는 회원만 board에 접근 가능.
        MemberDto memberDto = (MemberDto) session.getAttribute(SESSION_MEMBER_INFO);
        BoardHitDto boardHitDto = new BoardHitDto(BoardNo, memberDto.getMemberId());
        boardService.createOrUpdateHit(boardHitDto);

        BoardDto boardDto = boardService.getBoard(BoardNo);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);

//        model.addAttribute("Board", boardDto);
//        model.addAttribute("pgno", map.get("pgno"));
//        model.addAttribute("key", map.get("key"));
//        model.addAttribute("word", map.get("word"));


    }
}
