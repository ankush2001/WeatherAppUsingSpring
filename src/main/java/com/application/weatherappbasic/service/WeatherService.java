package com.application.weatherappbasic.service;

import com.application.weatherappbasic.dto.WeatherSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {

    @Value("${open_weather.api.key}")
    private String apiKey ;

    private final RestTemplate restTemplate = new RestTemplate() ;
    Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherSummary  getWeatherByCity(String city)  {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&units=metric";

        Map<String, Object> response ;

         try {
              response = restTemplate.getForObject(url, Map.class);
            if (response == null || response.isEmpty()) {
                throw new IllegalArgumentException("No data found for city: " + city);
            }
         } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong while fetching weather data for city: " + city, e);
         }
        logger.info("Weather data for city: {} - {}", city, response);

       Map<String, Object> main = (Map<String, Object>) response.get("main");
       Map<String, Object> wind = (Map<String, Object>) response.get("wind");
       Map<String, Object> sys = (Map<String, Object>) response.get("sys");
       List<Map<String, Object>> weatherList = safeGetList(response);
        Map<String, Object> weather = weatherList.isEmpty() ? Collections.emptyMap() : weatherList.get(0);

        return new WeatherSummary(
               (String) response.get("name"),
               (String) sys.get("country"),
               (String) weather.get("description"),
               ((Number) main.get("temp")).doubleValue(),
               ((Number) main.get("humidity")).intValue(),
               ((Number) wind.get("speed")).doubleValue()
       );

    }

    private List<Map<String, Object>> safeGetList(Map<String, Object> response) {
        Object value = response.get("weather");
        if (value instanceof List) {
            return (List<Map<String, Object>>) value;
        } else if (value instanceof Map) {
            return Collections.singletonList((Map<String, Object>) value);
        } else {
            return Collections.emptyList();
        }
    }


}
