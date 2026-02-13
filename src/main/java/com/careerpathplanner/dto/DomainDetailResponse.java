package com.careerpathplanner.dto;

import java.util.ArrayList;
import java.util.List;

public class DomainDetailResponse {

    private String domainName;
    private String description;
    private List<String> coreSkills = new ArrayList<>();
    private List<RoadmapStepDto> roadmap = new ArrayList<>();
    private List<CertificationDto> certifications = new ArrayList<>();

    public DomainDetailResponse() {
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCoreSkills() {
        return coreSkills;
    }

    public void setCoreSkills(List<String> coreSkills) {
        this.coreSkills = coreSkills;
    }

    public List<RoadmapStepDto> getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(List<RoadmapStepDto> roadmap) {
        this.roadmap = roadmap;
    }

    public List<CertificationDto> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationDto> certifications) {
        this.certifications = certifications;
    }

    public static class RoadmapStepDto {
        private int stepOrder;
        private String title;
        private String description;

        public RoadmapStepDto() {
        }

        public RoadmapStepDto(int stepOrder, String title, String description) {
            this.stepOrder = stepOrder;
            this.title = title;
            this.description = description;
        }

        public int getStepOrder() {
            return stepOrder;
        }

        public void setStepOrder(int stepOrder) {
            this.stepOrder = stepOrder;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class CertificationDto {
        private String name;
        private String description;
        private String level;

        public CertificationDto() {
        }

        public CertificationDto(String name, String description, String level) {
            this.name = name;
            this.description = description;
            this.level = level;
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
}
