package com.imsoft.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ForexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForexApplication.class, args);
    }

}