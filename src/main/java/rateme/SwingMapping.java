package rateme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by thorben on 26/07/15.
 */
public class SwingMapping {
    /*
    @RequestMapping(value = {"/", "/index", "/home"})
    public ModelAndView showMediumList(){
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("page", "mediumList");
        modelAndView.addObject("title", "RateMe");
        modelAndView.addObject("message", "Hello World");

        return modelAndView;
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {
        System.out.println("User> email: " + email + " password: " + password);


        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    @RequestMapping("/impressum")
    public ModelAndView showImpressum() {
        ModelAndView modelAndView = new ModelAndView("impressum");

        return modelAndView;
    }

    @RequestMapping("*")
    public ModelAndView show404Page() {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("page", "404");
        modelAndView.addObject("title", "404 Page not found | RateMe");

        return modelAndView;
    }
    */
}
