package sam.biblio.api.controller;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccueilController {
    private Logger logger = LoggerFactory.logger(AccueilController.class);

    @RequestMapping("/")
    public String home(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String message) {
        return "accueil";
    }
}
