package com.enjoyTrip.OdysseyFrontiers.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardListDto {

	// 글 목록
	private List<BoardDto> articles;
	// 현재 페이지번호
	private int currentPage;
	// 전체 페이지 수
	private int totalPageCount;

}
