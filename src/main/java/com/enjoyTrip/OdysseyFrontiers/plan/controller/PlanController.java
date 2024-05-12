package com.enjoyTrip.OdysseyFrontiers.plan.controller;

import com.enjoyTrip.OdysseyFrontiers.plan.model.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlanController {

    private PlanService planService;

    public PlanController(PlanService planService) {
        super();
        this.planService = planService;
    }

    @GetMapping("/")
    public String plan() {
        return "/plan/mainPlan";
    }
}
