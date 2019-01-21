package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.Role;
import com.example.spsdemowmx.repository.RoleRepository;
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
@Api(value = "角色")
class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @ApiOperation(value = "获取角色列表", notes = "")
    @GetMapping("/roles")
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @ApiOperation(value = "获取角色详细信息",notes = "通过 ID 查看角色")
    @ApiImplicitParam(name = "role", value = "角色详细实体 role", required = true, dataType = "Role")
    @GetMapping("/roles/{code}")
    public Role findById(@PathVariable("code") String code) {
        return roleRepository.findById(code).orElse(null);
    }
}