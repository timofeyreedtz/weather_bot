package com.tg.weather_bot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Condition {
    private String text;
    private String icon;
    private int code;

    public Condition(String text, String icon, int code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }
}
