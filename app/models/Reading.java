package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;

@Entity

public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;
  public Date date;

  public Reading(Date date, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    this.date = date;
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;

  }
}
