package com.example.spsdemowmx.web;

import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> Users() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }

    @PostMapping("/user")
    public void save(@RequestBody User user){
        userRepository.save(user);
    }
    @PutMapping("/users")
    public void update(@RequestBody User user){
        userRepository.save(user);
    }
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }

}
