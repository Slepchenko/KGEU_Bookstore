package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.exception.InvalidPasswordException;
import kgeu.slepchenko.bookstore.filter.AddUserModel;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "/users/register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpSession session, @ModelAttribute User user) {
        AddUserModel.checkInMenu(model, session);

        System.err.println("в метод регистер зашли");
        try {
            Optional<User> optionalUser = userService.create(user);
            System.err.println("в трай кетч зашли");
//            if (optionalUser.isEmpty()) {
//                System.err.println("а сюда?");
//                model.addAttribute("message", "Пользователь с таким логином уже существует");
//                return "errors/404";
//            }

            return "/users/login";
        } catch (InvalidPasswordException e) {

            System.err.println("в кэеч штоле??");
            model.addAttribute("user", user);
            model.addAttribute("error", e.getMessage());
            System.err.println(e.getMessage());
            return "/users/register";
        }
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        Optional<User> optionalUser = userService.findByLoginAndPassword(user.getEmail(), user.getPassword());
        if (optionalUser.isEmpty()) {
            model.addAttribute("error", "Логин или пароль введены неверно");
            return "/users/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", optionalUser.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }

}
