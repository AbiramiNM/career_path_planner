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
@Table(name = "domain_skills")
public class DomainSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", nullable = false)
    private CareerDomain careerDomain;

    @Column(name = "skill_name", nullable = false, length = 200)
    private String skillName;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;

    public DomainSkill() {
    }

    public DomainSkill(CareerDomain careerDomain, String skillName, int displayOrder) {
        this.careerDomain = careerDomain;
        this.skillName = skillName;
        this.displayOrder = displayOrder;
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
