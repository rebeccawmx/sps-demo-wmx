package com.example.spsdemowmx.service;

import com.example.spsdemowmx.domian.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired UserService userService;

    /*
    * 测试 saveOrUpdate 是否能插入数据
    */
    @Test
    public void saveOrUpdate(){

        User tempUser = new User(null,"testUsers","000000",null,null);
        userService.saveOrUpdate(tempUser);
        assertEquals(12,userService.findAll().size());

        userService.delete(tempUser.getId());
    }
}
