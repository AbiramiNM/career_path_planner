package com.careerpathplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerpathplanner.model.DomainCertification;

@Repository
public interface DomainCertificationRepository extends JpaRepository<DomainCertification, Long> {

    List<DomainCertification> findByCareerDomainId(Long domainId);
}
