package utils;

import models.Reading;


import java.util.*;

public class StationAnalytics {


  public static HashMap<Integer, String> responseMap = new HashMap<Integer, String>();

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
    if (windSpeed == 1) {
      return 0;
    }
    if (windSpeed > 1 && windSpeed <= 5) {
      return 1;
    }
    if (windSpeed >= 6 && windSpeed <= 11) {
      return 2;
    }
    if (windSpeed >= 12 && windSpeed <= 19) {
      return 3;
    }
    if (windSpeed >= 20 && windSpeed <= 28) {
      return 4;
    }
    if (windSpeed >= 29 && windSpeed <= 38) {
      return 5;
    }
    if (windSpeed >= 39 && windSpeed <= 49) {
      return 6;
    }
    if (windSpeed >= 50 && windSpeed <= 61) {
      return 7;
    }
    if (windSpeed >= 62 && windSpeed <= 74) {
      return 8;
    }
    if (windSpeed >= 75 && windSpeed <= 88) {
      return 9;
    }
    if (windSpeed >= 89 && windSpeed <= 102) {
      return 10;
    }
    if (windSpeed >= 103 && windSpeed <= 117) {
      return 11;
    } else {
      return 12;
    }
  }

  public static String windDirectionCompass(double windDirection) {
    if (windDirection >= 0 && windDirection <= 360) {
      if (windDirection >= 348.75 && windDirection <= 360.00) {
        return "North";
      }
      if (windDirection >= 0 && windDirection < 11.25) {
        return "North";
      }
      if (windDirection >= 11.25 && windDirection < 33.75) {
        return "North North East";
      }
      if (windDirection >= 33.75 && windDirection < 56.25) {
        return "North East";
      }
      if (windDirection >= 56.25 && windDirection < 78.75) {
        return "East North East";
      }
      if (windDirection >= 78.75 && windDirection < 101.25) {
        return "East";
      }
      if (windDirection >= 101.25 && windDirection < 123.75) {
        return "East South East";
      }
      if (windDirection >= 123.75 && windDirection < 146.25) {
        return "South East";
      }
      if (windDirection >= 146.25 && windDirection < 168.75) {
        return "South South East";
      }
      if (windDirection >= 168.75 && windDirection < 191.25) {
        return "South";
      }
      if (windDirection >= 191.25 && windDirection < 213.75) {
        return "South South West";
      }
      if (windDirection >= 213.75 && windDirection < 236.25) {
        return "South West";
      }
      if (windDirection >= 236.25 && windDirection < 258.75) {
        return "West South West";
      }
      if (windDirection >= 258.75 && windDirection < 281.25) {
        return "West";
      }
      if (windDirection >= 281.25 && windDirection < 303.75) {
        return "West North West";
      }
      if (windDirection >= 303.75 && windDirection < 326.25) {
        return "North West";
      }
      if (windDirection >= 326.25 && windDirection <= 348.75) {
        return "North North West";
      }
    }
  return "incorrect input";
}

  public static String generateWeatherIcon(int input) {
    String response = responseMap.get(input);
    return response;
  }

  public static void weatherIconResponseMap() {
    responseMap.put(100, "large red sun icon");

    responseMap.put(200, "large red cloud sun icon");

    responseMap.put(300, "large red cloud icon");

    responseMap.put(400, "large red cloud rain icon");

    responseMap.put(500, "cloud showers heavy icon");

    responseMap.put(600, "large red cloud sun rain icon");

    responseMap.put(700, "large red snowflake icon");

    responseMap.put(800, "large red bolt icon");

  }
}
