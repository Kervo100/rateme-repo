/**
 * Created by Mo on 15.06.2015.
 */

package rateme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Start {
    public static void main (String[] args) {
        System.out.println("rateme start");

        SpringApplication.run(Start.class, args);

        //UserController userController = new UserController();
        //User moritz = new User("Moritz Ellmers", "mers@web.de", "1234", true);
        //userController.createObject(moritz);
        //User mo = userService.getUserByName("Moritz Ellmers");
        //System.out.println(mo.getUsername());

        //MediumService mediumService = new MediumService();
        //System.out.println(medium.getTimestamp());

        //HibernateUtil.shutdown();
        System.out.println("rateme close");
    }
}
