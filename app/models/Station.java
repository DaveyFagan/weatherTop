package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {

  private String name;
  private double lat;
  private double lng;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Reading> readings = new ArrayList<Reading>();
  private double latestTemperature;
  private int latestPressure;
  private String latestWeather;
  private double latestFahrenheit;
  private int latestWindSpeed;
  private double latestWindChill;
  private String compassDirection;
  private double maxTemperature;
  private double minTemperature;
  private double maxWindSpeed;
  private double minWindSpeed;
  private int maxPressure;
  private int minPressure;
  private String latestWeatherIcon;
  private String temperatureTrend;
  private String windSpeedTrend;
  private String pressureTrend;

  public Station(String name, double lat, double lng) {
    this.setName(name);
    this.setLat(lat);
    this.setLng(lng);
  }

  public String getPressureTrend() {
    return pressureTrend;
  }

  public void setPressureTrend(String pressureTrend) {
    this.pressureTrend = pressureTrend;
  }

  public String getWindSpeedTrend() {
    return windSpeedTrend;
  }

  public void setWindSpeedTrend(String windSpeedTrend) {
    this.windSpeedTrend = windSpeedTrend;
  }

  public String getTemperatureTrend() {
    return temperatureTrend;
  }

  public void setTemperatureTrend(String temperatureTrend) {
    this.temperatureTrend = temperatureTrend;
  }

  public String getLatestWeatherIcon() {
    return latestWeatherIcon;
  }

  public void setLatestWeatherIcon(String latestWeatherIcon) {
    this.latestWeatherIcon = latestWeatherIcon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public List<Reading> getReadings() {
    return readings;
  }

  public void setReadings(List<Reading> readings) {
    this.readings = readings;
  }

  public double getLatestTemperature() {
    return latestTemperature;
  }

  public void setLatestTemperature(double latestTemperature) {
    this.latestTemperature = latestTemperature;
  }

  public int getLatestPressure() {
    return latestPressure;
  }

  public void setLatestPressure(int latestPressure) {
    this.latestPressure = latestPressure;
  }

  public String getLatestWeather() {
    return latestWeather;
  }

  public void setLatestWeather(String latestWeather) {
    this.latestWeather = latestWeather;
  }

  public double getLatestFahrenheit() {
    return latestFahrenheit;
  }

  public void setLatestFahrenheit(double latestFahrenheit) {
    this.latestFahrenheit = latestFahrenheit;
  }

  public int getLatestWindSpeed() {
    return latestWindSpeed;
  }

  public void setLatestWindSpeed(int latestWindSpeed) {
    this.latestWindSpeed = latestWindSpeed;
  }

  public double getLatestWindChill() {
    return latestWindChill;
  }

  public void setLatestWindChill(double latestWindChill) {
    this.latestWindChill = latestWindChill;
  }

  public String getCompassDirection() {
    return compassDirection;
  }

  public void setCompassDirection(String compassDirection) {
    this.compassDirection = compassDirection;
  }

  public double getMaxTemperature() {
    return maxTemperature;
  }

  public void setMaxTemperature(double maxTemperature) {
    this.maxTemperature = maxTemperature;
  }

  public double getMinTemperature() {
    return minTemperature;
  }

  public void setMinTemperature(double minTemperature) {
    this.minTemperature = minTemperature;
  }

  public double getMaxWindSpeed() {
    return maxWindSpeed;
  }

  public void setMaxWindSpeed(double maxWindSpeed) {
    this.maxWindSpeed = maxWindSpeed;
  }

  public double getMinWindSpeed() {
    return minWindSpeed;
  }

  public void setMinWindSpeed(double minWindSpeed) {
    this.minWindSpeed = minWindSpeed;
  }

  public int getMaxPressure() {
    return maxPressure;
  }

  public void setMaxPressure(int maxPressure) {
    this.maxPressure = maxPressure;
  }

  public int getMinPressure() {
    return minPressure;
  }

  public void setMinPressure(int minPressure) {
    this.minPressure = minPressure;
  }
}