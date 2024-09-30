package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findAll();

    Optional<Category> findById(int id);

    Optional<Category> findByName(String name);

}
