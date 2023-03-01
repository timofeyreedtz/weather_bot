package com.tg.weather_bot.bot;

import com.tg.weather_bot.models.Properties;
import com.tg.weather_bot.service.WeatherBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
@Component
public class WeatherBot extends TelegramLongPollingBot {
    private  String token;
    private  String username;
    private static long chat_id;
    private final WeatherBotService service;
    private final Properties properties;
    @Autowired
    public WeatherBot(WeatherBotService service,Properties properties) throws TelegramApiException {
        this.service = service;
        this.properties = properties;
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(this);

    }
    @Override
    public String getBotToken() {
        return properties.getToken();
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            chat_id = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/start" -> sendMessage("Привет! Этот бот умеет отправлять прогноз погоды в СПб " + "\n" +
                        "Используй /get, чтобы узнать текущий прогноз.");
                case "/get" -> sendMessage(service.getSpbCurrWeather());
                default -> sendMessage("Неизвестная команда.");
            }
        }
    }
    private void sendMessage(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(message);
        try{
            execute(sendMessage);
        }
        catch (TelegramApiException exception){

        }
    }
}
