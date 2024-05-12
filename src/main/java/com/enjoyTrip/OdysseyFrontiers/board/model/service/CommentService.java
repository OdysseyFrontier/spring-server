package com.enjoyTrip.OdysseyFrontiers.board.model.service;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.CommentDto;
import java.util.List;

public interface CommentService {
    void insertComment(CommentDto comment);
//    CommentDto selectCommentById(int commentId);
    List<CommentDto> selectCommentsByBoardNo(int boardNo);
    void updateComment(CommentDto comment);
    void deleteComment(int commentId);
}
