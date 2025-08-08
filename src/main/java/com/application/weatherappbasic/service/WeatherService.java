package com.application.weatherappbasic.service;

import com.application.weatherappbasic.dto.WeatherSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey ;

    private final RestTemplate restTemplate = new RestTemplate() ;

    public WeatherSummary  getWeatherByCity(String city){
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&units=metric";
       Map<String, Object> response = restTemplate.getForObject(url, Map.class);

       Map<String, Object> main = (Map<String, Object>) response.get("main");
       Map<String, Object> wind = (Map<String, Object>) response.get("wind");
       Map<String, Object> sys = (Map<String, Object>) response.get("sys");
       Map<String, Object> weather = ((java.util.List<Map<String,Object>>)response.get("weather")).get(0);

       return new WeatherSummary(
               (String) response.get("name"),
               (String) sys.get("country"),
               (String) weather.get("description"),
               ((Number) main.get("temp")).doubleValue(),
               ((Number) main.get("humidity")).intValue(),
               ((Number) wind.get("speed")).doubleValue()
       );

    }




}
