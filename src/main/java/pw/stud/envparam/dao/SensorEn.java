package pw.stud.envparam.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sensor")
public class SensorEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorEn sensorEn = (SensorEn) o;
        return id == sensorEn.id && Objects.equals(name, sensorEn.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
