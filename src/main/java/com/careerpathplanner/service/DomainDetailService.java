package com.careerpathplanner.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careerpathplanner.dto.DomainComparisonResponse;
import com.careerpathplanner.dto.DomainDetailResponse;
import com.careerpathplanner.model.CareerDomain;
import com.careerpathplanner.model.DomainCertification;
import com.careerpathplanner.model.DomainRoadmapStep;
import com.careerpathplanner.model.DomainSkill;
import com.careerpathplanner.repository.CareerDomainRepository;
import com.careerpathplanner.repository.DomainCertificationRepository;
import com.careerpathplanner.repository.DomainRoadmapStepRepository;
import com.careerpathplanner.repository.DomainSkillRepository;

@Service
public class DomainDetailService {

    private final CareerDomainRepository careerDomainRepository;
    private final DomainSkillRepository domainSkillRepository;
    private final DomainRoadmapStepRepository domainRoadmapStepRepository;
    private final DomainCertificationRepository domainCertificationRepository;

    public DomainDetailService(CareerDomainRepository careerDomainRepository,
                               DomainSkillRepository domainSkillRepository,
                               DomainRoadmapStepRepository domainRoadmapStepRepository,
                               DomainCertificationRepository domainCertificationRepository) {
        this.careerDomainRepository = careerDomainRepository;
        this.domainSkillRepository = domainSkillRepository;
        this.domainRoadmapStepRepository = domainRoadmapStepRepository;
        this.domainCertificationRepository = domainCertificationRepository;
    }

    public List<String> listAllDomainNames() {
        return careerDomainRepository.findAll().stream()
                .map(CareerDomain::getName)
                .collect(Collectors.toList());
    }

    public DomainDetailResponse getDetailsByDomainName(String domainName) {
        CareerDomain domain = careerDomainRepository.findByNameIgnoreCase(domainName.trim())
                .orElse(null);
        if (domain == null) {
            return null;
        }

        DomainDetailResponse response = new DomainDetailResponse();
        response.setDomainName(domain.getName());
        response.setDescription(domain.getDescription());

        List<String> skills = domainSkillRepository.findByCareerDomainIdOrderByDisplayOrderAsc(domain.getId())
                .stream()
                .map(DomainSkill::getSkillName)
                .collect(Collectors.toList());
        response.setCoreSkills(skills);

        List<DomainDetailResponse.RoadmapStepDto> roadmap = domainRoadmapStepRepository
                .findByCareerDomainIdOrderByStepOrderAsc(domain.getId())
                .stream()
                .map(s -> new DomainDetailResponse.RoadmapStepDto(
                        s.getStepOrder(),
                        s.getTitle(),
                        s.getDescription()))
                .collect(Collectors.toList());
        response.setRoadmap(roadmap);

        List<DomainDetailResponse.CertificationDto> certs = domainCertificationRepository
                .findByCareerDomainId(domain.getId())
                .stream()
                .map(c -> new DomainDetailResponse.CertificationDto(
                        c.getName(),
                        c.getDescription(),
                        c.getLevel()))
                .collect(Collectors.toList());
        response.setCertifications(certs);

        return response;
    }

    public DomainComparisonResponse getComparison(String domain1Name, String domain2Name) {
        CareerDomain d1 = careerDomainRepository.findByNameIgnoreCase(domain1Name.trim()).orElse(null);
        CareerDomain d2 = careerDomainRepository.findByNameIgnoreCase(domain2Name.trim()).orElse(null);
        if (d1 == null || d2 == null) {
            return null;
        }

        DomainComparisonResponse response = new DomainComparisonResponse();
        response.setDomain1Name(d1.getName());
        response.setDomain2Name(d2.getName());

        fillCompareBlock(response.getDomain1(), d1);
        fillCompareBlock(response.getDomain2(), d2);

        return response;
    }

    private void fillCompareBlock(DomainComparisonResponse.DomainCompareBlock block, CareerDomain domain) {
        block.setRequiredSkills(
                domainSkillRepository.findByCareerDomainIdOrderByDisplayOrderAsc(domain.getId())
                        .stream()
                        .map(DomainSkill::getSkillName)
                        .collect(Collectors.toList()));
        block.setWorkStyle(domain.getWorkStyle());
        block.setIndustryExposure(domain.getIndustryExposure());
        block.setGrowthOpportunities(domain.getGrowthOpportunities());
    }
}
