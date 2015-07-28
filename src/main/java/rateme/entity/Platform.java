package rateme.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Salaufein on 29.06.2015.
 */
@Entity
@Table(name="plattform")
public class Platform {

   public Platform () {}

   public Platform (String url) throws URISyntaxException {
      URI uri = new URI(url);
      String domain = uri.getHost();
      domain = domain.replaceAll(".*\\.(?=.*\\.)", "");
      System.out.println("Domain: " + domain);
      String[] domainParts = domain.split("\\.");
      System.out.println("Part 0: " + domainParts[0] + " ,Part 1: " + domainParts[1]);
      String platform = domainParts[0].substring(0, 1).toUpperCase() + domainParts[0].substring(1);
      System.out.println("Platform: " + platform);
      this.name = platform;
   }

   @Id
   @GenericGenerator(name = "generator", strategy = "increment")
   @GeneratedValue(generator = "generator")
   @Column(name="id")
   private  int id;

   @Column(name ="name", nullable = false, length = 100, unique = true)
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
