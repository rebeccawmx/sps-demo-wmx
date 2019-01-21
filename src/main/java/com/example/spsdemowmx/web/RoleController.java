package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.Role;
import com.example.spsdemowmx.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @GetMapping("/roles/{code}")
    public Role findById(@PathVariable("code") String code) {
        return roleRepository.findById(code).orElse(null);
    }
}
