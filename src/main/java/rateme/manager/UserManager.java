package rateme.manager;

import rateme.controller.CommentController;
import rateme.controller.MediumController;
import rateme.controller.UserController;
import rateme.entity.*;

/**
 * Created by thorben on 13/07/15.
 */
public class UserManager {
    public static UserManager userManager = new UserManager();
    private UserController userController;
    private CommentController commentController;
    private MediumController mediumController;

    public UserManager() {
        userController = new UserController();
        commentController = new CommentController();
        mediumController = new MediumController();
    }

    public boolean registerUser(String name, String password, String mail, boolean isAdmin) {
        User newUser = new User(name, mail, password, isAdmin);
        User checkIfExist = userController.getUserByName(name);
        if(checkIfExist == null) {
            userController.createObject(newUser);
            return true;
        }
        else {
            System.out.println("registerUser - ein User mit diesem Namen bereits vorhanden");
        }
        return false;
    }

    public boolean loginUser(String name, String password) {
        User user = userController.getUserByName(name);
        if(user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            return true;
        }
        else {
            System.out.println("loginUser - wrong password");
        }

        return false;
    }

    public boolean banUserWithID(int id) {
        User user = userController.getUserByID(id);

        if(user != null) {
            user.setIsBlocked(true);
            return true;
        }
        else {
            System.out.println("banUserWithID - User nicht vorhanden");
            return false;
        }
    }

    public boolean unbanUserWithID(int id) {
        User user = userController.getUserByID(id);

        if(user != null) {
            user.setIsBlocked(false);
            return true;
        }
        else {
            System.out.println("unbanUserWithID - User nicht vorhanden");
            return false;
        }
    }
}
