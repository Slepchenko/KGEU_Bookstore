package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnShoppingCartRepository implements ShoppingCartRepository {

    CrudRepository crudRepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        crudRepository.run(session -> session.persist(shoppingCart));
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> findCartById(int id) {
        return crudRepository.optional(
                "select sc from ShoppingCart sc " +
                        "join fetch sc.items i " +
                        "join fetch i.book b " +
                        "join fetch b.category c " +
                        "join fetch sc.user where sc.id = :scId ",
                ShoppingCart.class,
                Map.of("scId", id)
        );
    }

    @Override
    public void updateCart(ShoppingCart cart) {

    }

    @Override
    public void deleteCart(int cartId) {

    }
}
