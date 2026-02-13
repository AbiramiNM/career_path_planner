package com.careerpathplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerpathplanner.model.DomainSkill;

@Repository
public interface DomainSkillRepository extends JpaRepository<DomainSkill, Long> {

    List<DomainSkill> findByCareerDomainIdOrderByDisplayOrderAsc(Long domainId);
}
