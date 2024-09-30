package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final CategoryService categoryService;

    private final BookService bookService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", bookService.findByPagination(1, 6));
        return "index";
    }

}
