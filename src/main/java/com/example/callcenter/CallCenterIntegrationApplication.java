package com.example.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CallCenterIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallCenterIntegrationApplication.class, args);
    }
}
