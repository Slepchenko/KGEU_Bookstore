package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnBookRepository implements BookRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Book> findAll() {
        return crudRepository.query("from Book", Book.class);
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Book> findByCategory(String categoryName) {
        return null;
    }
}
