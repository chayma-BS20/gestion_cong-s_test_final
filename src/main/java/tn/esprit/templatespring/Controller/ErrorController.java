package tn.esprit.templatespring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Gérez l'erreur ici et renvoyez la vue appropriée pour l'erreur.
        return "error"; // Remplacez "error" par le nom de votre page d'erreur.
    }

    public String getErrorPath() {
        return "/error";
    }
}
