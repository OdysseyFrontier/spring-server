package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDetailDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private PlanMapper planMapper;

    public List<PlanDto> searchPlans(int contentTypeId, int sidoCode, int gugunCode, String keyword) {
        return planMapper.searchPlans(contentTypeId, sidoCode, gugunCode, keyword);
    }

    @Transactional
    public void createPlan(PlanDto planDto) {
        planMapper.insertPlan(planDto);
        for (PlanDetailDto detail : planDto.getPlanDetails()) {
            detail.setPlanId(planDto.getPlanId());
            planMapper.insertPlanDetail(detail);
        }
    }

    public PlanDto getPlan(long planId) {
        return planMapper.getPlan(planId);
    }

    @Transactional
    public void updatePlan(PlanDto planDto){
        planMapper.updatePlan(planDto);
        planMapper.deletePlanDetails(planDto.getPlanId());
        for (PlanDetailDto detail : planDto.getPlanDetails()) {
            detail.setPlanId(planDto.getPlanId());
            planMapper.insertPlanDetail(detail);
        }
    }

    @Transactional
    public void deletePlan(long planId){
        planMapper.deletePlanDetails(planId);
        planMapper.deletePlan(planId);
    }
}