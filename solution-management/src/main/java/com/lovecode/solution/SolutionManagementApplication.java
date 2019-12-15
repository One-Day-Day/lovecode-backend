package com.lovecode.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SolutionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionManagementApplication.class, args);
    }

}
