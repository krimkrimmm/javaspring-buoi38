package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) { this.service = service; }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        User user = service.login(email, password);
        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
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

}
