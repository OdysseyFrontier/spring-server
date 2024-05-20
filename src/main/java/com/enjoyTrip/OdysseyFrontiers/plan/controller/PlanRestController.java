package com.enjoyTrip.OdysseyFrontiers.plan.controller;

import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
import com.enjoyTrip.OdysseyFrontiers.plan.model.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanRestController {

    private PlanService planService;

    @GetMapping("/search/{contentTypeId}/{sidoCode}/{gugunCode}/{keyword}")
    public List<PlanDto> searchPlans(
            @PathVariable("contentTypeId") int contentTypeId,
            @PathVariable("sidoCode") int sidoCode,
            @PathVariable("gugunCode") int gugunCode,
            @PathVariable("keyword") String keyword) {
        return planService.searchPlans(contentTypeId, sidoCode, gugunCode, keyword);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPlan() {
        List<PlanDto> list = planService.searchPlans(0, 0, 0, "");
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPlan(@RequestBody PlanDto planDto) throws Exception {
        planService.createPlan(planDto);
        return ResponseEntity.ok("Plan created successfully");
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanDto> getPlan(@PathVariable("planId") long planId) {
        PlanDto planDto = planService.getPlan(planId);
        return ResponseEntity.ok(planDto);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlan(@PathVariable("planId") long planId, @RequestBody PlanDto planDto) throws Exception {
        planDto.setPlanId(planId);
        planService.updatePlan(planDto);
        return ResponseEntity.ok("Plan updated successfully");
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable("planId") long planId) throws Exception {
        planService.deletePlan(planId);
        return ResponseEntity.ok("Plan deleted successfully");
    }
}
