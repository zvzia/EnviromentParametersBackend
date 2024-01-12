package pw.stud.envparam.model;

import java.util.Objects;

public class DataFromSensorRequest {
    float temperature;
    int humidity;
    int batteryLevel;
    String token;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataFromSensorRequest that = (DataFromSensorRequest) o;
        return Float.compare(that.temperature, temperature) == 0 && humidity == that.humidity && batteryLevel == that.batteryLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash( temperature, humidity, batteryLevel);
    }

    @Override
    public String toString() {
        return "DataFromSensorRequest{" +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", batteryLevel=" + batteryLevel +
                '}';
    }
}
