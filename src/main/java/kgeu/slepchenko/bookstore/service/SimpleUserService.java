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
    private final ShoppingCartRepository shoppingCartRepository;


    @Override
    public Optional<User> save(User user) {

        User savedUser = userRepository.save(user).get();

        // Создаем корзину для пользователя
        ShoppingCart cart = new ShoppingCart();
        cart.setTotalPrice(0);
        cart.setUser(savedUser); // Устанавливаем связь
        shoppingCartRepository.save(cart);

        // Устанавливаем корзину в пользователя
        savedUser.setShoppingCart(cart);
        return userRepository.save(savedUser);




//        User savedUser;
//        if (user.getId() != 0) {
//            savedUser = userRepository.findByLoginAndPassword(user.getEmail(), user.getPassword())
//                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
//            savedUser.setName(user.getName());
//            savedUser.setEmail(user.getEmail());
//            savedUser.setPassword(user.getPassword());
//        } else {
//            savedUser = userRepository.save(user).get();
//        }
//
//        // Создаем корзину для пользователя
//        ShoppingCart cart = new ShoppingCart();
//        cart.setTotalPrice(0);
//        cart.setUser(savedUser); // Устанавливаем связь
//        shoppingCartRepository.save(cart);
//
//        // Обновляем связь в пользователе
//        savedUser.setShoppingCart(cart);
//        return userRepository.save(savedUser);
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
