package com.enjoyTrip.OdysseyFrontiers.plan.controller;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDetailDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/plan")
@AllArgsConstructor
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @GetMapping("/search/{contentTypeId}/{sidoCode}/{gugunCode}/{keyword}")
    public List<PlanDto> searchPlans(
            @PathVariable("contentTypeId") int contentTypeId,
            @PathVariable("sidoCode") int sidoCode,
            @PathVariable("gugunCode") int gugunCode,
            @PathVariable("keyword") String keyword) {
        System.out.println(contentTypeId + " " + sidoCode + " " + gugunCode + " " + keyword);
        System.out.println("searching");
        if (keyword.equals("no")) keyword = null;
        List<PlanDto> planDtos = planService.searchPlans(contentTypeId, sidoCode, gugunCode, keyword);
        for (PlanDto planDto : planDtos) {
            System.out.println(planDto);
        }
        return planDtos;
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPlan() {
        List<PlanDto> list = planService.searchPlans(0, 0, 0, "");
        return ResponseEntity.ok(list);
    }


    @GetMapping("/myMakePlans/{memberId}")
    public ResponseEntity<List<PlanDto>> makeMyPlans(@PathVariable Long memberId) {
        try {
            System.out.println("make");
            List<PlanDto> plans = planService.getPlansMadeByMember(memberId);
            System.out.println(plans);
            return ResponseEntity.ok(plans);
        } catch (Exception e) {
            log.error("Failed to fetch plans made by member: {}", memberId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/myLikePlans/{memberId}")
    public ResponseEntity<List<PlanDto>> likeMyPlans(@PathVariable Long memberId) {
        try {
            System.out.println("like");
            List<PlanDto> plans = planService.getLikedPlansByMember(memberId);
            System.out.println(plans);
            return ResponseEntity.ok(plans);
        } catch (Exception e) {
            log.error("Failed to fetch liked plans by member: {}", memberId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createPlan(@RequestBody PlanDto planDto) throws Exception {
        log.info("create {}", planDto);
        log.info("member {}", planDto.getMemberId());
        planService.createPlan(planDto);
        return ResponseEntity.ok("Plan created successfully");
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanDto> getPlan(@PathVariable("planId") long planId) {
        PlanDto planDto = planService.getPlan(planId);
        System.out.println(planDto);
        return ResponseEntity.ok(planDto);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlan(@PathVariable("planId") long planId, @RequestBody PlanDto planDto) throws Exception {
        planDto.setPlanId(planId);
        System.out.println(planDto);
        planService.updatePlan(planDto);
        return ResponseEntity.ok("Plan updated successfully");
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable("planId") long planId) throws Exception {
        planService.deletePlan(planId);
        return ResponseEntity.ok("Plan deleted successfully");
    }
}
