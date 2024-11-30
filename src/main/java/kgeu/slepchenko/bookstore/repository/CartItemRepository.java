package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.CartItem;

public interface CartItemRepository {

    void addItemToCart(int cartId, CartItem item) ;

    void removeItemFromCart(int cartId, int itemId);

}
