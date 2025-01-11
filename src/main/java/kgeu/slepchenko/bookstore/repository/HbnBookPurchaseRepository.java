package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.BookPurchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnBookPurchaseRepository implements BookPurchaseRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<BookPurchase> create(BookPurchase bookPurchase) {
        crudRepository.run(session -> session.persist(bookPurchase));
        return Optional.of(bookPurchase);
    }

    @Override
    public Optional<BookPurchase> findById(int id) {
        return crudRepository.optional(
                "from BookPurchase bp " +
                        "join fetch bp.user u " +
                        "join fetch u.shoppingСart sc " +
                        "join fetch sc.items i " +
                        "join fetch i.book b " +
                        "where bp.id = :fId ",
                BookPurchase.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Optional<BookPurchase> deleteById(int id) {
        return Optional.empty();
    }

    @Override
    public List<BookPurchase> findAllByUserId(int id) {
        return crudRepository.query(
                "from BookPurchase bp join fetch bp.user u where u.id = :fId",
                BookPurchase.class,
                Map.of("fId", id)
        );
    }
}
