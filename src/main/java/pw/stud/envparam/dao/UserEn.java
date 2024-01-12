package pw.stud.envparam.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`user`")
public class UserEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "msg_token")
    private String msgToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsgToken() {
        return msgToken;
    }

    public void setMsgToken(String msgToken) {
        this.msgToken = msgToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEn userEn = (UserEn) o;
        return id == userEn.id && Objects.equals(username, userEn.username) && Objects.equals(email, userEn.email) && Objects.equals(password, userEn.password) && Objects.equals(msgToken, userEn.msgToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, msgToken);
    }
}
