package rateme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kervin on 27/07/15.
 */

@Controller
public class UserController {

    public static UserController userController = new UserController();
    private UserService userService;
    private CommentService commentService;
    private MediumService mediumService;


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












    @RequestMapping("/register")
    public String registerUser() {
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("currentPage") String currentPage,
                              @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                              HttpServletResponse response) {
        Cookie newCookie = null;
        if(loginCookie.equals("false")) {
            System.out.println("login for User: " + email + " requested");
            User remoteUser = userService.getUserByEmail(email);
            if (remoteUser != null) {
                if (remoteUser.getPassword().equals(password)) {
                    System.out.println("User erfolgreich eingeloggt");
                    newCookie = new Cookie("rateMe_LoggedIn", email);
                    newCookie.setMaxAge(86400); //1 day
                    response.addCookie(newCookie);
                } else {
                    System.out.println("falsches Passwort");
                }
            } else {
                System.out.println("dieser User existiert nicht");
            }
        }
        else {
            System.out.println("logout for User: " + loginCookie + " requested");
            newCookie = new Cookie("rateMe_LoggedIn", "false");
            response.addCookie(newCookie);
            System.out.println("User erfolgreich ausgeloggt");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("page", currentPage);
        modelAndView.addObject("loginCookie", newCookie.getValue());

        return modelAndView;
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


}

