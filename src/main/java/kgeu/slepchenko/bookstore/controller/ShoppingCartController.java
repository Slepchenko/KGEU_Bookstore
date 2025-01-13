package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.service.CartItemService;
import kgeu.slepchenko.bookstore.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shoppingCart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public String create() {
        return "redirect:/shoppingCart/";
    }

    @GetMapping("/{id}")
    public String findShoppingCartById(@PathVariable int id, Model model) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findCartById(id);
        if (optionalShoppingCart.isEmpty()) {
            model.addAttribute("shoppingCart", null);
            return "cart";
        }
        ShoppingCart shoppingCart = shoppingCartService.findCartById(id).get();
        List<CartItem> cartItems = shoppingCart.getItems();
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartItems", shoppingCart.getItems());
        return "cart";
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id) {
        shoppingCartService.deleteCart(id);
        return "redirect:/index";
    }

}
