
package com.careerpathplanner.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CareerAssessmentRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 150)
    private String email;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestTechnology;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestCoreEngineering;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestGovernmentStability;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestBusinessCommerce;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestCreativeArts;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestHandsOnTrades;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer interestSocialImpact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getInterestTechnology() {
        return interestTechnology;
    }

    public void setInterestTechnology(Integer interestTechnology) {
        this.interestTechnology = interestTechnology;
    }

    public Integer getInterestCoreEngineering() {
        return interestCoreEngineering;
    }

    public void setInterestCoreEngineering(Integer interestCoreEngineering) {
        this.interestCoreEngineering = interestCoreEngineering;
    }

    public Integer getInterestGovernmentStability() {
        return interestGovernmentStability;
    }

    public void setInterestGovernmentStability(Integer interestGovernmentStability) {
        this.interestGovernmentStability = interestGovernmentStability;
    }

    public Integer getInterestBusinessCommerce() {
        return interestBusinessCommerce;
    }

    public void setInterestBusinessCommerce(Integer interestBusinessCommerce) {
        this.interestBusinessCommerce = interestBusinessCommerce;
    }

    public Integer getInterestCreativeArts() {
        return interestCreativeArts;
    }

    public void setInterestCreativeArts(Integer interestCreativeArts) {
        this.interestCreativeArts = interestCreativeArts;
    }

    public Integer getInterestHandsOnTrades() {
        return interestHandsOnTrades;
    }

    public void setInterestHandsOnTrades(Integer interestHandsOnTrades) {
        this.interestHandsOnTrades = interestHandsOnTrades;
    }

    public Integer getInterestSocialImpact() {
        return interestSocialImpact;
    }

    public void setInterestSocialImpact(Integer interestSocialImpact) {
        this.interestSocialImpact = interestSocialImpact;
    }
}

