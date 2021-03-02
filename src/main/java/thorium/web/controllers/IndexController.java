package thorium.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thorium.web.repositories.RenderRepository;
import thorium.web.services.RenderService;

@Controller
public class IndexController {

    private static final Logger log = LogManager.getLogger(IndexController.class.getName());

    private final RenderService rs;
    private final RenderRepository rr;

    public IndexController(
        RenderService rs,
        RenderRepository rr
    ) {
        this.rs = rs;
        this.rr = rr;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("renders", rr.findAll());
        return "index";
    }

}
