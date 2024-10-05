package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getBookByPagination(@RequestParam(defaultValue = "1") int page, Model model) {
        int size = 6;
        long totalBooks = bookService.getAllBooksSize();
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        model.addAttribute("books", bookService.findByPagination(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @GetMapping("/getAllBooksSize")
    public String getAllBooksSize(Model model) {
        model.addAttribute("all", bookService.getAllBooksSize());
        return "/test";
    }

    @GetMapping("/getCategoryPagination")
    public String getCategoryPagination(Model model, @RequestParam(name = "cat", required = false) String category, @RequestParam(defaultValue = "1") int page) {
        int size = 6;
        long totalBooks = bookService.getAllBooksSizeByCategory(category);
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        model.addAttribute("books", bookService.findByCategoryPagination(category, page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", category);
        return "/index";
    }

    @GetMapping("/{id}")
    public String getBookById(Model model, @PathVariable int id) {
        model.addAttribute("book", bookService.findById(id).get());
        return "test";
    }

    @GetMapping("/modal-book/{id}")
    public String modalBook(@PathVariable int id, Model model) {
        System.err.println(id);
        model.addAttribute("book", bookService.findById(id).get());
        return "modals";
    }

}
