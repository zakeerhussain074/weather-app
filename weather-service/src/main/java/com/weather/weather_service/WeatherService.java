package com.weather.weather_service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private static final String API_KEY = "2aed63febfd76f82e2f1fe6aa4057910";
    private static final String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric";

    @SuppressWarnings("unchecked")
    public List<WeatherResponse> getWeatherForecast(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(WEATHER_URL, city, API_KEY);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) response.get("list");

        List<WeatherResponse> forecasts = new ArrayList<>();
        int day = 0;
        
        for (int i = 0; i < list.size() && i < 24; i += 8) {
            Map<String, Object> dayData = list.get(i);
            Map<String, Object> main = (Map<String, Object>) dayData.get("main");
            Map<String, Object> wind = (Map<String, Object>) dayData.get("wind");
            
            
            double tempMin = ((Number) main.get("temp_min")).doubleValue();
            double tempMax = ((Number) main.get("temp_max")).doubleValue();
            double windSpeed = ((Number) wind.get("speed")).doubleValue();

            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) dayData.get("weather");
            String weather = (String) weatherList.get(0).get("main");

            String advice = "";

            if (weather.toLowerCase().contains("rain")) {
                advice = "Carry umbrella";
            }
            else if (tempMax > 40) {
                advice = "Use sunscreen lotion";
            }
            if (windSpeed > 10) {
                advice = "It's too windy, watch out!";
            }
            if (weather.equalsIgnoreCase("Thunderstorm")) {
                advice = "Don't step out! A Storm is brewing!";
            }
            
            forecasts.add(new WeatherResponse(++day, tempMin, tempMax, windSpeed, weather, advice));
        }
        return forecasts;
    }
}
