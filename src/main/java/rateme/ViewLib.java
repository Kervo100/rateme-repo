package rateme;

import org.springframework.web.servlet.ModelAndView;
import rateme.entity.Link;
import rateme.entity.Medium;
import rateme.entity.User;
import rateme.services.LinkService;
import rateme.services.MediumService;
import rateme.services.UserService;

import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Created by thorben on 28/07/15.
 */
public class ViewLib {
    private static ViewLib mViewLib = new ViewLib();

    private UserService userService;
    private MediumService mediumService;
    private LinkService linkService;

    public ViewLib() {
        this.userService = new UserService();
        this.mediumService = new MediumService();
        this.linkService = new LinkService();
    }

    public static ViewLib activeViewLib() {
        return mViewLib;
    }

    public ModelAndView getView(String loginCookie, String page) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("page", page);
        modelAndView.addObject("loginCookie", loginCookie);
        modelAndView.addObject("title", this.getTitleForPage(page));

        if(!loginCookie.equals("false")) {
            User usr = userService.getUserByID(Integer.parseInt(loginCookie));
            if(usr.isAdmin()) {
                modelAndView.addObject("isAdmin", "true");
            }
        }
        else {
            modelAndView.addObject("isAdmin", "false");
        }

        if(page.equals("medium-list")) {
            List<Medium> mediaList = this.mediumService.getMediumList();
            modelAndView.addObject("mediaList", mediaList);
            List<Link> linkList = this.linkService.getLinkList();
            modelAndView.addObject("linkList", linkList);
        }


        return modelAndView;
    }

    private String getTitleForPage(String page) {
        String title;
        String standardTitle = " | Rate Me";

        switch (page) {
            case "share":
                title = "Share";
                break;
            case "register":
                title = "Register User";
                break;
            case "impressum":
                title = "Impressum";
                break;
            case "medium-list":
                title = "Home";
                break;
            case "medium-detail":
                title = "Medium Detail";
                break;
            case "medium-detail-youtube":
                title = "Medium Detail";
                break;
            default:
                title = "Error";
                break;
        }

        return title + standardTitle;
    }
}
