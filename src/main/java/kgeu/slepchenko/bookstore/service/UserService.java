package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User todoUser);

    Optional<User> findByLoginAndPassword(String login, String password);

    String findUserNameById(int id);

    Optional<User> create(User user);

}
