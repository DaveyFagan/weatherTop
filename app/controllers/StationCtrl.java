package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.Date;

import static utils.StationAnalytics.*;

public class StationCtrl extends Controller {


  public void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id);
    Reading latestReading = null;
    if (station.getReadings().size() > 0) {
      latestReading = station.getReadings().get(station.getReadings().size() - 1);
      Logger.info("Latest weathercode reading is: " + latestReading.getCode());

      station.setLatestTemperature(latestReading.getTemperature());
      Logger.info("Latest temperature reading is: " + latestReading.getTemperature());

      station.setLatestPressure(latestReading.getPressure());
      Logger.info("Latest pressure reading is: " + latestReading.getPressure());

      station.setLatestWeather(StationAnalytics.convertWeatherCode(latestReading.getCode()));
      Logger.info("Latest weather: " + station.getLatestWeather());

      station.setLatestFahrenheit(StationAnalytics.celciusToFahrenheit(latestReading.getTemperature()));
      Logger.info("The latest fahrenheit : " + station.getLatestFahrenheit());


      station.setLatestWindSpeed(StationAnalytics.kmToBeaufort(latestReading.getWindSpeed()));
      Logger.info("The latest windspeed : " + station.getLatestWindSpeed());

      station.setLatestWindChill(StationAnalytics.windChillCalculator(latestReading.getTemperature(), latestReading.getWindSpeed()));
      Logger.info("The windchill : " + station.getLatestWindChill());

      station.setCompassDirection(StationAnalytics.windDirectionCompass(latestReading.getWindDirection()));
      Logger.info("The compass direction : " + station.getCompassDirection());

      station.setMaxTemperature(getMaxTemperature(station.getReadings()).getTemperature());

      station.setMinTemperature(getMinTemperature(station.getReadings()).getTemperature());

      station.setMaxWindSpeed(getMaxWindSpeed(station.getReadings()).getWindSpeed());

      station.setMinWindSpeed(getMinWindSpeed(station.getReadings()).getWindSpeed());

      station.setMaxPressure(getMaxPressure(station.getReadings()).getPressure());

      station.setMinPressure(getMinPressure(station.getReadings()).getPressure());

      station.setTemperatureTrend(StationAnalytics.getTemperatureTrend(station.getReadings()));
      Logger.info("Temperature trend is: " + station.getTemperatureTrend());

      station.setWindSpeedTrend(StationAnalytics.getWindSpeedTrend(station.getReadings()));
      Logger.info("Windspeed trend is: " + station.getWindSpeedTrend());

      station.setPressureTrend(StationAnalytics.getPressureTrend(station.getReadings()));
      Logger.info("Pressure trend is: " + station.getPressureTrend());

      station.setLatestWeatherIcon(StationAnalytics.generateWeatherIcon(latestReading.getCode()));
      Logger.info("Latest latestWeather code is: " + latestReading.getCode());
      Logger.info("Latest weathericon is: " + station.getLatestWeatherIcon());
    }

    render("station.html", station, latestReading);
  }

  public static void addReading(Long id, Date date, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    Date dates = new Date(System.currentTimeMillis());
    Reading reading = new Reading(dates, code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.getReadings().add(reading);
    station.save();
    Logger.info("Adding new reading");
    redirect("/stations/" + id);
  }

  public static void deletereading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing" + reading);
    station.getReadings().remove(reading);
    station.save();
    reading.delete();

    Reading latestReading = null;
    if (station.getReadings().size() > 0) {
      latestReading = station.getReadings().get(station.getReadings().size() - 1);
      Logger.info("Latest weathercode reading is: " + latestReading.getCode());

      station.setLatestTemperature(latestReading.getTemperature());
      Logger.info("Latest temperature reading is: " + latestReading.getTemperature());

      station.setLatestPressure(latestReading.getPressure());
      Logger.info("Latest pressure reading is: " + latestReading.getPressure());

      station.setLatestWeather(StationAnalytics.convertWeatherCode(latestReading.getCode()));
      Logger.info("Latest weather: " + station.getLatestWeather());

      station.setLatestFahrenheit(StationAnalytics.celciusToFahrenheit(latestReading.getTemperature()));
      Logger.info("The latest fahrenheit : " + station.getLatestFahrenheit());


      station.setLatestWindSpeed(StationAnalytics.kmToBeaufort(latestReading.getWindSpeed()));
      Logger.info("The latest windspeed : " + station.getLatestWindSpeed());

      station.setLatestWindChill(StationAnalytics.windChillCalculator(latestReading.getTemperature(), latestReading.getWindSpeed()));
      Logger.info("The windchill : " + station.getLatestWindChill());

      station.setCompassDirection(StationAnalytics.windDirectionCompass(latestReading.getWindDirection()));
      Logger.info("The compass direction : " + station.getCompassDirection());

      station.setMaxTemperature(getMaxTemperature(station.getReadings()).getTemperature());

      station.setMinTemperature(getMinTemperature(station.getReadings()).getTemperature());

      station.setMaxWindSpeed(getMaxWindSpeed(station.getReadings()).getWindSpeed());

      station.setMinWindSpeed(getMinWindSpeed(station.getReadings()).getWindSpeed());

      station.setMaxPressure(getMaxPressure(station.getReadings()).getPressure());

      station.setMinPressure(getMinPressure(station.getReadings()).getPressure());

      station.setTemperatureTrend(StationAnalytics.getTemperatureTrend(station.getReadings()));
      Logger.info("Temperature trend is: " + station.getTemperatureTrend());

      station.setWindSpeedTrend(StationAnalytics.getWindSpeedTrend(station.getReadings()));
      Logger.info("Windspeed trend is: " + station.getWindSpeedTrend());

      station.setPressureTrend(StationAnalytics.getPressureTrend(station.getReadings()));
      Logger.info("pressure trend is: " + station.getPressureTrend());

      station.setLatestWeatherIcon(StationAnalytics.generateWeatherIcon(latestReading.getCode()));
      Logger.info("Latest latestWeather code is: " + latestReading.getCode());
      Logger.info("Latest weathericon is: " + station.getLatestWeatherIcon());
    }
    render("station.html", station);
  }
}
