package com.apple.iphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IphoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(IphoneApplication.class, args);
    }

}
