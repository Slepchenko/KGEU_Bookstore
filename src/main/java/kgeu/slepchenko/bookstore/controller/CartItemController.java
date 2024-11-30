package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart/item")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/add/{cartId}")
    public String addItemToCart(@PathVariable int cartId, @ModelAttribute CartItem item) {
        cartItemService.addItemToCart(cartId, item);
        return "redirect:/cart/" + cartId;
    }

    @PostMapping("/remove/{cartId}/{itemId}")
    public String removeItemFromCart(@PathVariable int cartId, @PathVariable int itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return "redirect:/cart/" + cartId;
    }

}
