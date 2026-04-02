package com.careerpathplanner.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careerpathplanner.dto.CareerAssessmentRequest;
import com.careerpathplanner.dto.CareerRecommendationResponse;
import com.careerpathplanner.model.AssessmentResult;
import com.careerpathplanner.repository.AssessmentResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CareerRuleEngineService {

    private final AssessmentResultRepository assessmentResultRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CareerRuleEngineService(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    public CareerRecommendationResponse assessAndRecommend(CareerAssessmentRequest request) {
        Map<String, Integer> scores = calculateDomainScores(request);

        // Find primary domain (highest score)
        String primaryDomain = scores.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Exploration Needed");

        // Other domains within 1 point of the maximum are also suitable
        int maxScore = scores.get(primaryDomain);
        List<String> otherDomains = scores.entrySet().stream()
                .filter(e -> !e.getKey().equals(primaryDomain))
                .filter(e -> maxScore - e.getValue() <= 1)
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        String explanation = buildExplanation(primaryDomain, scores);
        List<String> careers = suggestedCareersFor(primaryDomain);
        List<String> nextSteps = suggestedNextStepsFor(primaryDomain);

        // Persist result
        saveResult(request, primaryDomain, otherDomains, scores);

        return new CareerRecommendationResponse(primaryDomain, otherDomains, explanation, careers, nextSteps);
    }

    private Map<String, Integer> calculateDomainScores(CareerAssessmentRequest request) {
        Map<String, Integer> scores = new HashMap<>();

        // Initialize domains
        scores.put("IT & Software", 0);
        scores.put("Core Engineering", 0);
        scores.put("Government & Public Sector", 0);
        scores.put("Business & Commerce", 0);
        scores.put("Creative & Media", 0);
        scores.put("Skilled Trades & Technical", 0);
        scores.put("Social / NGO & Education", 0);

        // Rule-based scoring (simple, transparent weights)

        // Technology interest
        addScore(scores, "IT & Software", request.getInterestTechnology() * 2);
        addScore(scores, "Core Engineering", request.getInterestTechnology());

        // Core engineering / machinery
        addScore(scores, "Core Engineering", request.getInterestCoreEngineering() * 2);
        addScore(scores, "Skilled Trades & Technical", request.getInterestCoreEngineering());

        // Government / stability
        addScore(scores, "Government & Public Sector", request.getInterestGovernmentStability() * 2);
        addScore(scores, "Social / NGO & Education", request.getInterestGovernmentStability());

        // Business / commerce
        addScore(scores, "Business & Commerce", request.getInterestBusinessCommerce() * 2);
        addScore(scores, "IT & Software", request.getInterestBusinessCommerce());

        // Creative / arts
        addScore(scores, "Creative & Media", request.getInterestCreativeArts() * 2);
        addScore(scores, "Business & Commerce", request.getInterestCreativeArts());

        // Hands-on trades
        addScore(scores, "Skilled Trades & Technical", request.getInterestHandsOnTrades() * 2);
        addScore(scores, "Core Engineering", request.getInterestHandsOnTrades());

        // Social impact / helping others
        addScore(scores, "Social / NGO & Education", request.getInterestSocialImpact() * 2);
        addScore(scores, "Government & Public Sector", request.getInterestSocialImpact());

        return scores;
    }

    private void addScore(Map<String, Integer> scores, String domain, int value) {
        scores.put(domain, scores.getOrDefault(domain, 0) + value);
    }

    private String buildExplanation(String primaryDomain, Map<String, Integer> scores) {
        List<String> reasons = new ArrayList<>();

        switch (primaryDomain) {
            case "IT & Software" -> reasons.add("You show strong interest in technology, problem solving and logical work.");
            case "Core Engineering" -> reasons.add("You are inclined towards machines, physical systems and analytical thinking.");
            case "Government & Public Sector" -> reasons.add("You value stability, structured work and contributing to public services.");
            case "Business & Commerce" -> reasons.add("You are interested in money management, business, and decision making.");
            case "Creative & Media" -> reasons.add("You enjoy expressing ideas visually or through content and creativity.");
            case "Skilled Trades & Technical" -> reasons.add("You are comfortable with practical, hands-on work and technical skills.");
            case "Social / NGO & Education" -> reasons.add("You like helping others, guiding people and contributing to society.");
            default -> reasons.add("Your interests are spread across multiple areas; you may explore different domains in more detail.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.join(" ", reasons));
        builder.append(" (Internal scores were calculated in a simple rule-based way without judging marks or performance.)");

        return builder.toString();
    }

    private List<String> suggestedCareersFor(String primaryDomain) {
        return switch (primaryDomain) {
            case "IT & Software" -> List.of("Software Developer", "Web Developer", "Data Analyst", "QA Engineer", "System Administrator");
            case "Core Engineering" -> List.of("Mechanical Engineer", "Electrical Engineer", "Civil Engineer", "Production Engineer", "Design Engineer");
            case "Government & Public Sector" -> List.of("UPSC / Civil Services", "Bank PO / Clerk", "Railway Jobs", "Public Sector Engineer", "Defence Services");
            case "Business & Commerce" -> List.of("Chartered Accountant", "Business Analyst", "Marketing Executive", "Entrepreneur", "Financial Planner");
            case "Creative & Media" -> List.of("Graphic Designer", "UI/UX Designer", "Content Creator", "Animator", "Media & Communication Professional");
            case "Skilled Trades & Technical" -> List.of("Electrician", "Automobile Technician", "CNC Operator", "Network Technician", "Field Service Engineer");
            case "Social / NGO & Education" -> List.of("Teacher / Lecturer", "Counsellor", "Social Worker", "NGO Coordinator", "Training & Development Professional");
            default -> List.of("Explore multiple domains through internships, workshops and short-term courses.");
        };
    }

    private List<String> suggestedNextStepsFor(String primaryDomain) {
        List<String> steps = new ArrayList<>();

        steps.add("Talk to seniors, teachers or professionals currently working in this domain.");
        steps.add("Search for beginner friendly YouTube playlists, blogs or courses to experience the field.");
        steps.add("Try at least one small project, workshop or internship related to this domain.");

        switch (primaryDomain) {
            case "IT & Software" -> {
                steps.add("Learn basic programming (C / Java / Python) and problem solving.");
                steps.add("Practice building small web or mobile applications to understand real work.");
            }
            case "Core Engineering" -> {
                steps.add("Strengthen fundamentals in Physics and Mathematics.");
                steps.add("Participate in technical clubs, mini projects and lab work.");
            }
            case "Government & Public Sector" -> {
                steps.add("Understand different government exams (UPSC, SSC, Banking, Railways etc.).");
                steps.add("Focus on aptitude, reasoning, and general awareness preparation.");
            }
            case "Business & Commerce" -> {
                steps.add("Learn basics of accounting, economics and business studies.");
                steps.add("Observe how small businesses around you work in real life.");
            }
            case "Creative & Media" -> {
                steps.add("Build a simple portfolio (designs, videos, writing samples etc.).");
                steps.add("Experiment with free tools like Canva, Figma or video editors.");
            }
            case "Skilled Trades & Technical" -> {
                steps.add("Consider government ITIs, polytechnic or skill development programs.");
                steps.add("Look for apprenticeship or on-the-job training opportunities.");
            }
            case "Social / NGO & Education" -> {
                steps.add("Volunteer for NGOs, teaching programs or social initiatives.");
                steps.add("Develop communication, empathy and counselling related skills.");
            }
            default -> {
                // keep generic steps only
            }
        }

        return steps;
    }

    private void saveResult(CareerAssessmentRequest request,
                            String primaryDomain,
                            List<String> otherDomains,
                            Map<String, Integer> scores) {
        String rawScoresJson;
        try {
            rawScoresJson = objectMapper.writeValueAsString(scores);
        } catch (JsonProcessingException e) {
            rawScoresJson = scores.toString();
        }

        String otherDomainsStr = String.join(", ", otherDomains);

        AssessmentResult result = new AssessmentResult(
                request.getName(),
                request.getEmail(),
                primaryDomain,
                otherDomainsStr,
                rawScoresJson
        );

        assessmentResultRepository.save(result);
    }
}

