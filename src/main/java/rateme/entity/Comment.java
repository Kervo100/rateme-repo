package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Salaufein on 29.06.2015.
 */

@Entity
@Table(name="kommentar")
public class Comment {

    public Comment() {}

    public Comment(String text, User user, Medium medium) {
        this.text = text;
        this.user = user;
        this.medium = medium;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name="id")
    private Integer id;

    @Column(name="text", nullable = false, length =2000)
    private String text;

    @Column(name="erstellungsdatum", nullable = false)
    private Date timestamp;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "benutzer_id")
    private User user;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "medium_id")
    private Medium medium;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(this.timestamp);
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(this.timestamp);
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(this.timestamp);
    }

    public void setDate(Date date) {
        this.timestamp = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
}
