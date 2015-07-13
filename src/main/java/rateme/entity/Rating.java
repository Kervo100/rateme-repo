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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benutzer_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medium_id")
    private Medium medium;

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
