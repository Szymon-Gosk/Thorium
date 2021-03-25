package thorium.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thorium.interpreting.Interpreter;
import thorium.interpreting.Stylizer;
import thorium.web.models.Query;
import thorium.web.repositories.QueryRepository;
import thorium.web.repositories.RenderRepository;
import thorium.web.services.QueryService;
import thorium.web.services.RenderService;

@Controller
public class InterpreterController {

    private final RenderService rs;
    private final RenderRepository rr;

    private final QueryService qs;
    private final QueryRepository qr;

    public InterpreterController(
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

    @GetMapping("/interpreter")
    public String index(Model model) {
        model.addAttribute("renders", rr.findAll());
        model.addAttribute("query", new Query());
        model.addAttribute("queries", qr.findAll());
        return "interpreter/main";
    }

    @PostMapping("/interpreter")
    public String query(Query query, Errors errors){
        if(errors.hasErrors()){
            return "/?";
        }
        Interpreter it = new Interpreter();
        String s = it.untokenize(Stylizer.tokensToLatex(it.tokenize(query.getContent())));
        query.setContent(s + "\\\\");
        qr.save(query);
        return "redirect:/interpreter";
    }

}
