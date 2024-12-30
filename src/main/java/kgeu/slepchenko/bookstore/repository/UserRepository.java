package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findUserById(int id);

    Optional<User> create(User user);

}
