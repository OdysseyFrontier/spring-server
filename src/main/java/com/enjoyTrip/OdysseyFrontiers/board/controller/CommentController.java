package com.enjoyTrip.OdysseyFrontiers.board.controller;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.CommentDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertComment(@RequestBody CommentDto commentDto) {
        commentService.insertComment(commentDto);
        List<CommentDto> comments = commentService.selectCommentsByBoardNo(commentDto.getBoardNo());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<?> selectCommentsByBoardNo(@PathVariable("boardNo") int boardNo) {
        List<CommentDto> comments = commentService.selectCommentsByBoardNo(boardNo);
        return new ResponseEntity<>(comments, HttpStatus.OK);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//        return ResponseEntity.ok().headers(headers).body(comments);
    }

    @PutMapping("/update")
    public String updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
        return "Comment updated successfully.";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") int commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully.";
    }
}
