package com.enjoyTrip.OdysseyFrontiers.attraction.controller;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionRestController {

    private AttractionService attractionService;

}
