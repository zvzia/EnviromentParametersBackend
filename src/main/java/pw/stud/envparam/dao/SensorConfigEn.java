package pw.stud.envparam.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sensor_config")
public class SensorConfigEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "sensor_id")
    private Integer sensorId;
    @Basic
    @Column(name = "measurment_frequency")
    private Integer measurmentFrequency;
    @Basic
    @Column(name = "max_temp")
    private Double maxTemp;
    @Basic
    @Column(name = "min_temp")
    private Double minTemp;
    @Basic
    @Column(name = "max_humidity")
    private Integer maxHumidity;
    @Basic
    @Column(name = "min_humidity")
    private Integer minHumidity;

    public SensorConfigEn() {
    }

    public SensorConfigEn(Integer sensorId, Integer measurmentFrequency, Double maxTemp, Double minTemp, Integer maxHumidity, Integer minHumidity) {
        this.sensorId = sensorId;
        this.measurmentFrequency = measurmentFrequency;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.maxHumidity = maxHumidity;
        this.minHumidity = minHumidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getMeasurmentFrequency() {
        return measurmentFrequency;
    }

    public void setMeasurmentFrequency(Integer measurmentFrequency) {
        this.measurmentFrequency = measurmentFrequency;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Integer getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(Integer maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public Integer getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(Integer minHumidity) {
        this.minHumidity = minHumidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorConfigEn that = (SensorConfigEn) o;
        return id == that.id && Objects.equals(sensorId, that.sensorId) && Objects.equals(measurmentFrequency, that.measurmentFrequency) && Objects.equals(maxTemp, that.maxTemp) && Objects.equals(minTemp, that.minTemp) && Objects.equals(maxHumidity, that.maxHumidity) && Objects.equals(minHumidity, that.minHumidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sensorId, measurmentFrequency, maxTemp, minTemp, maxHumidity, minHumidity);
    }
}
