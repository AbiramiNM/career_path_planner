package com.careerpathplanner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerpathplanner.dto.DomainComparisonResponse;
import com.careerpathplanner.dto.DomainDetailResponse;
import com.careerpathplanner.service.DomainDetailService;

@RestController
@RequestMapping("/api/domains")
@CrossOrigin(origins = "*")
public class DomainDetailController {

    private final DomainDetailService domainDetailService;

    public DomainDetailController(DomainDetailService domainDetailService) {
        this.domainDetailService = domainDetailService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listDomains() {
        return ResponseEntity.ok(domainDetailService.listAllDomainNames());
    }

    @GetMapping("/details")
    public ResponseEntity<DomainDetailResponse> getDetails(@RequestParam("name") String domainName) {
        DomainDetailResponse details = domainDetailService.getDetailsByDomainName(domainName);
        if (details == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(details);
    }

    @GetMapping("/compare")
    public ResponseEntity<DomainComparisonResponse> compare(
            @RequestParam("domain1") String domain1,
            @RequestParam("domain2") String domain2) {
        DomainComparisonResponse comparison = domainDetailService.getComparison(domain1, domain2);
        if (comparison == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comparison);
    }
}
