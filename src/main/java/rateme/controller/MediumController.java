package rateme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import rateme.services.CategoryService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.Category;
import rateme.entity.Medium;
import rateme.entity.User;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mo on 13.07.2015.
 */

@Controller
public class MediumController {
    public static MediumController mediumController = new MediumController();
    private MediumService mediumService = null;
    private UserService userService = null;
    private CategoryService categoryService = null;

    public MediumController() {
        this.mediumService = new MediumService();
        this.userService = new UserService();
        this.categoryService = new CategoryService();
    }

    public boolean addMedium(String name, String description, int userId, int categoryId){
        // Get Data from HTML Forms
        User user = userService.getUserByID(userId);
        Category category = this.categoryService.getCategoryById(categoryId);
        // Create a new Medium in database
        Medium medium = new Medium(name, description, user, category);
        if (this.mediumService.createObject(medium)){
            System.out.println("new medium created");
        }
        else {
            System.out.println("medium could not create");
        }
        return false;
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public ModelAndView showMediumList(){
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("page", "mediumList");
        modelAndView.addObject("title", "RateMe");
        modelAndView.addObject("message", "Hello World");

        return modelAndView;
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {
        System.out.println("login for User: " + email + " requested");

        User remoteUser = userService.getUserByEmail(email);
        if(remoteUser != null) {
            if(remoteUser.getPassword().equals(password)) {
                System.out.println("User erfolgreich eingeloggt");
            }
        }
        else {
            System.out.println("dieser User existiert nicht");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("page", "mediumList");

        return modelAndView;
    }

    @RequestMapping("/impressum")
    public ModelAndView showImpressum() {
        ModelAndView modelAndView = new ModelAndView("impressum");

        return modelAndView;
    }

    @RequestMapping("*")
    public ModelAndView show404Page() {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("page", "404");
        modelAndView.addObject("title", "404 Page not found | RateMe");

        return modelAndView;
    }
}
