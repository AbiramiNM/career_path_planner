package com.careerpathplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerpathplanner.model.AssessmentResult;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
}

