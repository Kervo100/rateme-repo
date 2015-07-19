package rateme.controller;

import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.*;

/**
 * Created by thorben on 13/07/15.
 */
public class UserController {
    public static UserController userController = new UserController();
    private UserService userService;
    private CommentService commentService;
    private MediumService mediumService;

    public UserController() {
        userService = new UserService();
        commentService = new CommentService();
        mediumService = new MediumService();
    }

    public boolean registerUser(String name, String password, String mail, boolean isAdmin) {
        User newUser = new User(name, mail, password, isAdmin);
        User checkIfExist = userService.getUserByName(name);
        if(checkIfExist == null) {
            userService.createObject(newUser);
            return true;
        }
        else {
            System.out.println("registerUser - ein User mit diesem Namen bereits vorhanden");
        }
        return false;
    }

    public boolean loginUser(String name, String password) {
        User user = userService.getUserByName(name);
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
        User user = userService.getUserByID(id);

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
        User user = userService.getUserByID(id);

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
