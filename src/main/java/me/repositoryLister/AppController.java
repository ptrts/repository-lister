package me.repositoryLister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AppController {

    @Autowired
    private UserContext userContext;

    @GetMapping
    public String root(Model model) {
        model.addAttribute("userContext", userContext);
        return "root";
    }
}
