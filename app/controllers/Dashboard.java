package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.data.validation.Error;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.Comparator;
import java.util.List;

public class Dashboard extends Controller {

  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();

    member.stations.sort(Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));

    List<Station> stations = member.stations;

    for (Station station : stations) {
      if (station.getReadings().size() > 0) {
        StationAnalytics.weatherSummary(station);
      }
    }
    render("dashboard.html", stations);
  }

  public static void addStation(String name, double lat, double lng) {
    validation.required(name);
    validation.required(lat);
    validation.range(lat, -180, 180);
    validation.required(lng);
    validation.range(lng, -180, 180);
    if (validation.hasErrors()) {
      for (Error error : validation.errors()) {
        System.out.println(error.message());
        Logger.info("Incorrect values inserted");
        params.flash();
        validation.keep();
        index();
      }
    }
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
