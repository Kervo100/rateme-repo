package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rateme.ViewLib;
import rateme.services.UserService;
import rateme.entity.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }


/*----------Register------------------------------------------------*/


    @RequestMapping("/register")
    public ModelAndView showRegister(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {
        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "register");

        return modelAndView;
    }


    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public ModelAndView registerUserRequest(
            @RequestParam("username") String username,
            @RequestParam("email") String mail,
            @RequestParam("password") String password,
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @RequestParam ("passwordConfirm") String passwordConfirm,
            HttpServletResponse response) {

        ModelAndView modelAndView;

        User newUser = new User(username, mail, password);
        if (!checkIfExist(mail)) {
            if(doubleCheckPassword(password,passwordConfirm)){
                this.userService.createObject(newUser);
                System.out.println(username + " " + mail + " " + password + " has been created successfully");
            } else {
                modelAndView = ViewLib.activeViewLib().getView(loginCookie, "confirm-password");
                return  modelAndView;
            }
        } else {
            modelAndView = ViewLib.activeViewLib().getView(loginCookie, "user-exists");
            return modelAndView;
        }

        loginCookie = this.setLoginCookie(loginCookie, mail, password, response).getValue();
        modelAndView = ViewLib.activeViewLib().getView(loginCookie, "welcome");
        return modelAndView;
    }

    public boolean checkIfExist(/*String username,*/ String mail) {

        //User checkIfExistUser = this.userService.getUserByName(username);
        User checkIfExistMail = this.userService.getUserByEmail(mail);
        if (/*checkIfExistUser == null || */checkIfExistMail == null) {
            return false;
        }
        else {
            System.out.println("User already exist");
        }

        return true;
    }

    public boolean doubleCheckPassword(String password, String passwordConfirm){
        if ((password).equals(passwordConfirm)) {
            return true;
        } else {
            System.out.println("Password wrong");
        }
        return false;
    }




/*<--------------Login--------------------------------------------------->*/



    @RequestMapping("/user-list")
    public ModelAndView showAllUsers(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {
        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "user-list");
        return modelAndView;
    }

    @RequestMapping(value = {"/userDelete/{userId}"}, method=RequestMethod.GET)
    public ModelAndView deleteUser(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                     @PathVariable(value = "userId") String userId) {
        String message = null;

        if(!loginCookie.equals("false") && userService.getUserByID(Integer.parseInt(loginCookie)).isAdmin()) {
            User user = userService.getUserByID(Integer.parseInt(userId));
            if (userService.deleteObject(user)) {
                message = "<p class='alert alert-success'>User gel&ouml;scht</p>";
            } else {
                message = "<p class='alert alert-danger'>Error</p>";
            }
        }

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "user-list");
        modelAndView.addObject("message", message);
        modelAndView.addObject("messageTitle", "User delete");

        return modelAndView;
    }

    @RequestMapping(value = {"/userToogleAdmin/{userId}"}, method=RequestMethod.GET)
    public ModelAndView userToogleAdmin(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                     @PathVariable(value = "userId") String userId) {
        String message = null;
        if(!loginCookie.equals("false") && userService.getUserByID(Integer.parseInt(loginCookie)).isAdmin()) {
            User user = userService.getUserByID(Integer.parseInt(userId));
            user.setIsAdmin(!user.isAdmin());
            if (userService.updateObject(user)) {
            } else {
                message = "<p class='alert alert-danger'>Error</p>";
            }
        }

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "user-list");
        modelAndView.addObject("message", message);
        modelAndView.addObject("messageTitle", "User toggle admin");

        return modelAndView;
    }

    @RequestMapping(value = {"/userToogleBlocked/{userId}"}, method=RequestMethod.GET)
    public ModelAndView userToogleBlocked(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                        @PathVariable(value = "userId") String userId) {
        String message = null;
        if(!loginCookie.equals("false") && userService.getUserByID(Integer.parseInt(loginCookie)).isAdmin()) {
            User user = userService.getUserByID(Integer.parseInt(userId));
            user.setIsBlocked(!user.isBlocked());
            if (userService.updateObject(user)) {
            } else {
                message = "<p class='alert alert-danger'>Error</p>";
            }
        }

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "user-list");
        modelAndView.addObject("message", message);
        modelAndView.addObject("messageTitle", "User toggle blocked user");

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              final RedirectAttributes redirectAttributes) {

        Cookie cookie = this.setLoginCookie(loginCookie, email, password, response);

        if (cookie.getValue().equals("password")) {
            redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>Password wrong</p>");
        }
        else if (cookie.getValue().equals("user")) {
            redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>User don't exist.</p>" +
                    "<a href='/register' class='alert-link'>If you don't have an account, create a new here.</a>");
        }
        redirectAttributes.addFlashAttribute("messageTitle", "Login failed");

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:"+referer);
    }

    private Cookie setLoginCookie(String loginCookie, String email, String password, HttpServletResponse response) {
        Cookie newCookie = new Cookie("rateMe_LoggedIn", "false");
        if (loginCookie.equals("false")) {
            User remoteUser = userService.getUserByEmail(email);
            if (remoteUser != null) {
                if (remoteUser.getPassword().equals(password)) {
                    System.out.println(email + " erfolgreich eingeloggt");
                    newCookie.setValue(remoteUser.getId().toString());
                    newCookie.setMaxAge(86400); //1 day
                    response.addCookie(newCookie);
                } else {
                    newCookie.setValue("password");
                    System.out.println("falsches Passwort");
                }
            } else {
                newCookie.setValue("user");
                System.out.println("dieser User existiert nicht");
            }
        } else {
            response.addCookie(newCookie);
            System.out.println(email + " erfolgreich ausgeloggt");
        }

        return newCookie;
    }
}
