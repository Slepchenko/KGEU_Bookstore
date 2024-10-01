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

    private int page = 1;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", bookService.findByPagination(page, 6));
        return "index";
    }

    @GetMapping("/startPage")
    public String getStart(Model model, @PathVariable("pageNumber") int pageNumber) {
        long totalBooks = bookService.getAllBooksSize();
        int totalPages = (int) Math.ceil((double) totalBooks / 6);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "index";
    }

}
