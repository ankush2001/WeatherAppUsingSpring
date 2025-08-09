package com.application.weatherappbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class WeatherAppBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppBasicApplication.class, args);
    }

}
