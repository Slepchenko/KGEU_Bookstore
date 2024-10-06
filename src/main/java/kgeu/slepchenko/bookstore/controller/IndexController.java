package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
public class IndexController {

    private final CategoryService categoryService;

    private final BookService bookService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        int page = 1;
        int size = 6;
        model.addAttribute("books", bookService.findByPagination(page, size));
        long totalBooks = bookService.getAllBooksSize();
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", bookService.findByPagination(1, 6));
        model.addAttribute("category", null);
        return "/index";
    }

}
