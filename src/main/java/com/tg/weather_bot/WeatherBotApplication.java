package com.tg.weather_bot;


import com.tg.weather_bot.bot.WeatherBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication

public class WeatherBotApplication {
    public static void main(String[] args)  {
        SpringApplication.run(WeatherBotApplication.class, args);
    }
}
