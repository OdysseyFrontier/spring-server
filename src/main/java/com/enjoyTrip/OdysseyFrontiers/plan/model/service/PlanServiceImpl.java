package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDetailDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    public List<PlanDto> searchPlans(int contentTypeId, int sidoCode, int gugunCode, String keyword) {
        System.out.println("service" + contentTypeId + ":" + sidoCode + ":" + gugunCode + ":" + keyword);
        return planMapper.searchPlans(contentTypeId, sidoCode, gugunCode, keyword);
    }

    public List<PlanDto> getPlansMadeByMember(Long memberId) {
        // Example method to fetch plans made by a member
        return planMapper.findPlansByMemberId(memberId);
    }

    public List<PlanDto> getLikedPlansByMember(Long memberId) {
        // Example method to fetch plans liked by a member
        return planMapper.findLikedPlansByMemberId(memberId);
    }

    @Override
    @Transactional
    public void createPlan(PlanDto planDto) {
        if (planDto == null) {
            throw new IllegalArgumentException("PlanDto cannot be null");
        }
        if (planDto.getPlanDetails() == null) {
            throw new IllegalArgumentException("Plan details cannot be null");
        }

        System.out.println(planDto);
        // start_time와 end_time을 계산해서 season에 맞는 값을 string으로 넣어주고
        planDto.setAccessType("public");
        LocalDate startTime = planDto.getStartTime();
        LocalDate endTime = planDto.getEndTime();
        String season = calculateSeason(startTime, endTime);
        planDto.setSeason(season);

        System.out.println("service" + planDto);
        planMapper.insertPlan(planDto);
        System.out.println("nice");
        for (PlanDetailDto detail : planDto.getPlanDetails()) {
            detail.setPlanId(planDto.getPlanId());
            int day = calculateDay(detail.getPlanTime(),startTime);
            detail.setDay(day);
            System.out.println(detail);

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

    private int calculateDay(LocalDateTime planTime, LocalDate startTime) {
        // 예외 처리 해줘야 할듯.
        if (planTime == null) return 0;

        // Convert planTime to LocalDate for comparison
        LocalDate planDate = planTime.toLocalDate();

        // Calculate the number of days between startTime and planDate
        long daysBetween = ChronoUnit.DAYS.between(startTime, planDate);

        // Return the difference as an int
        return ((int) daysBetween) + 1;
    }

    private String calculateSeason(LocalDate startTime, LocalDate endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }

        Month startMonth = startTime.getMonth();
        Month endMonth = endTime.getMonth();

        // Determine the season based on start and end months
        if ((startMonth == Month.DECEMBER || startMonth == Month.JANUARY || startMonth == Month.FEBRUARY) &&
                (endMonth == Month.DECEMBER || endMonth == Month.JANUARY || endMonth == Month.FEBRUARY)) {
            return "Winter";
        } else if ((startMonth == Month.MARCH || startMonth == Month.APRIL || startMonth == Month.MAY) &&
                (endMonth == Month.MARCH || endMonth == Month.APRIL || endMonth == Month.MAY)) {
            return "Spring";
        } else if ((startMonth == Month.JUNE || startMonth == Month.JULY || startMonth == Month.AUGUST) &&
                (endMonth == Month.JUNE || endMonth == Month.JULY || endMonth == Month.AUGUST)) {
            return "Summer";
        } else if ((startMonth == Month.SEPTEMBER || startMonth == Month.OCTOBER || startMonth == Month.NOVEMBER) &&
                (endMonth == Month.SEPTEMBER || endMonth == Month.OCTOBER || endMonth == Month.NOVEMBER)) {
            return "Autumn";
        } else {
            return "Mixed";
        }
    }
}