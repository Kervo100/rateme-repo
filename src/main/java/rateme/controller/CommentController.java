package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rateme.ViewLib;
import rateme.entity.Medium;
import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.Comment;
import rateme.entity.User;

@Controller
public class CommentController {

    private UserService userService;
    private CommentService commentService;
    private MediumService mediumService;

    public CommentController() {
        userService = new UserService();
        commentService = new CommentService();
        mediumService = new MediumService();
    }

    @RequestMapping("/medium-detail/{mediumId}/comment-send")
    public ModelAndView postComment(
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @PathVariable("mediumId") String mediumId,
            @RequestParam("page") String page,
            @RequestParam("comment-text") String text
            ) {

        User user = this.userService.getUserByID(Integer.parseInt(loginCookie));
        Medium medium = this.mediumService.getMediumById(Integer.parseInt(mediumId));
        Comment comment = new Comment(text, user, medium);
        this.commentService.createObject(comment);

        return ViewLib.activeViewLib().getView(loginCookie, page);
    }

    public boolean deleteComment(int commentID, int userID) {
        User user = userService.getUserByID(userID);
        Comment comment = commentService.getCommentByID(commentID);

        if(user != null && comment != null) {
            if(user.isAdmin() == true || user == comment.getUser()) {
                commentService.deleteObject(comment);
                return true;
            }
            else {
                System.out.println("deleteComment - User nicht berechtigt, den Kommentar zu loeschen");
                return false;
            }
        }
        else {
            System.out.println("deleteComment - user oder comment nicht vorhanden");
            return false;
        }
    }
}