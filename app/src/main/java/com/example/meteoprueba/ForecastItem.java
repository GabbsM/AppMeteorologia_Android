package com.example.meteoprueba;

public class ForecastItem {
    private String date;
    private String weather;

    public ForecastItem(String date, String weather) {
        this.date = date;
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }
}
