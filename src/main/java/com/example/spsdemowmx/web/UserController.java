package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import com.example.spsdemowmx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "用户", description = "用户 API")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户列表信息", notes = "")
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "通过 url 的 id 来获取用户的详细信息")
    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findOne(id).orElse(null);
    }

    @ApiOperation(value = "创建用户", notes = "通过 User 对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体 user", required = true, dataType = "User")
    @PostMapping("/users")
    public User create(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @ApiOperation(value = "更新用户", notes = "通过 User 对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户 ID", required = true, dataType = "User")
    @PutMapping("/users")
    public void update(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "删除用户", notes = "通过 ID 删除用户")
    @ApiImplicitParam(name = "user", value = "用户 ID", required = true, dataType = "Long")
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}