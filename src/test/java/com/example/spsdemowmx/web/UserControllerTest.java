package com.example.spsdemowmx.web;


import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import com.example.spsdemowmx.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    /*
    * 注入测试对象
    */
    @Autowired WebApplicationContext context;
    @Autowired UserController userController;
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

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
    @WithMockUser(roles = "ADMIN")
    public void create() throws Exception {
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"username\":\"temp\", \"password\":\"$2a$10$rKbNOIC77Fg8N7KQ/sYoFecEkLokTJt9k7rY5I8MNyCRyMnAMZwrO\"}"))
                .andExpect(status().isOk()).andDo(print());

        assertEquals(12, userController.findAll().size());

        //userController.delete(userRepository.findOneByUsername("temp").getId());
    }

    /**
     * 测试分页
     */
    @Test
    public void findUsers() {
        List<User> users;
        users = userController.findUsers(null, null).getContent();
        assertEquals(11, users.size());

        users = userController.findUsers("wumengxuan", null).getContent();
        assertEquals(1, users.size());

        users = userController.findUsers("lgh", null).getContent();
        assertEquals(0, users.size());

        users = userController.findUsers(null, PageRequest.of(3, 10)).getContent();
        assertEquals(0, users.size());

        users = userController.findUsers(null, PageRequest.of(2, 10)).getContent();
        assertEquals(0, users.size());

        users = userController.findUsers(null, PageRequest.of(1, 10)).getContent();
        assertEquals(1, users.size());

        users = userController.findUsers(null, PageRequest.of(0, 10)).getContent();
        assertEquals(10, users.size());

    }

    /**
     * 测试修改密码
     */
    @Test
    public void changePassword() throws Exception{

        // 验证密码是否正确
        FormLoginRequestBuilder login = formLogin().user("wumengxuan").password("111111");
        mvc.perform(login).andExpect(unauthenticated());

        //修改密码
        User wumengxuan = userRepository.findOneByUsername("wumengxuan");
        userService.changePassword(wumengxuan,"111111","777777");

        // 验证修改过后的密码
        login = formLogin().user("wumengxuan").password("777777");
        mvc.perform(login).andExpect(unauthenticated());

    }

}
