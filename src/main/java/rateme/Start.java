/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

import rateme.entity.User;
import rateme.manager.*;

public class Start {
    public static void main (String[] args) {
        System.out.println("rateme start");

        UserManager userManager = new UserManager();
        User user = userManager.getUserByName("John Doe");
        System.out.println(user.getEmail());

        HibernateUtil.shutdown();
        System.out.println("rateme close");
    }
}
