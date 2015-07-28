package rateme.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rateme.entity.*;
import rateme.services.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Mo on 13.07.2015.
 */

@Controller
public class MediumController {
    private MediumService mediumService = null;
    private UserService userService = null;
    private CategoryService categoryService = null;
    private LinkService linkService = null;
    private PlatformService platformService = null;

    public MediumController() {
        this.mediumService = new MediumService();
        this.userService = new UserService();
        this.categoryService = new CategoryService();
        this.linkService = new LinkService();
        this.platformService = new PlatformService();
    }
/*
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
*/

    @RequestMapping("/share")
    public ModelAndView shareMedium(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("loginCookie", loginCookie);
        modelAndView.addObject("page", "share");
        modelAndView.addObject("title", "Teile ein Medium | RateMe");

        return modelAndView;
    }

    @RequestMapping(value = "/medium-shared", method = RequestMethod.POST)
    public ModelAndView shareMediumSendRequest(
            @RequestParam("medium-title") String name,
            @RequestParam("medium-link") String url,
            @RequestParam("medium-category") int categoryId,
            @RequestParam("medium-description") String description,
            @RequestParam("user-id") int userId,
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {

        System.out.println(name + " " + url + " " + categoryId + " " + description);
        String message = "<p class='alert alert-danger'>Das Medium konnte nicht geteilt werden!</p>";

        // Get User and Category
        User user = this.userService.getUserByID(userId);
        Category category = this.categoryService.getCategoryById(categoryId);
        // Create a new Medium in database
        Medium medium = new Medium(name, description, user, category);
        // Create Verlinkung in database with returned mediumId
        Integer mediumId = this.mediumService.createObject(medium);

        if(mediumId != null) {
            Platform platform = null;
            try {
                platform = new Platform(url);
                Integer platformId = this.platformService.createObject(platform);
                System.out.println("PlatformID: " + platformId);
                if(platformId != null) {
                    Medium m = this.mediumService.getMediumById(mediumId);
                    Platform p = this.platformService.getPlatformById(platformId);
                    Link link = new Link(url, p, m);
                    Integer linkId = this.linkService.createObject(link);
                    if (linkId != null) {
                        message = "<p class='alert alert-success'>Das Medium wurde erfolgreich geteilt!</p>";
                    }
                    else {
                        this.mediumService.deleteObject(medium);
                        this.platformService.deleteObject(platform);
                    }
                }
                else {
                    this.mediumService.deleteObject(this.mediumService.getMediumById(mediumId));
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return showMediumList(loginCookie, message);
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public ModelAndView showMediumList(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                       String message){

        ModelAndView modelAndView = new ModelAndView("index");
        if(!loginCookie.equals("false")) {
            modelAndView.addObject("title", "RateMe - " + loginCookie);
        }
        else {
            modelAndView.addObject("title", "RateMe");
        }
        modelAndView.addObject("loginCookie", loginCookie);
        modelAndView.addObject("page", "mediumList");
        modelAndView.addObject("message", message);

        List<Medium> mediaList = this.mediumService.getMediumList();
        modelAndView.addObject("mediaList", mediaList);

        return modelAndView;
    }


    @RequestMapping("/impressum")
    public ModelAndView showImpressum() {
        ModelAndView modelAndView = new ModelAndView("impressum");

        return modelAndView;
    }


    @RequestMapping("*")
    public ModelAndView show404Page(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("loginCookie", loginCookie);
        modelAndView.addObject("page", "404");
        modelAndView.addObject("title", "404 Page not found | RateMe");

        return modelAndView;
    }
}
