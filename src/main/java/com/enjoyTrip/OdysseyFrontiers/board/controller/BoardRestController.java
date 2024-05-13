package com.enjoyTrip.OdysseyFrontiers.board.controller;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.BoardService;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.CommentService;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JwtInterpreter;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst.HEADER_ACCESS_TOKEN;
import static com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst.HEADER_AUTH;
import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;


@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;
    private final JwtInterpreter jwtInterpreter;


    @PostMapping("/")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto,
                                   @RequestPart(value = "upfile", required = false) MultipartFile[] files,
                                   @RequestHeader(name = HEADER_AUTH, required = true) String jwtToken
    ) throws Exception {
        log.info("jwtToken : {}", jwtToken);
        Long userId = jwtInterpreter.getUserId(jwtToken);
        boardDto.setMemberId(userId);

        int result = boardService.writeBoard(boardDto);
        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@RequestParam Map<String, String> map) throws Exception {
        // 게시판의 리스트를 json으로 보내주는 코드
        return new ResponseEntity<>(boardService.listBoard(map), HttpStatus.OK);
    }


    @GetMapping("/{BoardNo}")
    public ResponseEntity<?> view(@PathVariable int BoardNo,
                                  @RequestHeader(name = HEADER_AUTH, required = false) String jwtToken) throws Exception {

        // 현재는 회원만 board 에 조회 수 증가 가능.
        if (jwtToken != null) {
            Long userId = jwtInterpreter.getUserId(jwtToken);
            BoardHitDto boardHitDto = new BoardHitDto(BoardNo, userId);
            boardService.createOrUpdateHit(boardHitDto);
        }

        // 비회원 증가하려면, ip를 가져와야함?

        BoardDto boardDto = boardService.getBoard(BoardNo);

        log.info("{}", boardDto);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PutMapping("/{BoardNo}")
    public ResponseEntity<?> modify(@PathVariable int BoardNo,
                                    @RequestHeader(name = HEADER_AUTH, required = true) String jwtToken,
                                    @RequestBody BoardDto boardDto)
            throws Exception {


        if (BoardNo != boardDto.getBoardNo()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long userId = jwtInterpreter.getUserId(jwtToken);
        if (userId  != boardDto.getMemberId()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        System.out.println(BoardNo);
        System.out.println(boardDto);
        
        int result = boardService.modifyBoard(boardDto);
        
        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @DeleteMapping("/{BoardNo}")
    public ResponseEntity<?> delete(@PathVariable int BoardNo) throws Exception {

        int result = boardService.deleteBoard(BoardNo);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
