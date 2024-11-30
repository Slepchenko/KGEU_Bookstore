package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleShoppingCartService implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findCartById(int cartId) {
        return shoppingCartRepository.findCartById(cartId);
    }

    @Override
    public void updateCart(ShoppingCart cart) {
        shoppingCartRepository.updateCart(cart);
    }

    @Override
    public void deleteCart(int cartId) {
        shoppingCartRepository.deleteCart(cartId);
    }
}
