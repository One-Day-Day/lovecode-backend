package com.lovecode.problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProblemManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProblemManagementApplication.class, args);
    }

}
