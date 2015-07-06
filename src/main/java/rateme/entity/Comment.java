package rateme.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Salaufein on 29.06.2015.
 */

@Entity
@Table(name="kommentar")
public class Comment {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="text", nullable = false, length =2000)
    private String text;

    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "benutzer_id")
    private int userId;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn (name= "medium_id")
    private int mediumId;

    public Integer getId() {return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMediaId() {
        return mediumId;
    }

    public void setMediaId(int mediaId) {
        this.mediumId = mediaId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
