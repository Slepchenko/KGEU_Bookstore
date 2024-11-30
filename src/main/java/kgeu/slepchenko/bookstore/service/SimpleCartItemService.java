package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SimpleCartItemService implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public void addItemToCart(int cartId, CartItem item) {
        cartItemRepository.addItemToCart(cartId, item);
    }

    @Override
    public void removeItemFromCart(int cartId, int itemId) {
        cartItemRepository.removeItemFromCart(cartId, itemId);
    }
}
