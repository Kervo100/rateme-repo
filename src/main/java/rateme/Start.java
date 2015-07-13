/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

import rateme.entity.Medium;
import rateme.entity.User;
import rateme.controller.*;

public class Start {
    public static void main (String[] args) {
        System.out.println("rateme start");

        UserController userController = new UserController();
        User moritz = new User("Moritz Ellmers", "moritz.ellmers@web.de", "1234", true);
        userController.createObject(moritz);
        User mo = userController.getUserByName("Moritz Ellmers");
        System.out.println(mo.getUsername());

        //MediumController mediumController = new MediumController();
        //Medium medium = mediumController.getMediumByID(1);
        //System.out.println(medium.getTimestamp());

        HibernateUtil.shutdown();
        System.out.println("rateme close");
    }
}
