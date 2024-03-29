package pw.stud.envparam.model;

public class SensorConfigResponse {

    String sensorName;
    int measurementFreq;
    Double temperatureMax;
    Double temperatureMin;
    Integer humidityMax;
    Integer humidityMin;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getMeasurementFreq() {
        return measurementFreq;
    }

    public void setMeasurementFreq(int measurementFreq) {
        this.measurementFreq = measurementFreq;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(Integer humidityMax) {
        this.humidityMax = humidityMax;
    }

    public Integer getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(Integer humidityMin) {
        this.humidityMin = humidityMin;
    }
}
