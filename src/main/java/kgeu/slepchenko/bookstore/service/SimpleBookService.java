package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleBookService implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Collection<Book> findByCategory(String categoryName) {
        return bookRepository.findByCategory(categoryName);
    }
}
