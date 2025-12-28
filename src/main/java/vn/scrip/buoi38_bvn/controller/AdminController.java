package vn.scrip.buoi38_bvn.controller;

import vn.scrip.buoi38_bvn.entites.User;
import vn.scrip.buoi38_bvn.services.UserService;
import vn.scrip.buoi38_bvn.services.BookService;
import vn.scrip.buoi38_bvn.services.BorrowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final BookService bookService;
    private final BorrowService borrowService;

    public AdminController(UserService userService, BookService bookService, BorrowService borrowService) {
        this.userService = userService;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("borrows", borrowService.getAll());
        return "admin-dashboard";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
