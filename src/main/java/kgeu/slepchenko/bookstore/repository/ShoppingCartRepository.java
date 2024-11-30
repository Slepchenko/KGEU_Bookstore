package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartRepository {

    ShoppingCart save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findCartById(int cartId);

    void updateCart(ShoppingCart cart);

    void deleteCart(int cartId);

}
