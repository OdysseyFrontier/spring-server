package com.enjoyTrip.OdysseyFrontiers.board.model.service;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.CommentDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.mapper.CommentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void insertComment(CommentDto comment) {
        commentMapper.insertComment(comment);
    }

//    @Override
//    public CommentDto selectCommentById(int commentId) {
//        return commentMapper.selectCommentById(commentId);
//    }

    @Override
    public List<CommentDto> selectCommentsByBoardNo(int boardNo) {
        return commentMapper.selectCommentsByBoardNo(boardNo);
    }

    @Override
    @Transactional
    public void updateComment(CommentDto comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
    }
}
