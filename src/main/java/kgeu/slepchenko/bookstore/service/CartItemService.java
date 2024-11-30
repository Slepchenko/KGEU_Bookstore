package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.CartItem;

public interface CartItemService {

    void addItemToCart(int cartId, CartItem item) ;

    void removeItemFromCart(int cartId, int itemId);

}
