package com.careerpathplanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerpathplanner.dto.CareerAssessmentRequest;
import com.careerpathplanner.dto.CareerRecommendationResponse;
import com.careerpathplanner.service.CareerRuleEngineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "*")
public class CareerRecommendationController {

    private final CareerRuleEngineService careerRuleEngineService;

    public CareerRecommendationController(CareerRuleEngineService careerRuleEngineService) {
        this.careerRuleEngineService = careerRuleEngineService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Career Path Planner backend is running");
    }

    @PostMapping("/recommend")
    public ResponseEntity<CareerRecommendationResponse> recommend(@Valid @RequestBody CareerAssessmentRequest request) {
        CareerRecommendationResponse response = careerRuleEngineService.assessAndRecommend(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

