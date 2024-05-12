package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
    void createPlan(PlanDto planDto);

    List<PlanDto> listPlan(Map<String, Object> map);

    PlanDto getPlan(int planNo);

    void modifyPlan(int planId, PlanDto planDto);

    void deletePlan(int planNo);
}
