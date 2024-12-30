package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User todoUser);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findUserById(int id);

    Optional<User> create(User user);

}
