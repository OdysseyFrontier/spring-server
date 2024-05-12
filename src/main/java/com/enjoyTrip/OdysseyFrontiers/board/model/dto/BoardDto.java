package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.BoardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDto {

	private int boardNo;
	private String memberId;
	private String memberName;
	private String subject;
	private String content;
	private String registerTime;
	private BoardType type;
	private int hit;

}
