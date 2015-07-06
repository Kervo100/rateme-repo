package rateme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Salaufein on 29.06.2015.
 */
@Entity
@Table(name="plattform")
public class Platform {

   @Id
   @Column(name="id")
   private  int id;

   @Column(name ="name", nullable = false, length = 100)
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }


}
