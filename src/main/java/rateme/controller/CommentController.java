package rateme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rateme.entity.Medium;
import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.Comment;
import rateme.entity.User;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/medium/{mediumId}/comment-send")
    public ModelAndView postComment(
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @PathVariable("mediumId") String mediumId,
            @RequestParam("comment-text") String text,
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        ModelAndView modelAndView = new ModelAndView("redirect:"+referer);

        if (!loginCookie.equals("false")) {
            User user = this.userService.getUserByID(Integer.parseInt(loginCookie));
            Medium medium = this.mediumService.getMediumById(Integer.parseInt(mediumId));
            Comment comment = new Comment(text, user, medium);
            this.commentService.createObject(comment);
        }
        else {
            redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>Please login before you can comment</p>");
            redirectAttributes.addFlashAttribute("messageTitle", "Comment failed");
        }

        return modelAndView;
    }

    @RequestMapping("/medium/{mediumId}/comment-update")
    public ModelAndView updateComment(
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @PathVariable("mediumId") String mediumId,
            @RequestParam("commentId") Integer commentId,
            @RequestParam("edit-comment-text") String text,
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        ModelAndView modelAndView = new ModelAndView("redirect:"+referer);

        if (!loginCookie.equals("false")) {
            Comment comment = this.commentService.getCommentByID(commentId);
            if(!comment.getText().equals(text)) {
                comment.setText(text);
                this.commentService.updateObject(comment);
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>Please login before you can comment</p>");
            redirectAttributes.addFlashAttribute("messageTitle", "Comment failed");
        }

        return modelAndView;
    }

    @RequestMapping("/medium/{mediumId}/comment-delete")
    public ModelAndView deleteComment(
            @CookieValue(value = "rateMe_LoggedIn", defaultValue = "false") String loginCookie,
            @PathVariable("mediumId") String mediumId,
            @RequestParam("commentId") Integer commentId,
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        ModelAndView modelAndView = new ModelAndView("redirect:"+referer);

        User user = this.userService.getUserByID(Integer.parseInt(loginCookie));
        Comment comment = this.commentService.getCommentByID(commentId);

        if(user != null && comment != null) {
            if(user.isAdmin() || user == comment.getUser()) {
                this.commentService.deleteObject(comment);
            }
            else {
                System.out.println("User ist nicht berechtigt, den Kommentar zu loeschen");
                redirectAttributes.addFlashAttribute("message", "<p class='alert alert-danger'>Forbidden to delete comment</p>");
                redirectAttributes.addFlashAttribute("messageTitle", "Comment delete failed");
            }
        }
        else {
            System.out.println("User oder Comment nicht vorhanden");
        }

        return modelAndView;
    }
}