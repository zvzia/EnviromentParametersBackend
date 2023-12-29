package pw.stud.envparam.model;

public class AddSensorRequest {
    String name;
    String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AddSensorRequest{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
