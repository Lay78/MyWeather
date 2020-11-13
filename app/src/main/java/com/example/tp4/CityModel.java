package com.example.tp4;

public class CityModel {
    private String city_name;
    private String type_weather;
    private int temperature;
    private int temp_feels_like;
    private int temp_min;
    private int temp_max;

    public CityModel(String city_name, String type_weather, int temperature, int temp_feels_like, int temp_min, int temp_max) {
        this.city_name = city_name;
        this.type_weather = type_weather;
        this.temperature = temperature;
        this.temp_feels_like = temp_feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getType_weather() {
        return type_weather;
    }

    public void setType_weather(String type_weather) {
        this.type_weather = type_weather;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemp_feels_like() {
        return temp_feels_like;
    }

    public void setTemp_feels_like(int temp_feels_like) {
        this.temp_feels_like = temp_feels_like;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(int temp_min) {
        this.temp_min = temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(int temp_max) {
        this.temp_max = temp_max;
    }
}
