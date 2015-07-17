package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Salaufein on 29.06.2015.
 */

@Entity
@Table(name="verlinkung")
public class Link {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name="id")
    private  int id;

    @Column(name = "link", length = 1000)
    private String url;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "platform_id")
    private Platform platform;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "medium_id")
    private Medium medium;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
}
