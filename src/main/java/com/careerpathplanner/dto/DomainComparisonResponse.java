package com.careerpathplanner.dto;

import java.util.ArrayList;
import java.util.List;

public class DomainComparisonResponse {

    private String domain1Name;
    private String domain2Name;
    private DomainCompareBlock domain1 = new DomainCompareBlock();
    private DomainCompareBlock domain2 = new DomainCompareBlock();

    public DomainComparisonResponse() {
    }

    public String getDomain1Name() {
        return domain1Name;
    }

    public void setDomain1Name(String domain1Name) {
        this.domain1Name = domain1Name;
    }

    public String getDomain2Name() {
        return domain2Name;
    }

    public void setDomain2Name(String domain2Name) {
        this.domain2Name = domain2Name;
    }

    public DomainCompareBlock getDomain1() {
        return domain1;
    }

    public void setDomain1(DomainCompareBlock domain1) {
        this.domain1 = domain1;
    }

    public DomainCompareBlock getDomain2() {
        return domain2;
    }

    public void setDomain2(DomainCompareBlock domain2) {
        this.domain2 = domain2;
    }

    public static class DomainCompareBlock {
        private List<String> requiredSkills = new ArrayList<>();
        private String workStyle;
        private String industryExposure;
        private String growthOpportunities;

        public List<String> getRequiredSkills() {
            return requiredSkills;
        }

        public void setRequiredSkills(List<String> requiredSkills) {
            this.requiredSkills = requiredSkills;
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
}
