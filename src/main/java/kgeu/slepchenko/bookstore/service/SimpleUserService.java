package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.exception.InvalidPasswordException;
import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.passay.*;
import java.util.ArrayList;
import java.util.List;
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
        return userRepository.save(user);
    }

    @Override
    public Optional<User> create(User user) {
        if (!validatePassword(user)) {
            throw new InvalidPasswordException(
                    "Пароль должен содержать прописные буквы, специальный символ, цифру и т.д. ну вы сами знаете"
            );
        }
        User savedUser = userRepository.create(user).get();
        ShoppingCart cart = new ShoppingCart();
        cart.setTotalPrice(0);
        cart.setUser(savedUser);
        shoppingCartService.save(cart);
        savedUser.setShoppingCart(cart);
        return save(savedUser);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findUserById(id);
    }

    private boolean validatePassword(User user) {
        List<Rule> rules = new ArrayList<>();
        rules.add(new LengthRule(8, 16));
        rules.add(new WhitespaceRule());
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
        rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
        PasswordValidator validator = new PasswordValidator(rules);
        PasswordData password = new PasswordData(user.getPassword());
        RuleResult result = validator.validate(password);
        return result.isValid();
    }
}
