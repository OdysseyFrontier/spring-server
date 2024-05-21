package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
    void createPlan(PlanDto planDto);

    PlanDto getPlan(long planNo);

    void updatePlan(PlanDto planDto);

    void deletePlan(long planNo);

    List<PlanDto> searchPlans(int contentTypeId, int sidoCode, int gugunCode, String keyword);

    List<PlanDto> getPlansMadeByMember(Long memberId);

    List<PlanDto> getLikedPlansByMember(Long memberId);
}
