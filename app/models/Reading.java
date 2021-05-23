/**
 * Reading class represents all the weather readings
 * in the weatherTop apolication.
 *
 * @Author David Fagan
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;

@Entity

public class Reading extends Model {

  private int code;
  private double temperature;
  private double windSpeed;
  private int windDirection;
  private int pressure;
  private Date date;

  public Reading(Date date, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    this.date = date;
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindDirection(int windDirection) {
    this.windDirection = windDirection;
  }

  public int getWindDirection() {
    return windDirection;
  }

  public int getPressure() {
    return pressure;
  }

  public void setPressure(int pressure) {
    this.pressure = pressure;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return date;
  }
}
