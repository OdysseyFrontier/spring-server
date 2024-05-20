package com.enjoyTrip.OdysseyFrontiers.plan.model.dto;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanDetailDto {
    private long planDetailId;
    private long planId;
    private String day;
    private int contentId;
    private String description;
    private String planTime;
    private AttractionInfo attractionInfo; // Include AttractionInfo to hold related attraction details
}