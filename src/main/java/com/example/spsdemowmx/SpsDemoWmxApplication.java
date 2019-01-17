package com.example.spsdemowmx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wumengxuan
 */

@SpringBootApplication
public class SpsDemoWmxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpsDemoWmxApplication.class, args);
    }

}

@RestController
class HelloController{
    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello, Mengxuan Wu!";
    }


}
