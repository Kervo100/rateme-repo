package rateme.entity;

import javax.persistence.*;

/**
 * Created by Mo on 29.06.2015.
 */

@Entity
@Table(name = "bewertung")
public class Rating {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "wert", nullable = false)
    private byte value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benutzer_id")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medium_id")
    private int mediumId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }
}
