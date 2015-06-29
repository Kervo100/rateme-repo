package rateme.entity;

/**
 * Created by Mo on 29.06.2015.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="benutzer")
public class user {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable=false, length = 50)
    private String username;

    // Unique
    @Column(name="email", nullable=false, unique = true)
    private String email;

    @Column(name = "passwort", nullable = false, length = 50)
    private String password;

    @Column(name = "istAdmin", nullable = false)
    private boolean isAdmin = false;

    @Column(name = "istGesperrt", nullable = false)
    private boolean isBlocked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}
