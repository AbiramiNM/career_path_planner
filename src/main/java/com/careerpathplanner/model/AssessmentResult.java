package com.careerpathplanner.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "assessment_results")
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "primary_domain")
    private String primaryDomain;

    @Column(name = "other_suitable_domains")
    private String otherSuitableDomains;

    private LocalDateTime createdAt;

    @Lob
    @Column(name = "raw_scores")
    private String rawScoresJson;

    public AssessmentResult() {
    }

    public AssessmentResult(String name,
                            String email,
                            String primaryDomain,
                            String otherSuitableDomains,
                            String rawScoresJson) {
        this.name = name;
        this.email = email;
        this.primaryDomain = primaryDomain;
        this.otherSuitableDomains = otherSuitableDomains;
        this.rawScoresJson = rawScoresJson;
        this.createdAt = LocalDateTime.now();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryDomain() {
        return primaryDomain;
    }

    public void setPrimaryDomain(String primaryDomain) {
        this.primaryDomain = primaryDomain;
    }

    public String getOtherSuitableDomains() {
        return otherSuitableDomains;
    }

    public void setOtherSuitableDomains(String otherSuitableDomains) {
        this.otherSuitableDomains = otherSuitableDomains;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRawScoresJson() {
        return rawScoresJson;
    }

    public void setRawScoresJson(String rawScoresJson) {
        this.rawScoresJson = rawScoresJson;
    }
}

