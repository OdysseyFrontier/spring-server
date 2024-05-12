package com.enjoyTrip.OdysseyFrontiers.plan.model.mapper;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    void createPlan(PlanDto planDto);

    List<PlanDto> listPlan();

    PlanDto getPlan(int planNo);

    void modifyPlan(int planNo, PlanDto planDto);

    void deletePlan(int planNo);
}
