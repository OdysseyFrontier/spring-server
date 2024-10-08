package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardHitDto {
    private int boardHitId;
    private int boardNo;
    private long memberId;
    private String createTime;
    private String updateTime;

    public BoardHitDto(int boardNo, Long memberId) {
        this.boardNo = boardNo;
        this.memberId = memberId;
    }


}
