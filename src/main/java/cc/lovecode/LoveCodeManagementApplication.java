package cc.lovecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoveCodeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveCodeManagementApplication.class, args);
    }

}
