package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        crudRepository.run(session -> session.merge(user));
        return Optional.of(user);
    }

    public Optional<User> create(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
//        return crudRepository.optional("from User where email = :fEmail and password = :fPassword", User.class,
//                Map.of("fEmail", login, "fPassword", password));
        return crudRepository.optional("select distinct u from User u left join fetch u.shoppingCart sc left join fetch sc.items where u.email = :fEmail and u.password = :fPassword", User.class,
                Map.of("fEmail", login, "fPassword", password));
    }

   @Override
    public Optional<User> findUserById(int id) {
       return crudRepository
               .optional("from User where id = :fId", User.class, Map.of("fId", id));
    }
}
