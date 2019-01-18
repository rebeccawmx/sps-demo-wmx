package com.example.spsdemowmx.repository;

import com.example.spsdemowmx.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
