package com.enjoyTrip.OdysseyFrontiers.stat.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class StatDto {
	private int content_id;
	private String time_slot;
	private String activity_count;
	private String registration_date;
	private String member_count;
	private String title;
	private String hit_count;
	private String sido_name;
	private String gugun_name;
	private String attraction_count;
	
}
