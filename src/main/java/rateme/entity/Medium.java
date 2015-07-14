package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mo on 29.06.2015.
 */

@Entity
@Table(name = "medium")
public class Medium {

    public Medium(){}

    public Medium(String name, String description, User user, Category category){
        this.name = name;
        this.description = description;
        this.user = user;
        this.category = category;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "erstellungsdatum", nullable = false)
    private Date timestamp;

    @Column(name = "beschreibung", length = 2000)
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benutzer_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kategorie_id")
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(timestamp);
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(timestamp);
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(timestamp);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return this.user;
    }

    public void setUserId(User u) {
        this.user = u;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category c) {
        this.category = c;
    }

}
