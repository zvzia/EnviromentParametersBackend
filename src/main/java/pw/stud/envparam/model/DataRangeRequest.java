package pw.stud.envparam.model;

import java.util.Date;
import java.util.Objects;

public class DataRangeRequest {
    int sensorId;
    Date start;
    Date end;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRangeRequest that = (DataRangeRequest) o;
        return sensorId == that.sensorId && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, start, end);
    }
}
