package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
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
        return crudRepository.optional(
                "from Book where id = :fId",
                Book.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Book> findByCategory(String categoryName) {
        return crudRepository.query(
                "from Book f join fetch f.category where f.category.name like :fCategory_name",
                Book.class,
                Map.of("fCategory_name", categoryName));
    }

    @Override
    public Collection<Book> findByPagination(int page, int size) {
        return crudRepository.query("from Book", Book.class, page, size);
    }

    public Collection<Book> findByCategoryPagination(String categoryName, int page, int size) {
        return crudRepository.query(
                "from Book f join fetch f.category where f.category.name like :fCategory_name",
                Book.class,
                Map.of("fCategory_name", categoryName),
                page,
                size);
    }

    @Override
    public long getAllBooksSize() {
//        return crudRepository.optional("select count(b) from Book b", Long.class).get();
        return crudRepository.optional("select count(b) from Book b", Long.class).get();
     }

     @Override
    public long getAllBooksSizeByCategory(String category) {
        return crudRepository.optional("select count(b) from Book b where b.category.name like :fCategory", Long.class, Map.of("fCategory", category)).get();
     }
}
