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
 * Created by thorben on 13/07/15.
 */

@Controller
public class UserController {
    public static UserController userController = new UserController();
    private UserService userService;
    private CommentService commentService;
    private MediumService mediumService;

    public UserController() {
        this.userService = new UserService();
        this.commentService = new CommentService();
        this.mediumService = new MediumService();
    }

    @RequestMapping("/register/addUser")
    public boolean registerUser(String name, String password, String mail, boolean isAdmin) {
        User newUser = new User(name, mail, password, isAdmin);
        User checkIfExist = this.userService.getUserByName(name);
        if(checkIfExist == null) {
            this.userService.createObject(newUser);
            return true;
        }
        else {
            System.out.println("registerUser - ein User mit diesem Namen bereits vorhanden");
        }
        return false;
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
                    newCookie = new Cookie("rateMe_LoggedIn", remoteUser.getId().toString());
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
