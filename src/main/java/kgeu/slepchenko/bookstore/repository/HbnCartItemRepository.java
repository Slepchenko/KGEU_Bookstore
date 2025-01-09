package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@AllArgsConstructor
public class HbnCartItemRepository implements CartItemRepository {

    final private CrudRepository crudRepository;

    @Override
    public void addItemToCart(CartItem item) {
        crudRepository.run(session -> session.merge(item));
    }

    @Override
    public boolean removeAllItemsFromCart(int id) {
        return crudRepository.query(
                "delete from CartItem ci where ci.shoppingCart.id = :fId",
                Map.of("fId", id)
        );
    }
}
