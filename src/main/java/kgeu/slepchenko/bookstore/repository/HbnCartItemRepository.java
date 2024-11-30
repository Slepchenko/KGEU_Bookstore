package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HbnCartItemRepository implements CartItemRepository {

    @Override
    public void addItemToCart(int cartId, CartItem item) {

    }

    @Override
    public void removeItemFromCart(int cartId, int itemId) {

    }
}
