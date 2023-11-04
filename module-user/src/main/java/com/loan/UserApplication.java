package com.loan;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.loan", "com.loan.core"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "user"));
        app.run(args);
    }
}
