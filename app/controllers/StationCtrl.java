package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.data.validation.Error;
import play.mvc.Controller;

import java.util.Date;

import static utils.StationAnalytics.*;

public class StationCtrl extends Controller {

  public static void stationIndex(Long id) {
    Station station = Station.findById(id);
    render("station.html", station);
  }

  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id);
    if (station.getReadings().size() > 0) {
      weatherSummary(station);
    }
    render("station.html", station);
  }

  public static void addReading(Long id, Date date, int code, double temperature, double windSpeed, int windDirection, int pressure) {

    validation.required(code);
    validation.range(code, 100, 800);

    validation.required(temperature);
    validation.range(temperature, -25, 35);

    validation.required(windDirection);
    validation.range(windDirection, 1, 360);

    validation.required(windSpeed);
    validation.range(windSpeed, 1, 117);

    validation.required(pressure);
    validation.range(pressure, 900, 1100);

    if (validation.hasErrors()) {
      for (Error error : validation.errors()) {
        Logger.info("Incorrect values inserted");
        System.out.println(error.message());
        params.flash();
        validation.keep();
        index(id);
      }
    }
    double roundoffTemperature = Math.round(temperature * 100.0) / 100.0;
    double roundoffWindSpeed = Math.round(windSpeed * 100.0) / 100.0;
    Date dates = new Date(System.currentTimeMillis());
    Reading reading = new Reading(dates, code, roundoffTemperature, roundoffWindSpeed, windDirection, pressure);

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

    if (station.getReadings().size() > 0) {
      weatherSummary(station);
    }
    render("station.html", station);
  }
}
