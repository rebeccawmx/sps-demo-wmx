package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.Organization;
import com.example.spsdemowmx.repository.OrganizationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value ="组织")
class OrganizationController {
    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    @ApiOperation(value = "组织信息列表", notes = "")
    @GetMapping("/organizations")
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @ApiOperation(value = "获取组织信息", notes = "通过 ID 查看组织")
    @ApiImplicitParam(name = "organizations", value = "组织详细实体 organization", required = true, dataType = "Organization")
    @GetMapping("/organizations/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        return organizationRepository.findById(id).orElse(null);
    }
}