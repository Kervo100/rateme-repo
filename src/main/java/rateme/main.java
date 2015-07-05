/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

import rateme.entity.*;
import rateme.manager.*;

public class main {
    public static void main (String[] args) {
        System.out.println("rateme start");

        UserManager userManager = new UserManager();
        User u = userManager.getUserByName("John Doe");

        HibernateUtil.shutdown();
        System.out.println("rateme close");
    }
}
