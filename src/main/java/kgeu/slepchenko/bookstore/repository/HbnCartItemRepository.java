package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HbnCartItemRepository implements CartItemRepository {

    final private CrudRepository crudRepository;

    @Override
    public void addItemToCart(CartItem item) {
        crudRepository.run(session -> session.merge(item));
    }

    @Override
    public void removeItemFromCart(int itemId) {

    }
}
