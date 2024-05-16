package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardLikeDto {
    private int boardNo;
    private long memberId;
    private String createTime;

    public BoardLikeDto(int boardNo, Long memberId) {
        this.boardNo = boardNo;
        this.memberId = memberId;
    }


}
