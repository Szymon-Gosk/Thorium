package gosk.szymon.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping(value = "/login")
    public String login() {
        return "/authorization/login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "/authorization/register";
    }

}
