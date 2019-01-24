package com.example.spsdemowmx.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @Description TODO
 * @Author wumengxuan
 * @Date 2019/1/23 15:01
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class loginControllerTest {
    @Autowired
    WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    /**
     * 测试管理员能登陆成功
     * @throws Exception
     */

    @Test
    public void testLoginSuccess() throws Exception{
        FormLoginRequestBuilder login = formLogin().user("wumengxuan").password("111111");
        // 为什么是 is3xxRedirection() ? REDIRECTION 而不是 success ?
        mockMvc.perform(login)
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated());
    }

    /**
     * 测试密码不对是否能登陆成功
     * @throws Exception
     */
    @Test
    public void  testLoginFail() throws Exception{
        FormLoginRequestBuilder login = formLogin().user("wumengxuan").password("777777");
        mockMvc.perform(login).andExpect(unauthenticated());
    }

}
