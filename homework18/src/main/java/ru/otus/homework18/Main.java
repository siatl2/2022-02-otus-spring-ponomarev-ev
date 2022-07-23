package ru.otus.homework18;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableMongock
@EnableReactiveMongoRepositories
@SpringBootApplication
@EnableCircuitBreaker
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

