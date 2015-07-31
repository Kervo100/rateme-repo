package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "bewertung")
public class Rating {

    public Rating() {}

    public Rating(Byte value, User user, Medium medium) {
        this.value = value;
        this.user = user;
        this.medium = medium;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
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
