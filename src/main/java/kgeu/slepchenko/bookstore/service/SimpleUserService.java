package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.repository.ShoppingCartRepository;
import kgeu.slepchenko.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    private final ShoppingCartService shoppingCartService;


    @Override
    public Optional<User> save(User user) {

        User savedUser = userRepository.save(user).get();

        // Создаем корзину для пользователя
        ShoppingCart cart = new ShoppingCart();
        cart.setTotalPrice(0);
        cart.setUser(savedUser); // Устанавливаем связь
        shoppingCartService.save(cart);

        // Устанавливаем корзину в пользователя
        savedUser.setShoppingCart(cart);
        return create(savedUser);

    }

    @Override
    public Optional<User> create(User user) {
        return userRepository.create(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public String findUserNameById(int id) {
        return userRepository.findUserNameById(id);
    }
}
