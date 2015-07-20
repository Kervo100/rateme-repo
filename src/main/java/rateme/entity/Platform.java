package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Salaufein on 29.06.2015.
 */
@Entity
@Table(name="plattform")
public class Platform {

   @Id
   @GenericGenerator(name = "generator", strategy = "increment")
   @GeneratedValue(generator = "generator")
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
