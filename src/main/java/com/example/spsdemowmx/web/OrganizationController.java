package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.Organization;
import com.example.spsdemowmx.repository.OrganizationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

class OrganizationController {
    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    @GetMapping("/organizations")
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @GetMapping("/organizations/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        return organizationRepository.findById(id).orElse(null);
    }
}