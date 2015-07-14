package rateme.manager;

import rateme.controller.CategoryController;
import rateme.controller.MediumController;
import rateme.controller.UserController;
import rateme.entity.Category;
import rateme.entity.Medium;
import rateme.entity.User;

import java.util.List;

/**
 * Created by Mo on 13.07.2015.
 */
public class MediumManager {

    public static MediumManager mediumManager = new MediumManager();
    private MediumController mediumController = null;
    private UserController userController = null;
    private CategoryController categoryController = null;

    public MediumManager() {
        this.mediumController = new MediumController();
        this.userController = new UserController();
        this.categoryController = new CategoryController();
    }

    public boolean addMedium(String name, String description, int userId, int categoryId){
        // Get Data from HTML Forms
        User user = userController.getUserByID(userId);
        Category category = this.categoryController.getCategoryById(categoryId);
        // Create a new Medium in database
        Medium medium = new Medium(name, description, user, category);
        if (this.mediumController.createObject(medium)){
            System.out.println("new medium created");
        }
        else {
            System.out.println("medium could not create");
        }
        // TODO HTML Befehle
        return false;
    }

    public boolean getMediumList(){
        List<Medium> mediaList = this.mediumController.getMediumList();
        return false;
    }
}
