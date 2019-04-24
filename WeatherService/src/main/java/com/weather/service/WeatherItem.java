package com.weather.service;

import lombok.Data;

@Data
public class WeatherItem {

    private String location;
    private Double currentTemperature;
    private Double tomorrowTemperature;

    WeatherItem(String location, Double currentTemperature, Double tomorrowTemperature) {
        this.location = location;
        this.currentTemperature = currentTemperature;
        this.tomorrowTemperature = tomorrowTemperature;
    }

    public WeatherItem() {}
}
