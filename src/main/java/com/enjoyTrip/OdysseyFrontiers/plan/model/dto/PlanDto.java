package com.enjoyTrip.OdysseyFrontiers.plan.model.dto;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import com.enjoyTrip.OdysseyFrontiers.util.AccessType;
import com.enjoyTrip.OdysseyFrontiers.util.Season;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PlanDto {
    private long planId;
    private long memberId;
    private String name;
    private String title;
    private String description;
    private Season season;
    private AccessType accessType;
    private String startTime;
    private String recentUpdateTime;
    private List<PlanDetailDto> planDetails; // Add PlanDetailDto to represent plan details
}
