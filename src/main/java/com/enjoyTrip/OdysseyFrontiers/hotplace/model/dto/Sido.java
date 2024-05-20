package com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sido {
	
	private int sidoCode;
	private String sidoName;

	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
	
}
