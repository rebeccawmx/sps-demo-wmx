package com.example.spsdemowmx.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String password;

    // 增加关联关系：用户--组织
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "org.id")
    Organization organization;

    // 增加关联关系：用户--角色
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",inverseJoinColumns = @JoinColumn(name = "role_code"))
    Set<Role> roles;

}