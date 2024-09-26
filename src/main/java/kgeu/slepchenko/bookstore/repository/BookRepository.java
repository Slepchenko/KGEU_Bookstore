package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> findAll();

    Optional<Book> findById(int id);

    Collection<Book> findByCategory(String categoryName);

}
