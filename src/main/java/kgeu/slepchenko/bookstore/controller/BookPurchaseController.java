package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.filter.AddUserModel;
import kgeu.slepchenko.bookstore.model.BookPurchase;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.service.BookPurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/bookPurchase")
public class BookPurchaseController {

    private final BookPurchaseService bookPurchaseService;

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute BookPurchase bookPurchase, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        User user = (User) model.getAttribute("user");
        bookPurchase.setUser(user);

        bookPurchaseService.create(bookPurchase);
        return "redirect:/books/bookByPagination";
    }

}
