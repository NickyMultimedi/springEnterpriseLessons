package be.multimedi.lessons.springadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BeerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class, args);

    }

    @Bean
    public String myString() {
        return "hello World";
    }
}
