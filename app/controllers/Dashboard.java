package controllers;

import models.Member;
import models.Reading;
import models.Station;

import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;


import java.util.Comparator;
import java.util.List;

import static utils.StationAnalytics.*;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();

    member.stations.sort(Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));

    List<Station> stations = member.stations;

    for (Station station : stations) {
      Reading latestReading = null;
      if (station.getReadings().size() > 0) {
        latestReading = station.getReadings().get(station.getReadings().size() - 1);
        Logger.info("Latest weathercode reading is: " + latestReading.code);

        station.setLatestTemperature(latestReading.temperature);
        Logger.info("Latest temperature reading is: " + latestReading.temperature);

        station.setLatestPressure(latestReading.pressure);
        Logger.info("Latest pressure reading is: " + latestReading.pressure);

        station.setLatestWeather(StationAnalytics.convertWeatherCode(latestReading.code));
        Logger.info("Latest weather: " + station.getLatestWeather());

        station.setLatestFahrenheit(StationAnalytics.celciusToFahrenheit(latestReading.temperature));
        Logger.info("The latest fahrenheit : " + station.getLatestFahrenheit());

        station.setLatestWindSpeed(StationAnalytics.kmToBeaufort(latestReading.windSpeed));
        Logger.info("The latest windspeed : " + station.getLatestWindSpeed());

        station.setLatestWindChill(StationAnalytics.windChillCalculator(latestReading.temperature, latestReading.windSpeed));
        Logger.info("The windchill : " + station.getLatestWindChill());

        station.setCompassDirection(StationAnalytics.windDirectionCompass(latestReading.windDirection));
        Logger.info("The compass direction : " + station.getCompassDirection());

        station.setMaxTemperature(getMaxTemperature(station.getReadings()).temperature);

        station.setMinTemperature(getMinTemperature(station.getReadings()).temperature);

        station.setMaxWindSpeed(getMaxWindSpeed(station.getReadings()).windSpeed);

        station.setMinWindSpeed(getMinWindSpeed(station.getReadings()).windSpeed);

        station.setMaxPressure(getMaxPressure(station.getReadings()).pressure);

        station.setMinPressure(getMinPressure(station.getReadings()).pressure);

        station.setTemperatureTrend(StationAnalytics.getTemperatureTrend(station.getReadings()));
        Logger.info("Temperature trend is: " + station.getTemperatureTrend());

        station.setWindSpeedTrend(StationAnalytics.getWindSpeedTrend(station.getReadings()));
        Logger.info("Windspeed trend is: " + station.getWindSpeedTrend());

        station.setPressureTrend(StationAnalytics.getPressureTrend(station.getReadings()));
        Logger.info("Pressure trend is: " + station.getPressureTrend());

        station.setLatestWeatherIcon(StationAnalytics.generateWeatherIcon(latestReading.code));
        Logger.info("Latest latestWeather code is: " + latestReading.code);
        Logger.info("Latest weathericon is: " + station.getLatestWeatherIcon());
      }
    }
    render("dashboard.html", stations);
  }

  public static void addStation(String name, double lat, double lng) {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, lat, lng);
    member.stations.add(station);
    member.save();
    Logger.info("Adding a new station called " + name + " Latitude: " + lat + " Longitude: " + lng);
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    Logger.info("Removing" + station.getName());
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

}
