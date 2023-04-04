package pw.stud.envparam.model;

import java.util.Objects;

public class SensorIdRequest {
    int sensorId;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorIdRequest that = (SensorIdRequest) o;
        return sensorId == that.sensorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId);
    }
}
