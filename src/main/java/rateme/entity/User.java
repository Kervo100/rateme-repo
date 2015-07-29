package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Created by Mo on 29.06.2015.
 */

@Entity
@Table(name = "benutzer")
public class User {

    public User() {}
    public User(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }
    public User(String name, String email, String password, boolean admin) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.isAdmin = admin;
    }


    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50)
    private String username;

    // Unique
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "passwort", nullable = false, length = 50)
    private String password;

    @Column(name = "istAdmin", nullable = false)
    private boolean isAdmin = false;

    @Column(name = "istGesperrt", nullable = false)
    private boolean isBlocked = false;

    // Auto-generate Getter and Setter with ALT + EINFG
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
