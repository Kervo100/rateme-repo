/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

public class Start {
    public static void main (String[] args) {
        System.out.println("rateme start");

        //UserController userController = new UserController();
        //User moritz = new User("Moritz Ellmers", "mers@web.de", "1234", true);
        //userController.createObject(moritz);
        //User mo = userService.getUserByName("Moritz Ellmers");
        //System.out.println(mo.getUsername());

        //MediumService mediumService = new MediumService();
        //System.out.println(medium.getTimestamp());

        HibernateUtil.shutdown();
        System.out.println("rateme close");
    }
}
