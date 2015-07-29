package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rateme.ViewLib;
import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public ModelAndView registerUserRequest(
            @RequestParam("username") String username,
            @RequestParam("email") String mail,
            @RequestParam("password") String password,
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @RequestParam ("passwordConfirm") String passwordConfirm) {

        ModelAndView modelAndView = null;

        User newUser = new User(username, mail, password);
        if (!checkIfExist(username, mail)) {
            if(doubleCheckPassword(password,passwordConfirm)){
                this.userService.createObject(newUser);
                System.out.println(username + " " + mail + " " + password + " has been created successfully");
            } else {

                modelAndView = ViewLib.activeViewLib().getView(loginCookie, "confirmPassword");
                return  modelAndView;
            }
        } else {
            modelAndView = ViewLib.activeViewLib().getView(loginCookie, "userExist");
            return modelAndView;
        }
        modelAndView = ViewLib.activeViewLib().getView(loginCookie, "welcome");
        return modelAndView;
    }

    public boolean checkIfExist(String username, String mail) {

        User checkIfExistUser = this.userService.getUserByName(username);
        User checkIfExistMail = this.userService.getUserByEmail(mail);
        if (checkIfExistUser == null || checkIfExistMail == null) {
            return false;
        }
        else {
            System.out.println("ein User mit diesem Eintrag ist bereits vorhanden");
        }

        return true;
    }

    public boolean doubleCheckPassword(String password, String passwordConfirm){
        if ((password).equals(passwordConfirm)) {
            return true;
        } else {
            System.out.println("Passwort wurde falsch eingegeben");
        }
        return false;
    }

    @RequestMapping("/register")
    public ModelAndView showRegister() {
        ModelAndView modelAndView = new ModelAndView("register");

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("currentPage") String currentPage,
                              @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                              HttpServletResponse response) {
        Cookie newCookie = null;
        if (loginCookie.equals("false")) {
            User remoteUser = userService.getUserByEmail(email);
            if (remoteUser != null) {
                if (remoteUser.getPassword().equals(password)) {
                    System.out.println(email + " erfolgreich eingeloggt");
                    newCookie = new Cookie("rateMe_LoggedIn", remoteUser.getId().toString());
                    newCookie.setMaxAge(86400); //1 day
                    response.addCookie(newCookie);
                } else {
                    System.out.println("falsches Passwort");
                }
            } else {
                System.out.println("dieser User existiert nicht");
            }
        } else {
            newCookie = new Cookie("rateMe_LoggedIn", "false");
            response.addCookie(newCookie);
            System.out.println(email + " erfolgreich ausgeloggt");
        }

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(newCookie.getValue(), currentPage);
        return modelAndView;
    }

    public boolean banUserWithID(int id) {
        User user = this.userService.getUserByID(id);

        if (user != null) {
            user.setIsBlocked(true);
            return true;
        } else {
            System.out.println("banUserWithID - User nicht vorhanden");
            return false;
        }
    }

    public boolean unbanUserWithID(int id) {
        User user = this.userService.getUserByID(id);

        if (user != null) {
            user.setIsBlocked(false);
            return true;
        } else {
            System.out.println("unbanUserWithID - User nicht vorhanden");
            return false;
        }
    }
}
