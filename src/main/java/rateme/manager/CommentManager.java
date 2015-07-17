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

    public boolean deleteComment(int mediumID, int commentID, int userID) {
        User user = userController.getUserByID(userID);
        Medium medium = mediumController.getMediumByID(mediumID);
        Comment comment = commentController.getCommentByID(commentID);

        if(user != null && medium != null && comment != null) {
            if(user.isAdmin() == true || user == comment.getUser()) {

                return true;
            }
            else {
                System.out.println("deleteComment - User nicht berechtigt, den Kommentar zu löschen");
                return false;
            }
        }
        else {
            System.out.println("deleteComment - eines der Objekte nicht vorhanden");
            return false;
        }
    }
}
