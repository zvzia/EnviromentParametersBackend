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
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "token")
    private String token;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        SensorEn sensorEn = (SensorEn) o;
        return id == sensorEn.id && userId == sensorEn.userId && Objects.equals(name, sensorEn.name) && Objects.equals(token, sensorEn.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, token);
    }
}
