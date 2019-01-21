package com.example.spsdemowmx;

import com.example.spsdemowmx.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

import java.util.List;


/**
 * @author wumengxuan
 */

@SpringBootApplication
public class SpsDemoWmxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpsDemoWmxApplication.class, args);
    }

}