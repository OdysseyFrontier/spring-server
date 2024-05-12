package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.mapper.PlanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;

    private PlanServiceImpl(PlanMapper PlanMapper) {
        this.planMapper = PlanMapper;
    }

    @Override
    public void createPlan(PlanDto planDto) {
        planMapper.createPlan(planDto);
    }

    @Override
    public List<PlanDto> listPlan(Map<String, Object> map) {
        return planMapper.listPlan();
    }

    @Override
    public PlanDto getPlan(int planNo) {
        return planMapper.getPlan(planNo);
    }

    @Override
    public void modifyPlan(int planNo, PlanDto planDto) {
        planMapper.modifyPlan(planNo, planDto);
    }

    @Override
    public void deletePlan(int planNo) {
        planMapper.deletePlan(planNo);
    }
}
