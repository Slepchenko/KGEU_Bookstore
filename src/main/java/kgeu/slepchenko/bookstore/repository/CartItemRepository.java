package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.CartItem;

public interface CartItemRepository {

    void addItemToCart(CartItem item);

    void removeItemFromCart(int itemId);

}
