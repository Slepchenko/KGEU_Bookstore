package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.filter.AddUserModel;
import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.Feedback;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.service.BookService;
import kgeu.slepchenko.bookstore.service.CartItemService;
import kgeu.slepchenko.bookstore.service.CategoryService;

import kgeu.slepchenko.bookstore.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final FeedbackService feedbackService;
    private final CartItemService cartItemService;

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
        System.err.println("ALL");
        model.addAttribute("mode", "all");
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
        System.err.println("CATEGORY");
        model.addAttribute("mode", "cat");
        return "index";
    }

    @GetMapping("/{id}")
    public String getBookById(Model model, @PathVariable int id) {
        model.addAttribute("book", bookService.findById(id).get());
        return "book";
    }

    @PostMapping("/sendFeedback")
    public String sendFeedback(HttpSession session,
                               @ModelAttribute Feedback feedBack,
                               @RequestParam("contactMethod") String contactMethod,
                               Model model) {
        Model modelUpdated = AddUserModel.checkInMenu(model, session);
        User currentUser = (User) modelUpdated.getAttribute("user");
        int size = 6;
        long totalBooks = bookService.getAllBooksSize();
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        model.addAttribute("books", bookService.findByPagination(1, size));
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categories", categoryService.findAll());
        feedBack.setFeedback("phone");
        feedBack.setName(currentUser.getName());
        if (contactMethod.equals("email")) {
            feedBack.setFeedback("email");
        }
        feedbackService.save(feedBack);
        return "/index";
    }

    @GetMapping("/search")
    public String searchBook(Model model, @RequestParam(name = "q", required = false) String search, @RequestParam(defaultValue = "1") int page, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        int size = 6;
        long totalBooks = bookService.getSizeSearchedBook(search);
        int totalPages = (int) Math.ceil((double) totalBooks / size);

        model.addAttribute("books", bookService.searchBook(search, page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("searchedText", search);
        System.err.println("SEARCH");
        model.addAttribute("mode", "search");
        return "index";
    }

}
