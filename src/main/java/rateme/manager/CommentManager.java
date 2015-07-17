package rateme.manager;

import rateme.controller.CommentController;
import rateme.controller.MediumController;
import rateme.controller.UserController;
import rateme.entity.Comment;
import rateme.entity.Medium;
import rateme.entity.User;

/**
 * Created by thorben on 13/07/15.
 */
public class CommentManager {
    public static CommentManager commentManager = new CommentManager();
    private UserController userController;
    private CommentController commentController;
    private MediumController mediumController;

    public CommentManager() {
        userController = new UserController();
        commentController = new CommentController();
        mediumController = new MediumController();
    }

    public boolean deleteComment(int commentID, int userID) {
        User user = userController.getUserByID(userID);
        Comment comment = commentController.getCommentByID(commentID);

        if(user != null && comment != null) {
            if(user.isAdmin() == true || user == comment.getUser()) {
                commentController.deleteObject(comment);
                return true;
            }
            else {
                System.out.println("deleteComment - User nicht berechtigt, den Kommentar zu löschen");
                return false;
            }
        }
        else {
            System.out.println("deleteComment - user oder comment nicht vorhanden");
            return false;
        }
    }
}
