package rateme.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mo on 29.06.2015.
 */

@Entity
@Table(name = "medium")
public class Medium {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "erstellungsdatum", nullable = false)
    private String timestamp;

    @Column(name = "beschreibung", length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benutzer_id")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kategorie_id")
    private int kategorieId;

    public int getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(int kategorieId) {
        this.kategorieId = kategorieId;
    }

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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
