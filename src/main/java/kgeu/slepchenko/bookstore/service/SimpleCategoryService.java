package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Category;
import kgeu.slepchenko.bookstore.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Collection<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
