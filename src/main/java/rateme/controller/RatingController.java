package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rateme.entity.Comment;
import rateme.entity.Medium;
import rateme.entity.Rating;
import rateme.entity.User;
import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.RatingService;
import rateme.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RatingController {

    private UserService userService;
    private RatingService ratingService;
    private MediumService mediumService;

    public RatingController() {
        userService = new UserService();
        ratingService = new RatingService();
        mediumService = new MediumService();
    }

    @RequestMapping("/medium/{mediumId}/rating-send")
    public ModelAndView postComment(
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @PathVariable("mediumId") String mediumId,
            @RequestParam("score") String score,
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request
            ) {

        String referer = request.getHeader("Referer");
        ModelAndView modelAndView = new ModelAndView("redirect:"+referer);

        if (!loginCookie.equals("false")) {
            User user = this.userService.getUserByID(Integer.parseInt(loginCookie));
            Medium medium = this.mediumService.getMediumById(Integer.parseInt(mediumId));
            Rating rating = new Rating(Byte.parseByte(score), user, medium);
            this.ratingService.createObject(rating);
        }
        else {
            redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>Please login before you can rate</p>");
            redirectAttributes.addFlashAttribute("messageTitle", "Rating failed");
        }

        return modelAndView;
    }

}