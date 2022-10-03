package gosk.szymon.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {

    @GetMapping(value = "/start")
    public String start() {
        return "cards/start";
    }

    @GetMapping(value = "/faq")
    public String faq() {
        return "cards/faq";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "cards/about";
    }

}
