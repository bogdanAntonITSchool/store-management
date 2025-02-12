package ro.itschool.store_management.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// The difference between @Controller and @RestController is that @Controller is used to create a controller that returns a view,
// while @RestController is used to create a controller that returns an object.
@Controller
@RequestMapping("/home-bogdan")
public class HomeController {

    // The methods in a controller usually return a String that represents the name of the view that will be rendered.
    // The views can be found in the src/main/resources/templates directory.
    // By using spring-boot-starter-thymeleaf, we can use Thymeleaf as the template engine, and we have everything we need to create a simple web application.
    @GetMapping
    public String getHomePage(HttpServletRequest request) {

        // We can add attributes to the request object that will be used in the view.
        // This will provide us the ability to pass data from the controller to the view (dynamic frontends).
        request.setAttribute("user", "Bogdan");
        return "home";
    }

}
