package com.careerpathplanner.dto;

import java.util.List;

public class CareerRecommendationResponse {

    private String primaryDomain;
    private List<String> otherSuitableDomains;
    private String explanation;
    private List<String> suggestedCareers;
    private List<String> suggestedNextSteps;

    public CareerRecommendationResponse() {
    }

    public CareerRecommendationResponse(String primaryDomain,
                                        List<String> otherSuitableDomains,
                                        String explanation,
                                        List<String> suggestedCareers,
                                        List<String> suggestedNextSteps) {
        this.primaryDomain = primaryDomain;
        this.otherSuitableDomains = otherSuitableDomains;
        this.explanation = explanation;
        this.suggestedCareers = suggestedCareers;
        this.suggestedNextSteps = suggestedNextSteps;
    }

    public String getPrimaryDomain() {
        return primaryDomain;
    }

    public void setPrimaryDomain(String primaryDomain) {
        this.primaryDomain = primaryDomain;
    }

    public List<String> getOtherSuitableDomains() {
        return otherSuitableDomains;
    }

    public void setOtherSuitableDomains(List<String> otherSuitableDomains) {
        this.otherSuitableDomains = otherSuitableDomains;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getSuggestedCareers() {
        return suggestedCareers;
    }

    public void setSuggestedCareers(List<String> suggestedCareers) {
        this.suggestedCareers = suggestedCareers;
    }

    public List<String> getSuggestedNextSteps() {
        return suggestedNextSteps;
    }

    public void setSuggestedNextSteps(List<String> suggestedNextSteps) {
        this.suggestedNextSteps = suggestedNextSteps;
    }
}

