package com.enjoyTrip.OdysseyFrontiers.attraction.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gugun {
	
	private int gugunCode;
	private String gugunName;
	private int sidoCode;
	

	@Override
	public String toString() {
		return "Gugun [gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", sidoCode=" + sidoCode + "]";
	}
}
