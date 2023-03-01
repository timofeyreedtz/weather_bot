package com.tg.weather_bot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Properties {
    private String token,username,url;

    public Properties(String token, String username, String url) {
        this.token = token;
        this.username = username;
        this.url = url;
    }
}
