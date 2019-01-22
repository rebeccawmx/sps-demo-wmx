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
    * 测试 findAll 是否返回5个User
    */
    @Test
    public void testFindAll() {
        assertEquals(5, userRepository.findAll().size());
    }

    /*
    * 测试 findById 查找 Id = 4 的 User 是否匹配
    * */
    @Test
    public void testFindById() {
        assertEquals("wumengxuan", userRepository.findById(4L).get().getUsername());
    }

}
