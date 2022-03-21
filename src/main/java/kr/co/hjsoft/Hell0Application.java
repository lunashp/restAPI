package kr.co.hjsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Hell0Application {

    public static void main(String[] args) {
        SpringApplication.run(Hell0Application.class, args);
    }

}