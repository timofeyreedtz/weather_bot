package com.tg.weather_bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tg.weather_bot.models.CurrentWeather;
import com.tg.weather_bot.models.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class WeatherBotService {
    private final String url;
    private final static ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate;
    private final Properties properties;
    @Autowired
    public WeatherBotService(RestTemplate restTemplate,Properties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        url = properties.getUrl();
    }

    public String getSpbCurrWeather() {
        String string  = restTemplate.getForObject(url, String.class);
        CurrentWeather currentWeather = new CurrentWeather();
        try {
            currentWeather = mapper.readValue(string,CurrentWeather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return   currentWeather.getLocation().getLocaltime() + "\n" +  "\n" + "Текущая погода в Санкт-Петербурге: " + currentWeather.getCurrent().getTemp_c() + "\n"+
                "Последнее обновление данных: " + currentWeather.getCurrent().getLast_updated() + "\n" +
                "Ощущается как: " + currentWeather.getCurrent().getFeelslike_c() + "\n" +
                "На улице: " + currentWeather.getCurrent().getCondition().getText() + " " +  "\n" +
                "Скорость ветра км/ч: " + currentWeather.getCurrent().getWind_kph() + "\n\n" +
                currentWeather.getCurrent().getCondition().getIcon();
    }
}
