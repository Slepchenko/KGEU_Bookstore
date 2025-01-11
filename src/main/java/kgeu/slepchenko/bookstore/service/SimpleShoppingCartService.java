package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.model.ShoppingCart;
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
    public Optional<ShoppingCart> findCartById(int id) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findCartById(id);
        if (optionalShoppingCart.isEmpty()) {
            return Optional.empty();
        }
        ShoppingCart shoppingCart = optionalShoppingCart.get();
        shoppingCart.setTotalPrice(shoppingCart.getItems().stream().map(CartItem::getPrice).reduce(Integer::sum).get());
        return Optional.of(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> updateCart(ShoppingCart cart) {
        return shoppingCartRepository.updateCart(cart);
    }

    @Override
    public void deleteCart(int cartId) {
        shoppingCartRepository.deleteCart(cartId);
    }
}
