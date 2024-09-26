package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository {

    Collection<Category> findAll();

    Optional<Category> findById(int id);

    Collection<Category> findByName(String name);

}
