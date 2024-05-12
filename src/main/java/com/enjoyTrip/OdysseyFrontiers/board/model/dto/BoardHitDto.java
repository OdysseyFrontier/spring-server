package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

public class BoardHitDto {
    private int boardHitId;
    private int boardNo;
    private String memberId;
    private String createTime;
    private String updateTime;

    public BoardHitDto(int boardNo, String memberId) {
        this.boardNo = boardNo;
        this.memberId = memberId;
    }

    public int getBoardHitId() {
        return boardHitId;
    }

    public void setBoardHitId(int boardHitId) {
        this.boardHitId = boardHitId;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
