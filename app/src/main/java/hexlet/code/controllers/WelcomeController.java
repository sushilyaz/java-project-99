package hexlet.code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping
    @ResponseBody
    public String welcome() {
        return "Welcome to Spring";
    }
}
