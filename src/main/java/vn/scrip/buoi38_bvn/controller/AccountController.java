package vn.scrip.buoi38_bvn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi38_bvn.entities.User;
import vn.scrip.buoi38_bvn.services.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/update")
    public String update(User form, HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setFullName(form.getFullName());
        user.setPassword(form.getPassword());
        userService.register(user); // save lại
        session.setAttribute("user", user);
        return "redirect:/account";
    }
}

