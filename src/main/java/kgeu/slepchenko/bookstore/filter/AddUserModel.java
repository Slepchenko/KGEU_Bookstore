package kgeu.slepchenko.bookstore.filter;

import kgeu.slepchenko.bookstore.model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public class AddUserModel {
    public static Model checkInMenu(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return model.addAttribute("user", user);
    }
}
