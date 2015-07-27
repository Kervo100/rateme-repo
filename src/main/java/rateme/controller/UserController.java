package rateme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rateme.services.UserService;
import rateme.entity.*;

/**
 * Created by kervin on 27/07/15.
 */

@Controller
public class UserController {

    //public static UserController userController = new UserController();
    private UserService userService = null;

    public UserController() {
        this.userService = new UserService();
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public ModelAndView registerUserRequest(
            @RequestParam("username") String username,
            @RequestParam("email") String mail,
            @RequestParam("password") String password) {

        User newUser = new User(username, mail, password);
        if (!checkIfExist(username) ) {
            this.userService.createObject(newUser);
        }
       System.out.println(username + " " + mail + " " + password);

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("page", "mediumList");
        modelAndView.addObject("title", "RateMe");

        return modelAndView;
    }


    public boolean checkIfExist(String username) {

        User checkIfExistUser = this.userService.getUserByName(username);
        if (checkIfExistUser == null) {
            //create new User in Database
            return false;
        }   else {
        System.out.println("ein User mit diesem Eintrag ist bereits vorhanden");
    }
    return true;

    }


    @RequestMapping("/register")
    public ModelAndView showRegister() {
        ModelAndView modelAndView = new ModelAndView("register");

        return modelAndView;
    }

}










/*
    @RequestMapping("/register")
    public String registerUser() {
        return "login";
    }



    public boolean loginUser(String name, String password) {
        User user = this.userService.getUserByName(name);
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
        User user = this.userService.getUserByID(id);

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
        User user = this.userService.getUserByID(id);

        if(user != null) {
            user.setIsBlocked(false);
            return true;
        }
        else {
            System.out.println("unbanUserWithID - User nicht vorhanden");
            return false;
        }
    }
*/



