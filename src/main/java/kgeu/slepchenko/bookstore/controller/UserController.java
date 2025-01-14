package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.exception.InvalidPasswordException;
import kgeu.slepchenko.bookstore.filter.AddUserModel;
import kgeu.slepchenko.bookstore.logic.ConvAllBookPurchase;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.service.BookPurchaseService;
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

    private final BookPurchaseService bookPurchaseService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "/users/register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpSession session, @ModelAttribute User user) {
        AddUserModel.checkInMenu(model, session);
        try {
            userService.create(user);
            return "/users/login";
        } catch (InvalidPasswordException e) {
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

    @GetMapping("/info")
    public String userInfo(Model model, HttpSession session) {
        User user = (User) AddUserModel.checkInMenu(model, session).getAttribute("user");
        if (user == null) {
            return "redirect:/users/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("all", bookPurchaseService.findAllByUserId(user.getId()));
        model.addAttribute("info", ConvAllBookPurchase.getListInfo(bookPurchaseService.findAllByUserId(user.getId())));
        return "users/userInfo";
    }

}
