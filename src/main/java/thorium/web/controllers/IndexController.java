package thorium.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thorium.web.models.Query;
import thorium.web.repositories.QueryRepository;
import thorium.web.repositories.RenderRepository;
import thorium.web.services.QueryService;
import thorium.web.services.RenderService;

@Controller
public class IndexController {

    private static final Logger log = LogManager.getLogger(IndexController.class.getName());

    private final RenderService rs;
    private final RenderRepository rr;

    private final QueryService qs;
    private final QueryRepository qr;

    public IndexController(
        RenderService rs,
        RenderRepository rr,
        QueryService qs,
        QueryRepository qr
    ) {
        this.rs = rs;
        this.rr = rr;
        this.qs = qs;
        this.qr = qr;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("renders", rr.findAll());
        model.addAttribute("query", new Query());
        model.addAttribute("queries", qr.findAll());
        return "index";
    }

    @PostMapping("/")
    public String query(Query query, Errors errors){
        if(errors.hasErrors()){
            return "/?";
        }
        qr.save(query);
        return "redirect:/";
    }

}
