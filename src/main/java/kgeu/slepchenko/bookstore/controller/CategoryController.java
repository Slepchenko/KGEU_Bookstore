package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/allCategories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "/test";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable int id, Model model) {
        System.err.println(categoryService.findById(id));
        model.addAttribute("category", categoryService.findById(id).get());
        return "/test";
    }

}
