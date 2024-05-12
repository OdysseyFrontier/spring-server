package com.enjoyTrip.OdysseyFrontiers.board.model.mapper;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(CommentDto comment);
//    CommentDto selectCommentById(int commentId);
    List<CommentDto> selectCommentsByBoardNo(int boardNo);
    void updateComment(CommentDto comment);
    void deleteComment(int commentId);
}
