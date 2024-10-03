package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final CategoryService categoryService;


    @GetMapping("/allCategoryBook")
    public String getAllCategoryBook(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @GetMapping("/bookByPagination")
    public String getBookByPagination(/*@PathVariable int page,*/ Model model) {
        int page = 1;
        model.addAttribute("books", bookService.findByPagination(page, 6));
        return "/";
    }

    @GetMapping("/getAllBooksSize")
    public String getAllBooksSize(Model model) {
        model.addAttribute("all", bookService.getAllBooksSize());
        return "/test";
    }

    @GetMapping("/getCategoryPagination")
    public String getCategoryPagination(Model model, @RequestParam(name = "cat", required = false) String category) {
        model.addAttribute("books", bookService.findByCategoryPagination(category, 1, 6));
        model.addAttribute("categories", categoryService.findAll());
        return "/index";
    }

}
