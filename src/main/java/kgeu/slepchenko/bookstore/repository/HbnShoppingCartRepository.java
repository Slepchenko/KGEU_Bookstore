package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnShoppingCartRepository implements ShoppingCartRepository{

    CrudRepository crudRepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        crudRepository.run(session -> session.persist(shoppingCart));
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> findCartById(int cartId) {
        return Optional.empty();
    }

    @Override
    public void updateCart(ShoppingCart cart) {

    }

    @Override
    public void deleteCart(int cartId) {

    }
}
