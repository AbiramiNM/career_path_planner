package com.careerpathplanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerpathplanner.model.CareerDomain;

@Repository
public interface CareerDomainRepository extends JpaRepository<CareerDomain, Long> {

    Optional<CareerDomain> findByNameIgnoreCase(String name);
}
