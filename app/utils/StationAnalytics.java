package utils;

import models.Reading;
import models.Station;
import play.Logger;

import java.util.*;

public class StationAnalytics {

  public static HashMap<Integer, String> weatherIcons = new HashMap<Integer, String>();

  public StationAnalytics() {
  }

  static {
    weatherIconResponseMap();
  }

  public static Reading getMaxTemperature(List<Reading> readings) {
    Reading maxTemperature = null;
    if (readings.size() > 0) {
      maxTemperature = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getTemperature() > maxTemperature.getTemperature()) {
          maxTemperature = reading;
        }
      }
    }
    return maxTemperature;
  }

  public static String getTemperatureTrend(List<Reading> readings) {
    if (readings.size() > 0) {
      if (readings.size() > 1) {
        if (readings.get(readings.size() - 2).getTemperature() < readings.get(readings.size() - 1).getTemperature()) {
          return "large blue arrow up icon";
        } else if (readings.get(readings.size() - 2).getTemperature() > readings.get(readings.size() - 1).getTemperature()) {
          return "large blue arrow down icon";
        } else {
          return "large blue minus icon";
        }
      }
      if (readings.size() > 2) {
        if (readings.get(readings.size() - 3).getTemperature() < readings.get(readings.size() - 1).getTemperature()) {
          return "large blue arrow up icon";
        } else if (readings.get(readings.size() - 3).getTemperature() > readings.get(readings.size() - 1).getTemperature()) {
          return "large blue arrow down icon";
        } else {
          return "large blue minus icon";
        }
      }
    }
    return "large blue minus icon";
  }

  public static String getWindSpeedTrend(List<Reading> readings) {
    if (readings.size() > 0) {
      if (readings.size() > 1) {
        if (readings.get(readings.size() - 2).getWindSpeed() < readings.get(readings.size() - 1).getWindSpeed()) {
          return "large green arrow up icon";
        } else if (readings.get(readings.size() - 2).getWindSpeed() > readings.get(readings.size() - 1).getWindSpeed()) {
          return "large green arrow down icon";
        } else {
          return "large green minus icon";
        }
      }
      if (readings.size() > 2) {
        if (readings.get(readings.size() - 3).getWindSpeed() < readings.get(readings.size() - 1).getWindSpeed()) {
          return "large green arrow up icon";
        } else if (readings.get(readings.size() - 3).getWindSpeed() > readings.get(readings.size() - 1).getWindSpeed()) {
          return "large green arrow down icon";
        } else {
          return "large green minus icon";
        }
      }
    }
    return "large green minus icon";
  }

  public static String getPressureTrend(List<Reading> readings) {
    if (readings.size() > 0) {
      if (readings.size() > 1) {
        if (readings.get(readings.size() - 2).getPressure() < readings.get(readings.size() - 1).getPressure()) {
          return "large orange arrow up icon";
        } else if (readings.get(readings.size() - 2).getPressure() > readings.get(readings.size() - 1).getPressure()) {
          return "large orange arrow down icon";
        } else {
          return "large orange minus icon";
        }
      }
      if (readings.size() > 2) {
        if (readings.get(readings.size() - 3).getPressure() < readings.get(readings.size() - 1).getPressure()) {
          return "large orange arrow up icon";
        } else if (readings.get(readings.size() - 3).getPressure() > readings.get(readings.size() - 1).getPressure()) {
          return "large orange arrow down icon";
        } else {
          return "large orange minus icon";
        }
      }
    }
    return "large orange minus icon";
  }

  public static Reading getMinTemperature(List<Reading> readings) {
    Reading minTemperature = null;
    if (readings.size() > 0) {
      minTemperature = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getTemperature() < minTemperature.getTemperature()) {
          minTemperature = reading;
        }
      }
    }
    return minTemperature;
  }

  public static Reading getMaxWindSpeed(List<Reading> readings) {
    Reading maxWindSpeed = null;
    if (readings.size() > 0) {
      maxWindSpeed = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getWindSpeed() > maxWindSpeed.getWindSpeed()) {
          maxWindSpeed = reading;
        }
      }
    }
    return maxWindSpeed;
  }

  public static Reading getMinWindSpeed(List<Reading> readings) {
    Reading minWindSpeed = null;
    if (readings.size() > 0) {
      minWindSpeed = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getWindSpeed() < minWindSpeed.getWindSpeed()) {
          minWindSpeed = reading;
        }
      }
    }
    return minWindSpeed;
  }

  public static Reading getMaxPressure(List<Reading> readings) {
    Reading maxPressure = null;
    if (readings.size() > 0) {
      maxPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getPressure() > maxPressure.getPressure()) {
          maxPressure = reading;
        }
      }
    }
    return maxPressure;
  }

  public static Reading getMinPressure(List<Reading> readings) {
    Reading minPressure = null;
    if (readings.size() > 0) {
      minPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.getPressure() < minPressure.getPressure()) {
          minPressure = reading;
        }
      }
    }
    return minPressure;
  }

  public static String convertWeatherCode(int code) {
    switch (code) {
      case 100:
        return "Clear";
      case 200:
        return "Partial Clouds";
      case 300:
        return "Cloudy";
      case 400:
        return "Light Showers";
      case 500:
        return "Heavy Showers";
      case 600:
        return "Rain";
      case 700:
        return "Snow";
      case 800:
        return "Thunder";
      default:
        return "Incorrect code";
    }
  }

  public static double celciusToFahrenheit(double temperature) {
    double fahrenheit;
    fahrenheit = (temperature * 9 / 5) + 32;
    double roundoffFahrenheit = Math.round(fahrenheit * 100.0) / 100.0;
    return roundoffFahrenheit;
  }

  public static double windChillCalculator(double temperature, double windSpeed) {
    double windChill;
    windChill = 13.2 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temperature * Math.pow(windSpeed, 0.16);
    double roundoffWindChill = Math.round(windChill * 100.0) / 100.0;
    return roundoffWindChill;
  }

  public static int kmToBeaufort(double windSpeed) {
    if (windSpeed >= 1 && windSpeed <= 117) {
      if (windSpeed == 1) {
        return 0;
      } else if (windSpeed > 1 && windSpeed <= 5) {
        return 1;
      } else if (windSpeed >= 6 && windSpeed <= 11) {
        return 2;
      } else if (windSpeed >= 12 && windSpeed <= 19) {
        return 3;
      } else if (windSpeed >= 20 && windSpeed <= 28) {
        return 4;
      } else if (windSpeed >= 29 && windSpeed <= 38) {
        return 5;
      } else if (windSpeed >= 39 && windSpeed <= 49) {
        return 6;
      } else if (windSpeed >= 50 && windSpeed <= 61) {
        return 7;
      } else if (windSpeed >= 62 && windSpeed <= 74) {
        return 8;
      } else if (windSpeed >= 75 && windSpeed <= 88) {
        return 9;
      } else if (windSpeed >= 89 && windSpeed <= 102) {
        return 10;
      } else {
        return 11;
      }
    }
    return 0;
  }

  public static String windDirectionCompass(double windDirection) {
    if (windDirection >= 0 && windDirection <= 360) {
      if (windDirection >= 348.75 && windDirection <= 360.00) {
        return "North";
      } else if (windDirection >= 0 && windDirection < 11.25) {
        return "North";
      } else if (windDirection >= 11.25 && windDirection < 33.75) {
        return "North North East";
      } else if (windDirection >= 33.75 && windDirection < 56.25) {
        return "North East";
      } else if (windDirection >= 56.25 && windDirection < 78.75) {
        return "East North East";
      } else if (windDirection >= 78.75 && windDirection < 101.25) {
        return "East";
      } else if (windDirection >= 101.25 && windDirection < 123.75) {
        return "East South East";
      } else if (windDirection >= 123.75 && windDirection < 146.25) {
        return "South East";
      } else if (windDirection >= 146.25 && windDirection < 168.75) {
        return "South South East";
      } else if (windDirection >= 168.75 && windDirection < 191.25) {
        return "South";
      } else if (windDirection >= 191.25 && windDirection < 213.75) {
        return "South South West";
      } else if (windDirection >= 213.75 && windDirection < 236.25) {
        return "South West";
      } else if (windDirection >= 236.25 && windDirection < 258.75) {
        return "West South West";
      } else if (windDirection >= 258.75 && windDirection < 281.25) {
        return "West";
      } else if (windDirection >= 281.25 && windDirection < 303.75) {
        return "West North West";
      } else if (windDirection >= 303.75 && windDirection < 326.25) {
        return "North West";
      } else {
        return "North North West";
      }
    }
    return "incorrect input";
  }

  public static String generateWeatherIcon(int code) {
    String response = weatherIcons.get(code);
    return response;
  }

  public static void weatherIconResponseMap() {
    weatherIcons.put(100, "large red sun icon");
    weatherIcons.put(200, "large red cloud sun icon");
    weatherIcons.put(300, "large red cloud icon");
    weatherIcons.put(400, "large red cloud rain icon");
    weatherIcons.put(500, "large red cloud showers heavy icon");
    weatherIcons.put(600, "large red cloud sun rain icon");
    weatherIcons.put(700, "large red snowflake icon");
    weatherIcons.put(800, "large red bolt icon");
  }

  public static String temperatureIcon(double latestTemperature) {
    if (latestTemperature <= 7) {
      return "large blue thermometer empty icon";
    } else if (latestTemperature > 7 && latestTemperature <= 14) {
      return "large blue thermometer quarter icon";
    } else if (latestTemperature > 14 && latestTemperature <= 21) {
      return "large blue thermometer half icon";
    } else if (latestTemperature > 21 && latestTemperature <= 28) {
      return "large blue thermometer three quarters icon";
    } else {
      return "large blue thermometer full icon";
    }
  }

  public static Station weatherSummary(Station station) {
    Reading latestReading = null;
    latestReading = station.getReadings().get(station.getReadings().size() - 1);
    Logger.info("Latest weathercode reading is: " + latestReading.getCode());

    station.setLatestTemperature(latestReading.getTemperature());
    Logger.info("Latest temperature reading is: " + latestReading.getTemperature());

    station.setLatestPressure(latestReading.getPressure());
    Logger.info("Latest pressure reading is: " + latestReading.getPressure());

    station.setLatestWeather(convertWeatherCode(latestReading.getCode()));
    Logger.info("Latest weather: " + station.getLatestWeather());

    station.setLatestFahrenheit(celciusToFahrenheit(latestReading.getTemperature()));
    Logger.info("The latest fahrenheit : " + station.getLatestFahrenheit());

    station.setLatestWindSpeed(kmToBeaufort(latestReading.getWindSpeed()));
    Logger.info("The latest windspeed : " + station.getLatestWindSpeed());

    station.setLatestWindChill(windChillCalculator(latestReading.getTemperature(), latestReading.getWindSpeed()));
    Logger.info("The windchill : " + station.getLatestWindChill());

    station.setCompassDirection(windDirectionCompass(latestReading.getWindDirection()));
    Logger.info("The compass direction : " + station.getCompassDirection());

    station.setMaxTemperature(getMaxTemperature(station.getReadings()).getTemperature());

    station.setMinTemperature(getMinTemperature(station.getReadings()).getTemperature());

    station.setMaxWindSpeed(getMaxWindSpeed(station.getReadings()).getWindSpeed());

    station.setMinWindSpeed(getMinWindSpeed(station.getReadings()).getWindSpeed());

    station.setMaxPressure(getMaxPressure(station.getReadings()).getPressure());

    station.setMinPressure(getMinPressure(station.getReadings()).getPressure());

    station.setTemperatureTrend(getTemperatureTrend(station.getReadings()));
    Logger.info("Temperature trend is: " + station.getTemperatureTrend());

    station.setWindSpeedTrend(getWindSpeedTrend(station.getReadings()));
    Logger.info("Windspeed trend is: " + station.getWindSpeedTrend());

    station.setPressureTrend(getPressureTrend(station.getReadings()));
    Logger.info("pressure trend is: " + station.getPressureTrend());

    station.setLatestWeatherIcon(generateWeatherIcon(latestReading.getCode()));
    Logger.info("Latest latestWeather code is: " + latestReading.getCode());
    Logger.info("Latest weathericon is: " + station.getLatestWeatherIcon());

    station.setThermometerIcon(temperatureIcon(latestReading.getTemperature()));
    Logger.info("Thermometer icon: " + station.getThermometerIcon());

    return station;
  }
}
