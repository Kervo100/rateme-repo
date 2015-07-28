package rateme;

import org.springframework.web.servlet.ModelAndView;
import rateme.entity.User;
import rateme.services.UserService;

/**
 * Created by thorben on 28/07/15.
 */
public class ViewLib {
    private static ViewLib mViewLib = new ViewLib();

    private UserService userService;

    public ViewLib() {
        userService = new UserService();
    }

    public static ViewLib viewLib() {
        return mViewLib;
    }

    public ModelAndView getView(String loginCookie, String page) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("page", page);
        modelAndView.addObject("loginCookie", loginCookie);
        modelAndView.addObject("title", page + " | RateMe");

        if(!loginCookie.equals("false")) {
            User usr = userService.getUserByEmail(loginCookie);
            if(usr.isAdmin()) {
                modelAndView.addObject("isAdmin", "true");
            }
        }
        else {
            modelAndView.addObject("isAdmin", "false");
        }



        return modelAndView;
    }
}
