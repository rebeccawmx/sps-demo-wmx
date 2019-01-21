package com.example.spsdemowmx.repository;

import com.example.spsdemowmx.domian.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}