package com.example.spsdemowmx.web;


import com.example.spsdemowmx.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    /*
    * 注入测试对象
    */
    @Autowired
    WebApplicationContext context;
    @Autowired UserController userController;
    @Autowired UserRepository userRepository;

    private MockMvc mvc;

    /*
    * 初始化，模拟加载 mvc 的 web 测试环境
    * 指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc
    */
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /*
    * 测试 Service层 indAll ,查找所有用户
    */
    @Test
    public void findAll(){
        assertEquals(3, userRepository.findAll().size());
    }

    /*
    * 测试 Web 层 findAll ,查找所有用户
    */
    @Test
    public void mockFindAll() throws Exception{
        mvc.perform(get("/api/users")).andExpect(status().isOk()).andDo(print());
    }

    /*
    * 测试 web 层插入数据，测试结束后删除
    */
    @Test
    public void create() throws Exception {
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"username\":\"temp\", \"password\":\"777777\"}"))
                .andExpect(status().isOk()).andDo(print());

        assertEquals(4, userController.findAll().size());

        userController.delete(userRepository.findOneByUsername("temp").getId());
    }

}
