/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

import rateme.entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main (String[] args) {
        System.out.println("rateme start");

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("PersistenceUnit");

        EntityManager em = emf.createEntityManager();

        User member = em.find(User.class, 1);

        System.out.println("user: " + member.getUsername());

        System.out.println("rateme close");
    }


}
