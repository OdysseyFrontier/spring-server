package com.enjoyTrip.OdysseyFrontiers.plan.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.AccessType;
import com.enjoyTrip.OdysseyFrontiers.util.Season;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanDto {
    private int planId;
    private long memberId;
    private String name;
    private String title;
    private String description;
    private Season season;
    private AccessType accessType;
    private String startTime;
    private String recentUpdateTime;


}
