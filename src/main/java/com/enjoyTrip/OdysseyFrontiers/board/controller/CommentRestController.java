package com.enjoyTrip.OdysseyFrontiers.board.controller;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.CommentDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/comment")
public class CommentRestController {

    private final CommentService commentService;

    @Autowired
    public CommentRestController(CommentService commentService) {
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
