package com.careerpathplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerpathplanner.model.DomainRoadmapStep;

@Repository
public interface DomainRoadmapStepRepository extends JpaRepository<DomainRoadmapStep, Long> {

    List<DomainRoadmapStep> findByCareerDomainIdOrderByStepOrderAsc(Long domainId);
}
