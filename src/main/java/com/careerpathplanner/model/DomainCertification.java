package com.careerpathplanner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "domain_certifications")
public class DomainCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", nullable = false)
    private CareerDomain careerDomain;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String level; // e.g. Beginner, Intermediate, Advanced

    public DomainCertification() {
    }

    public DomainCertification(CareerDomain careerDomain, String name, String description, String level) {
        this.careerDomain = careerDomain;
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public CareerDomain getCareerDomain() {
        return careerDomain;
    }

    public void setCareerDomain(CareerDomain careerDomain) {
        this.careerDomain = careerDomain;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
