package com.application.weatherappbasic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherSummary {
    private String city;
    private String country;
    private String description;
    private double temperature;
    private double humidity;
    private double windSpeed;
}
