package thorium.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thorium.web.repositories.RenderRepository;
import thorium.web.services.RenderService;

@Controller
public class PredefBuildController {

    private final RenderService rs;
    private final RenderRepository rr;

    public PredefBuildController(
            RenderService rs,
            RenderRepository rr
    ) {
        this.rs = rs;
        this.rr = rr;
    }

    @GetMapping("/predefined")
    public String index(Model model) {
        model.addAttribute("renders", rr.findAll());
        return "predefined/main";
    }

}
