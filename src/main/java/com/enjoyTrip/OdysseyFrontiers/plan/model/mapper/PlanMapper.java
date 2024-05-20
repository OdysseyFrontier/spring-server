package com.enjoyTrip.OdysseyFrontiers.plan.model.mapper;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDetailDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    List<PlanDto> searchPlans(int contentTypeId, int sidoCode, int gugunCode, String keyword);

    void insertPlan(PlanDto planDto);

    void insertPlanDetail(PlanDetailDto planDetailDto);

    PlanDto getPlan(long planId);

    void updatePlan(PlanDto planDto);

    void deletePlan(long planId);

    void deletePlanDetails(long planId);
}