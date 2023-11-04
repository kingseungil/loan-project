package com.loan;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.loan", "com.loan.core"})
@EnableCaching
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrganizationApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "organization"));
        app.run(args);
    }
}