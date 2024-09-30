package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class IndexController {

    private final CategoryService categoryService;

    private final BookService bookService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, @PathVariable("pageNumber") int pageNumber) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", bookService.findByPagination(pageNumber, 6));
        long totalBooks = bookService.getAllBooksSize();
        int totalPages = (int) Math.ceil((double) totalBooks / 6);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "index";
    }

}
