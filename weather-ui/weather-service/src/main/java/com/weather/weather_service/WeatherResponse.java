package com.weather.weather_service;

public class WeatherResponse {
	private int day;
    private double minTemp;
    private double maxTemp;
    private double windSpeed;
    private String weather;
    private String advice;

    public WeatherResponse(int day, double minTemp, double maxTemp,double windSpeed, String weather, String advice) {
        this.day = day;
    	this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.windSpeed = windSpeed;
        this.weather = weather;
        this.advice = advice;
    }
    
    public int getDay() {return day;}
    public double getMinTemp() { return minTemp; }
    public double getMaxTemp() { return maxTemp; }
    public double getWindSpeed() { return windSpeed; }
    public String getWeather() { return weather; }
    public String getAdvice() { return advice; }
}
