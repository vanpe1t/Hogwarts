package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HogwartsApplication {

    Logger logger = LoggerFactory.getLogger(HogwartsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(HogwartsApplication.class, args);
    }

}
