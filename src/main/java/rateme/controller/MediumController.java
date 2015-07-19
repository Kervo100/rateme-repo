package rateme.controller;

import rateme.services.CategoryService;
import rateme.services.MediumService;
import rateme.services.UserService;
import rateme.entity.Category;
import rateme.entity.Medium;
import rateme.entity.User;

import java.util.List;

/**
 * Created by Mo on 13.07.2015.
 */
public class MediumController {

    public static MediumController mediumController = new MediumController();
    private MediumService mediumService = null;
    private UserService userService = null;
    private CategoryService categoryService = null;

    public MediumController() {
        this.mediumService = new MediumService();
        this.userService = new UserService();
        this.categoryService = new CategoryService();
    }

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
        // TODO HTML Befehle
        return false;
    }

    public boolean getMediumList(){
        List<Medium> mediaList = this.mediumService.getMediumList();
        return false;
    }
}
