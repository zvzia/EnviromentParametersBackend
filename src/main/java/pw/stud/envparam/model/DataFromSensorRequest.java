package pw.stud.envparam.model;

import java.util.Objects;

public class DataFromSensorRequest {
    SurroundingConditions surroundingConditions;
    int batteryLevel;

    public SurroundingConditions getSurroundingConditions() {
        return surroundingConditions;
    }

    public void setSurroundingConditions(SurroundingConditions surroundingConditions) {
        this.surroundingConditions = surroundingConditions;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataFromSensorRequest that = (DataFromSensorRequest) o;
        return batteryLevel == that.batteryLevel && Objects.equals(surroundingConditions, that.surroundingConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surroundingConditions, batteryLevel);
    }
}
