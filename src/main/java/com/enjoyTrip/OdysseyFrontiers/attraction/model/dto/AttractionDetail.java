package com.enjoyTrip.OdysseyFrontiers.attraction.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionDetail {
	private int contentId;
	private String cat1;
	private String cat2;
	private String cat3;
	private String createdTime;
	private String modifiedTime;
	private String booktour;

    @Override
	public String toString() {
		return "AttractionDetail [contentId=" + contentId + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", booktour=" + booktour + "]";
	}
	
	
}
