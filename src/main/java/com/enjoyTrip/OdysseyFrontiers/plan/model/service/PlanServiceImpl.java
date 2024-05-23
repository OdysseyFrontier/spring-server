package com.enjoyTrip.OdysseyFrontiers.plan.model.service;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.mapper.AttractionMapper;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.service.AttractionService;
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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;
    private AttractionMapper attractionMapper;

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

        System.out.println("service " + planDto);
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
        PlanDto plan = planMapper.getPlan(planId);
        System.out.println("service get plan");
        System.out.println(plan);
        LocalDateTime latestPlanDetail = plan.getPlanDetails().stream()
                .map(PlanDetailDto::getPlanTime)
                .filter(Objects::nonNull)
                .max(Comparator.comparing(LocalDateTime::toLocalDate))
                .orElse(null);
        System.out.println(latestPlanDetail);
        if (latestPlanDetail != null) {
            plan.setEndTime(LocalDate.from(Objects.requireNonNull(latestPlanDetail)));
        }
        return plan;
    }

    @Transactional
    public void updatePlan(PlanDto planDto){
        if (planDto == null) {
            throw new IllegalArgumentException("PlanDto cannot be null");
        }
        if (planDto.getPlanDetails() == null) {
            throw new IllegalArgumentException("Plan details cannot be null");
        }

        planDto.setAccessType("public");
        LocalDate startTime = planDto.getStartTime();
        LocalDate endTime = planDto.getEndTime();
        String season = calculateSeason(startTime, endTime);
        planDto.setSeason(season);

        System.out.println("====================================");
        for (PlanDetailDto planDetail : planDto.getPlanDetails()) {
            System.out.println(planDetail);
        }
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

        LocalDate winterStart = LocalDate.of(startTime.getYear(), Month.DECEMBER, 1);
        LocalDate winterEnd = LocalDate.of(endTime.getYear() + 1, Month.FEBRUARY, 28);
        LocalDate springStart = LocalDate.of(startTime.getYear(), Month.MARCH, 1);
        LocalDate springEnd = LocalDate.of(endTime.getYear(), Month.MAY, 31);
        LocalDate summerStart = LocalDate.of(startTime.getYear(), Month.JUNE, 1);
        LocalDate summerEnd = LocalDate.of(endTime.getYear(), Month.AUGUST, 31);
        LocalDate autumnStart = LocalDate.of(startTime.getYear(), Month.SEPTEMBER, 1);
        LocalDate autumnEnd = LocalDate.of(endTime.getYear(), Month.NOVEMBER, 30);

        long winterDays = calculateDaysInSeason(startTime, endTime, winterStart, winterEnd);
        long springDays = calculateDaysInSeason(startTime, endTime, springStart, springEnd);
        long summerDays = calculateDaysInSeason(startTime, endTime, summerStart, summerEnd);
        long autumnDays = calculateDaysInSeason(startTime, endTime, autumnStart, autumnEnd);

        if (winterDays >= springDays && winterDays >= summerDays && winterDays >= autumnDays) {
            return "Winter";
        } else if (springDays >= winterDays && springDays >= summerDays && springDays >= autumnDays) {
            return "Spring";
        } else if (summerDays >= winterDays && summerDays >= springDays && summerDays >= autumnDays) {
            return "Summer";
        } else {
            return "Fall";
        }
    }

    private long calculateDaysInSeason(LocalDate startTime, LocalDate endTime, LocalDate seasonStart, LocalDate seasonEnd) {
        LocalDate actualSeasonStart = startTime.isBefore(seasonStart) ? seasonStart : startTime;
        LocalDate actualSeasonEnd = endTime.isAfter(seasonEnd) ? seasonEnd : endTime;

        if (actualSeasonStart.isAfter(actualSeasonEnd)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(actualSeasonStart, actualSeasonEnd) + 1;
    }
}