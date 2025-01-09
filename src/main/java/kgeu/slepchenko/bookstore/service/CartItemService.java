package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.model.User;

public interface CartItemService {

    void addItemToCart(int bookId, User user);

    boolean removeAllItemsFromCart(int id);

}
