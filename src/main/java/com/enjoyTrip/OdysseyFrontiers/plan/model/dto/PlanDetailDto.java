package com.enjoyTrip.OdysseyFrontiers.plan.model.dto;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PlanDetailDto {
    private long planDetailId;
    private long planId;
    private int day;
    private LocalDateTime planTime; // 해당하는 일자 + 시간
    private int contentId;
    private String description;
    private AttractionInfo attractionInfo; // Include AttractionInfo to hold related attraction details
}