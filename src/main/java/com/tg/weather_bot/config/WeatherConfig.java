package com.tg.weather_bot.config;

import com.tg.weather_bot.models.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:/bot.properties")
public class WeatherConfig {
    @Autowired
    private Environment env;
    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    };
    @Bean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder = restTemplateBuilder();
        return restTemplateBuilder.build();
    }
    @Bean
    public Properties getProperties(){
        return new Properties(env.getProperty("token"), env.getProperty("username"),env.getProperty("url") );
    }
}
