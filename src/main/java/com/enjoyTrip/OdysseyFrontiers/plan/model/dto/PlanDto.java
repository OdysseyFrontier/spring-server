package com.enjoyTrip.OdysseyFrontiers.plan.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.AccessType;
import com.enjoyTrip.OdysseyFrontiers.util.Season;

public class PlanDto {
    private int planId;
    private String memberId;
    private String memberName;
    private String title;
    private String description;
    private Season season;
    private AccessType accessType;
    private String startTime;
    private String recentUpdateTime;


    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(String recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }

    @Override
    public String toString() {
        return "PlanDto{" +
                "planId=" + planId +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", season=" + season +
                ", accessType=" + accessType +
                ", startTime='" + startTime + '\'' +
                ", recentUpdateTime='" + recentUpdateTime + '\'' +
                '}';
    }
}
