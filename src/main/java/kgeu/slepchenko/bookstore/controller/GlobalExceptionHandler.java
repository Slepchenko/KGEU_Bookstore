package kgeu.slepchenko.bookstore.controller;

import kgeu.slepchenko.bookstore.exception.InvalidPasswordException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    public String handleInvalidPasswordException(InvalidPasswordException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "users/register";
    }
}
