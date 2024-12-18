package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public String createCart() {
        return "redirect:/shoppingCart/";
    }

    @GetMapping("/{id}")
    public String getCart(@PathVariable int id, Model model) {
        ShoppingCart cart = shoppingCartService.findCartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id) {
        shoppingCartService.deleteCart(id);
        return "redirect:/index";
    }

}
