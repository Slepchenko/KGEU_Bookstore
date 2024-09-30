package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Collection<Book> findAll();

    Optional<Book> findById(int id);

    Collection<Book> findByCategory(String categoryName);

    Collection<Book> findByPagination(int page, int size);

    long getAllBooksSize();

}
