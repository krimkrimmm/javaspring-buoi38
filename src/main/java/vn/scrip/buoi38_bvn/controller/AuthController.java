package vn.scrip.buoi38_bvn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.UserService;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session) {
        User u = service.login(email, password);
        if (u == null) return "login";
        session.setAttribute("user", u);
        return "redirect:/books";
    }
}







