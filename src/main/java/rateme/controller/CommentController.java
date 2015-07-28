package rateme.controller;

import rateme.services.CommentService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.Comment;
import rateme.entity.Medium;
import rateme.entity.User;

/**
 * Created by thorben on 13/07/15.
 */
public class CommentController {

    public static CommentController commentManager = new CommentController();
    private UserService userService;
    private CommentService commentService;
    private MediumService mediumService;

    public CommentController() {
        userService = new UserService();
        commentService = new CommentService();
        mediumService = new MediumService();
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