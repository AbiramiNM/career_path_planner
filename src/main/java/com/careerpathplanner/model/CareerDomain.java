package com.careerpathplanner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "career_domains")
public class CareerDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "work_style", length = 1000)
    private String workStyle;

    @Column(name = "industry_exposure", length = 1000)
    private String industryExposure;

    @Column(name = "growth_opportunities", length = 1000)
    private String growthOpportunities;

    public CareerDomain() {
    }

    public CareerDomain(String name, String description, String workStyle, String industryExposure, String growthOpportunities) {
        this.name = name;
        this.description = description;
        this.workStyle = workStyle;
        this.industryExposure = industryExposure;
        this.growthOpportunities = growthOpportunities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(String workStyle) {
        this.workStyle = workStyle;
    }

    public String getIndustryExposure() {
        return industryExposure;
    }

    public void setIndustryExposure(String industryExposure) {
        this.industryExposure = industryExposure;
    }

    public String getGrowthOpportunities() {
        return growthOpportunities;
    }

    public void setGrowthOpportunities(String growthOpportunities) {
        this.growthOpportunities = growthOpportunities;
    }
}
