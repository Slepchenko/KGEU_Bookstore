package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.filter.AddUserModel;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/add/{bookId}")
    public String addItemToCart(@PathVariable int bookId, Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        User user = (User) model.getAttribute("user");
        System.err.println(user.getShoppingCart());
        cartItemService.addItemToCart(bookId, user);
        return "redirect:/books/"+ bookId;
    }

    public String removeItemFromCart() {

        return "redirect:/books/";
    }

}
