package com.software.eventplanning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan
@EnableWebMvc
public class EventPlanningApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventPlanningApplication.class, args);
    }

}
