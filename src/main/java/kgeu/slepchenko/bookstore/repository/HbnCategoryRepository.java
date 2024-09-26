package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    @Override
    public Optional<Category> findById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId",
                Category.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Category> findByName(String name) {
        return null;
    }
}
