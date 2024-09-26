package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final CategoryService categoryService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

}
