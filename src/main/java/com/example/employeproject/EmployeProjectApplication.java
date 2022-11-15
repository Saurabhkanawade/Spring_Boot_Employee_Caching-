package com.example.employeproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@Log4j2
@EnableCaching
@EnableSpringDataWebSupport
public class EmployeProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeProjectApplication.class, args);

 }
}

