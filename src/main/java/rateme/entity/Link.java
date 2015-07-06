package rateme.entity;

import javax.persistence.*;

/**
 * Created by Salaufein on 29.06.2015.
 */

@Entity
@Table(name="verlinkung")
public class Link {
    @Id
    @Column(name="id")
    private  int id;

    @Column(name = "link", length = 1000)
    private String url;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "platform_id")
    private int platformId;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "medium_id")
    private int mediumId;

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

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }
}
