package com.application.weatherappbasic.controller;

import com.application.weatherappbasic.dto.WeatherSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.application.weatherappbasic.service.WeatherService;

@RestController
@RequestMapping("/api")

public class WeatherController {


    private WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/weather")
    public ResponseEntity<WeatherSummary> getWeather(@RequestParam String city) {
        return ResponseEntity.ok(weatherService.getWeatherByCity(city));
    }
}
