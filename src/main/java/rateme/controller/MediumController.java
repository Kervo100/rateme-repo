package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rateme.ViewLib;
import rateme.entity.*;
import rateme.services.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.*;

@Controller
public class MediumController {
    private MediumService mediumService = null;
    private UserService userService = null;
    private CategoryService categoryService = null;
    private LinkService linkService = null;
    private PlatformService platformService = null;
    private CommentService commentService = null;
    private RatingService ratingService = null;

    public MediumController() {
        this.mediumService = new MediumService();
        this.userService = new UserService();
        this.categoryService = new CategoryService();
        this.linkService = new LinkService();
        this.platformService = new PlatformService();
        this.commentService = new CommentService();
        this.ratingService = new RatingService();
    }

    @RequestMapping("/share")
    public ModelAndView shareMedium(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie){
        return ViewLib.activeViewLib().getView(loginCookie, "share");
    }

    @RequestMapping(value = "/medium-shared", method = RequestMethod.POST)
    public ModelAndView shareMediumSendRequest(
            @RequestParam("medium-title") String name,
            @RequestParam("medium-link") String url,
            @RequestParam("medium-category") int categoryId,
            @RequestParam("medium-description") String description,
            @RequestParam("user-id") int userId,
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

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

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("messageTitle" , "Medium share");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public ModelAndView showMediumList(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {

        return ViewLib.activeViewLib().getView(loginCookie, "medium-list");
    }

    @RequestMapping("/medium/{mediumId}")
    public ModelAndView showMediumDetails(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                          @PathVariable(value = "mediumId") Integer mediumId) {

        Medium medium = this.mediumService.getMediumById(mediumId);
        Link link = this.linkService.getLinkByMediumId(mediumId);

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "medium-detail");
        String mediumDetailHead = "";

        if(link.getPlatform().getName().equals("Youtube")) {
            String linkUrl = link.getUrl();
            String[] parts = linkUrl.split("\\.com/");
            linkUrl = parts[0] + ".com/v/" + parts[1];
            System.out.println(linkUrl);
            modelAndView.addObject("linkUrl", linkUrl);
            mediumDetailHead = "youtube";
        }

        modelAndView.addObject("mediumDetailHead",mediumDetailHead);
        modelAndView.addObject("medium", medium);
        modelAndView.addObject("link", link);

        List<Comment> commentList = this.commentService.getCommentListByMedium(medium);
        modelAndView.addObject("commentList", commentList);

        Byte mediumRating = this.ratingService.getAverageRatingByMediumId(medium);
        modelAndView.addObject("mediumRating", mediumRating);

        return modelAndView;
    }

    @RequestMapping(value = {"/mediumDelete/{mediumId}"}, method=RequestMethod.GET)
    public ModelAndView deleteMedium(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                                          @PathVariable(value = "mediumId") String mediumId) {
        String message = null;
        if(!loginCookie.equals("false") && userService.getUserByID(Integer.parseInt(loginCookie)).isAdmin()) {
            Medium medium = mediumService.getMediumById(Integer.parseInt(mediumId));
            if (mediumService.deleteObject(medium)) {
                message = "<p class='alert alert-success'>Medium gel&ouml;scht</p>";
            } else {
                message = "<p class='alert alert-danger'>Error</p>";
            }
        }

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "medium-list");
        modelAndView.addObject("messageTitle", "Medium deleted");
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
                               @RequestParam("searchTerm") String searchTerm,
                               @RequestParam("medium-category") int categoryId) {

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "medium-list");
        modelAndView.getModel().remove("mediaList");
        modelAndView.getModel().remove("linkList");

        searchTerm = searchTerm.toLowerCase();
        List<Medium> mediaList = this.mediumService.getMediumList();
        Category category = this.categoryService.getCategoryById(categoryId);
        List<Link> linkList = this.linkService.getLinkList();

        ArrayList<Medium> filteredMedia = new ArrayList<>();
        ArrayList<Link> filteredLinks = new ArrayList<>();
        //Search in Title
        for(int i = 0; i < mediaList.size(); i++) {
            String name = mediaList.get(i).getName().toLowerCase();
            if(mediaList.get(i).getCategory().getId() == category.getId()
                    && name.contains(searchTerm)) {
                filteredMedia.add(mediaList.get(i));
                filteredLinks.add(linkList.get(i));
            }
        }
        //Search in Description
        for(int i = 0; i < mediaList.size(); i++) {
            String desc = mediaList.get(i).getDescription().toLowerCase();
            if(mediaList.get(i).getCategory().getId() == category.getId()
                    && desc.contains(searchTerm)) {

                if(!filteredMedia.contains(mediaList.get(i))) {
                    filteredMedia.add(mediaList.get(i));
                    filteredLinks.add(linkList.get(i));
                }
            }
        }
        modelAndView.addObject("mediaList", filteredMedia);
        modelAndView.addObject("linkList", filteredLinks);

        return modelAndView;
    }

    @RequestMapping("/impressum")
    public ModelAndView showImpressum(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {
        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "impressum");

        return modelAndView;
    }

    @RequestMapping("*")
    public ModelAndView show404Page(@CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie) {

        ModelAndView modelAndView = ViewLib.activeViewLib().getView(loginCookie, "404");

        return modelAndView;
    }
}
