package com.concurrency.currency01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.concurrency.currency01.mapper")
public class Currency01Application {
    public static void main(String[] args) {
        SpringApplication.run(Currency01Application.class, args);
    }
}
