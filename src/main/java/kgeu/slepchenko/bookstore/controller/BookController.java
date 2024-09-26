package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/allBooks")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "/index";
    }

}
