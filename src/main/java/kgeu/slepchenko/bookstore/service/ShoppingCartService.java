package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartService {

    ShoppingCart save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findCartById(int cartId);

    Optional<ShoppingCart> updateCart(ShoppingCart cart);

    void deleteCart(int cartId);
}
