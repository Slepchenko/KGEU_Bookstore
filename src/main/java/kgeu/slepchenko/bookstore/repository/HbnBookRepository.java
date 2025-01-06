package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnBookRepository implements BookRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Book> findAll() {
        return crudRepository.query("from Book f join fetch f.category", Book.class);
    }

    @Override
    public Optional<Book> findById(int id) {
        return crudRepository.optional(
                "from Book f join fetch f.category where f.id = :fId",
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
        return crudRepository.query("from Book f join fetch f.category", Book.class, page, size);
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
        return crudRepository.optional("select count(b) from Book b", Long.class).get();
     }

     @Override
    public long getAllBooksSizeByCategory(String category) {
        return crudRepository.optional("select count(b) from Book b where b.category.name like :fCategory", Long.class, Map.of("fCategory", category)).get();
     }

     public long getSizeSearchedBook(String search) {
        long books = crudRepository.optional("select count(b) from Book b where lower(b.name) like lower(concat('%', :fSearch, '%')) "
                        + "or lower(b.author) like lower(concat('%', :fSearch, '%'))",
                Long.class,
                Map.of("fSearch", search)
        ).get();
        return books;
     }

     @Override
    public Collection<Book> searchBook(String search, int page, int size) {
       return crudRepository.query(
               "from Book b join fetch b.category a where lower(b.name) like lower(concat('%', :fSearch, '%')) "
                       + "or lower(b.author) like lower(concat('%', :fSearch, '%'))",
               Book.class,
               Map.of("fSearch", search),
               page,
               size
       );
    }
}
