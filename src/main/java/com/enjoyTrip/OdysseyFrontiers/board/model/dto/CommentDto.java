package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

public class CommentDto {
    private int commentId;
    private long memberId;
    private String name;
    private int boardNo;
    private String content;
    private String registerTime;

    public CommentDto(int commentId, Long memberId, int boardNo, String content, String registerTime) {
        this.commentId = commentId;
        this.memberId = memberId;
        this.boardNo = boardNo;
        this.content = content;
        this.registerTime = registerTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", memberId='" + memberId + '\'' +
                ", boardNo=" + boardNo +
                ", content='" + content + '\'' +
                ", registerTime='" + registerTime + '\'' +
                '}';
    }
}
