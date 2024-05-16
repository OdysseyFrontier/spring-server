package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class BoardDto {

	// board PK
	private int boardNo;
	
	// 작성자 이름 가져오기 위한 외래키(members PK)
	private long memberId;
	
	// 작정자 이름
	private String name;
	
	private String subject;
	private String content;
	private String registerTime;
	
	// type = notice, community
	private String type;
	
	private int hit;

}
