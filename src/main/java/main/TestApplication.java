package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Random;

@EnableAutoConfiguration
@Configuration
@EnableWebMvc
@ComponentScan
public class TestApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
