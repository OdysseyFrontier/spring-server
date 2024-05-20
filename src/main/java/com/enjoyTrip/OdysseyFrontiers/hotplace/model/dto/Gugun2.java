package com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gugun2 {

	private int gugunCode;
	private String gugunName;
	private int sidoCode;


	@Override
	public String toString() {
		return "Gugun [gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", sidoCode=" + sidoCode + "]";
	}
}
