package vn.scrip.buoi38_bvn.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entities.User;
import vn.scrip.buoi38_bvn.services.UserService;

import static vn.scrip.buoi38_bvn.entities.Role.*;

@Controller
public class AuthController {
    private final UserService service;
    public AuthController(UserService service) { this.service = service; }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        User user = service.login(email, password);
        if(user != null) {
            session.setAttribute("user", user);
            switch(user.getRole()) {
                case ADMIN: return "redirect:/admin/books";
                case LIBRARIAN: return "redirect:/librarian/books";
                case READER: return "redirect:/";
            }
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() { return "register"; }

    @PostMapping("/register")
    public String register(User user) {
        service.register(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}