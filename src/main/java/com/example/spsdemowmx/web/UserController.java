package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.domian.UserMapper;
import com.example.spsdemowmx.service.UserService;
import com.example.spsdemowmx.service.dto.PasswordChangeDTO;
import com.example.spsdemowmx.web.VM.LoginVM;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "创建用户", notes = "通过 User 对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体 user", required = true, dataType = "User")
    @PostMapping("/users")
    public User create(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "更新用户", notes = "通过 User 对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户 ID", required = true, dataType = "User")
    @PutMapping("/users")
    public void update(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "更新用户密码", notes = "比对密码进行更改")
    @ApiResponses({
            @ApiResponse(code = 200, message = "更新成功", response = User.class),
            @ApiResponse(code = 408, message = "密码不合规"),
            @ApiResponse(code = 404, message = "没有找到用户id或角色id")
    })
    @PutMapping("/users/{userID}/password")
    public void changePassword(@PathVariable("userID") Long id, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.findOne(id).ifPresent(
                user -> userService.changePassword(user, passwordChangeDTO.getCurrentPassword(), passwordChangeDTO.getNewPassword())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "删除用户", notes = "通过 ID 删除用户")
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @ApiOperation(value = "查询用户（分页）", notes = "")
    @GetMapping("/users/pages")
    public Page<User> findUsers(@RequestParam(value = "keyword", required = false) String keyword, @PageableDefault Pageable pageable) {
        return userService.queryUsers(keyword, pageable);
    }

    @ApiOperation(value = "用户登陆", notes = "")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginVM loginVm) {
        return ResponseEntity.ok(UserMapper.INSTANCE.loginVmToUser(loginVm));
    }

}