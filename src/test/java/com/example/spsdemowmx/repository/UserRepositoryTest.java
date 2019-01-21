package com.example.spsdemowmx.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    /*
    * 测试 findAll
    */
    @Test
    public void testFindAll() {
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void testFindById() {
        assertEquals("wumengxuan", userRepository.findById(1L).get().getUsername());
    }

}
